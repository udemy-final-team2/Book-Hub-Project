package com.example.BookHub.Docs;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DocsService {

    private final AmazonS3Client amazonS3Client;
    private final DocsRepository repository;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;   // S3 버킷 이름

    // 문서 저장
    void writeDocument(DocsDTO dto) {
        repository.writeDocument(dto);
    }

    // 문서 목록 조회
    List<DocsDTO> readDocumentList(Long folder_id) {
        return repository.readDocumentList(folder_id);
    }

    // 문서 조회
    DocsDTO readDocument(Long id) {
        return repository.readDocument(id);
    }

    // 문서 삭제
    void deleteDocument(Long id) {
        repository.deleteDocument(id);
    }

    public String upload(MultipartFile multipartFile) throws IOException {
        log.info("multipartFile={}", multipartFile);
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 변환 실패"));
        return upload(uploadFile);
    }

    public String upload(File uploadFile) {
        String fileUrl = putS3(uploadFile, uploadFile.getName());
        //removeNewFile(uploadFile);
        return fileUrl;
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

    // s3로 업로드
    private String putS3(File uploadFile, String fileName) {
        fileName = createFileName(fileName);
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 파일명을 난수화(UUID)
    public String createFileName(String fileName){
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