package com.example.BookHub.Docs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DocsController {

    private final DocsService docsService;

    // 문서 작성폼
    @GetMapping("/docs/write")
    public String writeDocument() {
        return "writeform";
    }

    // 문서 작성
    @PostMapping("/docs/write")
    public String writeDocument(DocsDTO dto, MultipartFile file) throws IOException {
        String s3Key = docsService.upload(file);
        DocsDTO saveDoc = DocsDTO.builder()
                .folderId(dto.getFolderId())
                .title(dto.getTitle())
                .memo(dto.getMemo())
                .s3Key(s3Key)
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
    @GetMapping("/docs/{folder_id}")
    public String getDocumentList(@PathVariable Long folder_id, Model model) {
        List<DocsDTO> documentList = docsService.readDocumentList(folder_id);
        model.addAttribute("documentList", documentList);
        return "docs";
    }

    // 문서 삭제
    @PostMapping("/delete")
    public String deleteDocument(@RequestParam Long documentId) {
        docsService.deleteDocument(documentId);
        return "redirect:/docs/list";
    }
}