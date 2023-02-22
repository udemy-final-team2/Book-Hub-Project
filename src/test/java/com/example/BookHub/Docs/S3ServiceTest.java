package com.example.BookHub.Docs;

import com.example.BookHub.S3.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
@Slf4j
class S3ServiceTest {
    @Autowired
    private S3Service s3Service;

    // S3로 파일 업로드
    @Test
    void testUpload() {
        try {
            String filePath = "/파일경로/파일명";
            String uploadName = s3Service.upload(new File(filePath));
            log.info("업로드 파일명 = {} ", uploadName);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    // S3에서 파일 삭제
    @Test
    void testRemove() {
        try {
            s3Service.removeS3File("파일명");
            log.info("S3에서 파일 삭제 완료");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}