package com.example.BookHub.Docs;

import com.example.BookHub.Storage.StorageDTO;
import com.example.BookHub.Storage.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller("docscontroller")
@RequiredArgsConstructor
@Slf4j
public class DocsController {

    private final DocsService docsService;
    private final StorageService storageService;

    // 문서 작성폼
    @GetMapping("docs/write")
    public String insertDocs() {
        return "insertform";
    }

    // 문서 작성
    @PostMapping("docs/write")
    public String insertDocs(String title, String memo,
                             MultipartFile file) throws IOException {
        DocsDTO dto = new DocsDTO();
        dto.setTitle(title);
        dto.setMemo(memo);
        int insetId = docsService.insertDocs(dto);
        log.info("multipartFile={}", file);

        StorageDTO storageDTO = new StorageDTO();
        String url = storageService.upload(file);
        storageDTO.setUrl(url);
        storageDTO.setDocument_id(insetId);
        storageService.insertStorage(storageDTO);
        return "docs";
    }
}
