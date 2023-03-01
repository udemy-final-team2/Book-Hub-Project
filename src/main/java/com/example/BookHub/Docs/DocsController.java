package com.example.BookHub.Docs;

import com.example.BookHub.S3.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DocsController {

    private final DocsService docsService;
    private final S3Service s3Service;

    // 문서 작성폼
    @GetMapping("/document/write")
    public String writeDocument() {
        return "writeform";
    }

    // 문서 작성
    @PostMapping("/document/write")
    public String writeDocument(@ModelAttribute DocsDTO dto,
                                MultipartFile file) throws IOException {
        String s3Key = s3Service.upload(file);
        dto.setS3Key(s3Key);
        docsService.writeDocument(dto);
        return "redirect:/document/write";
    }

    // 문서 단건 조회
    @GetMapping("/document/read/{documentId}")
    public String readDocument(@PathVariable Long documentId, Model model) {
        DocsDTO document = docsService.readDocument(documentId);
        String key = document.getS3Key();
        String htmlContent = s3Service.readDocument(key);
        model.addAttribute("document", document);
        model.addAttribute("htmlContent", htmlContent);
        return "documentdetail";
    }

    // 문서 단일 삭제
    @PostMapping("/document/delete")
    public String deleteDocument(@RequestParam Long documentId) {
        docsService.deleteDocument(documentId);
        return "redirect:/folder/list";
    }

    // 문서 비교
    @GetMapping("/document/compare")
    public String compareDocument(@RequestParam Long documentId1, @RequestParam Long documentId2, Model model) {
        DocsDTO document1 = docsService.readDocument(documentId1);
        DocsDTO document2 = docsService.readDocument(documentId2);
        String[] documentList = s3Service.compareDocument(document1.getS3Key(), document2.getS3Key());
        model.addAttribute("documentList", documentList);
        return "compareview";
    }

}
