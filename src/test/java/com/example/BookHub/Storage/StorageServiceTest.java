package com.example.BookHub.Storage;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class StorageServiceTest {
    @Autowired
    private StorageService storageService;

    @Test
    public void testUpload() {
        try {
            String filePath = "/Users/taehyun/Downloads/README.md";
            String uploadName = storageService.upload(filePath);
            log.info(uploadName);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testRemove() {
        try {
            storageService.removeS3File("a08696b3-03c1-4185-9fc1-9942f518e637.md");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testGetList() {
        try {
            storageService.getFileList();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}