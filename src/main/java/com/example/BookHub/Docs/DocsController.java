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
@Slf4j
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
        String url = docsService.upload(file);
        DocsDTO saveDoc = DocsDTO.builder()
                .folderId(dto.getFolderId())
                .title(dto.getTitle())
                .memo(dto.getMemo())
                .s3Key(dto.getS3Key())
                .build();
        docsService.writeDocument(saveDoc);
        return "redirect:/docs/write";
    }

    // 문서 조회
    @GetMapping("/docs/read/{documentId}")
    public String readDocument(@PathVariable Long documentId, Model model) {
        DocsDTO document = docsService.readDocument(documentId);
        model.addAttribute("document", document);
        return "document";
    }

    // 폴더 문서 리스트 조회
    @GetMapping("/docs/{folderId}")
    public String getDocumentList(@PathVariable Long folderId, Model model) {
        List<DocsDTO> documentList = docsService.readDocumentList(folderId);
        model.addAttribute("documentList", documentList);
        return "docs";
    }

    // 문서 삭제
    @PostMapping("/docs/delete")
    public String deleteDocument(@RequestParam Long documentId) {
        docsService.deleteDocument(documentId);
        return "redirect:/docs/list";
    }

    // 문서 비교
    @PostMapping("/docs/compare")
    public String[] compareDocument(@RequestParam Long documentId1, @RequestParam Long documentId2) {
        DocsDTO document1 = docsService.readDocument(documentId1);
        DocsDTO document2 = docsService.readDocument(documentId2);
        return docsService.compareDocument(document1.getS3Key(), document2.getS3Key());
    }
}
