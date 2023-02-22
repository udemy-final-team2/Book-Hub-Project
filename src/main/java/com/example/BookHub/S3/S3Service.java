package com.example.BookHub.S3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 s3;

    public String upload(MultipartFile multipartFile) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 변환 실패"));
        return upload(uploadFile);
    }

    public String upload(File uploadFile) {
        //removeNewFile(uploadFile);
        return putS3(uploadFile, uploadFile.getName());
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일을 S3에 정상적으로 업로드하고, 로컬에 복사된 파일은 삭제되었습니다.");
        } else {
            log.info("로컬에 복사된 파일을 삭제하지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    // S3로 파일 업로드
    private String putS3(File uploadFile, String fileName) {
        fileName = createFileName(fileName);
        s3.putObject(bucket, fileName, uploadFile);
        return fileName;
    }

    // S3로 파일 업로드를 위한 파일명 난수화(UUID)
    public String createFileName(String fileName){
        String timeStamp = String.valueOf(System.currentTimeMillis());
        return timeStamp + "_" + UUID.randomUUID() + "_" + fileName;
    }

    // S3에서 파일 삭제
    public void removeS3File(String fileName) {
        if (s3.doesObjectExist(bucket, fileName)) {
            s3.deleteObject(new DeleteObjectRequest(bucket, fileName));
        } else {
            log.warn("File " + fileName + " does not exist in S3 bucket " + bucket);
        }
    }

    // 문서 비교
    public String[] compareDocument(String key1, String key2) {
        S3Object s3Object1 = s3.getObject(bucket, key1);
        String htmlContent1 = new BufferedReader(
                new InputStreamReader(s3Object1.getObjectContent(), UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        S3Object s3Object2 = s3.getObject(bucket, key2);
        String htmlContent2 = new BufferedReader(
                new InputStreamReader(s3Object2.getObjectContent(), UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        return new String[]{htmlContent1, htmlContent2};
    }
}
