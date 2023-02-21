package com.example.BookHub.Docs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
@Slf4j
class DocsServiceTest {
    @Autowired
    private DocsService docsService;

    @Test
    void testUpload() {
        try {
            String filePath = "/Users/사용자/Downloads/파일명";
            String uploadName = docsService.upload(new File(filePath));
            log.info(uploadName);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void testRemove() {
        try {
            docsService.removeS3File("파일명");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    // 문서 비교 테스트
    @Test
    void compareDocument() {
        String key1 = "파일명1";
        String key2 = "파일명2";
        String[] strings = docsService.compareDocument(key1, key2);
        Assertions.assertEquals(strings.length, 2);
    }
}