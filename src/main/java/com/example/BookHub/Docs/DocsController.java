package com.example.BookHub.Docs;

import com.example.BookHub.S3.S3Service;
import com.example.BookHub.User.UserDTO;
import com.example.BookHub.Util.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.example.BookHub.Util.SessionConst.LOGIN_USER;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DocsController {

    private final DocsService docsService;
    private final S3Service s3Service;

    // 문서 작성폼
    @GetMapping("/docs/write")
    public String writeDocument() {
        return "writeform";
    }

    // 문서 작성
    @PostMapping("/docs/write")
    public String writeDocument(@ModelAttribute DocsDTO dto,
                                MultipartFile file) throws IOException {
        String s3Key = s3Service.upload(file);
        dto.setS3Key(s3Key);
        docsService.writeDocument(dto);
        return "redirect:/docs/write";
    }

    // 문서 단건 조회
    @GetMapping("/docs/read/{documentId}")
    public String readDocument(HttpSession session,
                               @PathVariable Long documentId,
                               Model model) {
        Long userId = ((UserDTO) session.getAttribute(LOGIN_USER)).getId();
        DocsDTO document = docsService.readDocument(userId, documentId);
        model.addAttribute("document", document);
        return "docsdetail";
    }

    // 문서 단일 삭제
    @GetMapping("/docs/delete")
    public String deleteDocument(HttpSession session, @RequestParam Long documentId) {
        log.info(String.valueOf(documentId));
        Long userId = ((UserDTO) session.getAttribute(LOGIN_USER)).getId();
        docsService.deleteDocument(userId, documentId);
        return "redirect:/folder/list";
    }

    // 문서 비교
    @PostMapping("/docs/compare")
    public String compareDocument(HttpSession session,
                                  @RequestParam Long documentId1,
                                  @RequestParam Long documentId2,
                                  Model model) {
        Long userId = ((UserDTO) session.getAttribute(LOGIN_USER)).getId();
        DocsDTO document1 = docsService.readDocument(userId, documentId1);
        DocsDTO document2 = docsService.readDocument(userId, documentId2);
        String[] documentList = s3Service.compareDocument(document1.getS3Key(), document2.getS3Key());
        model.addAttribute("documentList", documentList);
        return "compareview";
    }
}
