package com.example.BookHub.Docs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/docs")
public class DocsController {

    private final DocsService docsService;

    // 문서 작성폼
    @GetMapping("/write")
    public String writeDocument() {
        return "writeform";
    }

    // 문서 작성
    @PostMapping("/write")
    public String writeDocument(DocsDTO dto, MultipartFile file) throws IOException {
        String url = docsService.upload(file);
        DocsDTO saveDoc = DocsDTO.builder()
                .folder_id(dto.getFolder_id())
                .title(dto.getTitle())
                .memo(dto.getMemo())
                .url(url)
                .build();
        docsService.writeDocument(saveDoc);
        return "redirect:/docs/write";
    }

    // 문서 조회
    @GetMapping("/read/{documentId}")
    public String readDocument(@PathVariable Long documentId, Model model) {
        DocsDTO document = docsService.readDocument(documentId);
        model.addAttribute("document", document);
        return "document";
    }

    // 문서 리스트
    @GetMapping("/list")
    public String getDocumentList() {
        return "docs";
    }

    // 문서 삭제
    @PostMapping("/delete")
    public String deleteDocument(@RequestParam Long documentId) {
        docsService.deleteDocument(documentId);
        return "redirect:/docs/list";
    }
}
