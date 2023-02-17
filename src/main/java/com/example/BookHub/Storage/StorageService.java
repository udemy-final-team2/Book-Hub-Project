package com.example.BookHub.Storage;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class StorageService {

    private final AmazonS3Client amazonS3Client;
    private final StorageRepository repository;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;   // S3 버킷 이름

    // 파일 업로드
    /*public String upload(String filePath) {
        File targetFile = new File(filePath);
        log.info(targetFile.getName());
        return putS3(targetFile, targetFile.getName());
    }*/

    public void insertStorage(StorageDTO dto) {
        repository.insertStorage(dto);
    }

    public String upload(MultipartFile multipartFile) throws IOException {
        log.info("multipartFile={}", multipartFile);

        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 변환 실패"));
        return upload(uploadFile);
    }

    private String upload(File uploadFile) {
        String imageUrl = putS3(uploadFile, uploadFile.getName());
        removeNewFile(uploadFile);
        return imageUrl;
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
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

    // s3로 업로드
    private String putS3(File uploadFile, String fileName) {
        fileName = createFileName(fileName);
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 파일명을 난수화(UUID)
    public String createFileName(String fileName){
//        return UUID.randomUUID().toString().concat(fileName.substring(fileName.lastIndexOf(".")));
        return UUID.randomUUID() + "_" + fileName;
    }

    // s3에서 파일 삭제
    public void removeS3File(String fileName) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    public void getFileList() {
        List<S3ObjectSummary> summaries = amazonS3Client.listObjects(bucket).getObjectSummaries();
        log.info(String.valueOf(summaries.size()));
        for (S3ObjectSummary summary: summaries) {
            log.info("s3에 저장된 파일 이름 = " + summary.getKey());
        }
    }
}
