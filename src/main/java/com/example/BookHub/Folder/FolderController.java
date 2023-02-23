package com.example.BookHub.Folder;

import com.example.BookHub.Docs.DocsDTO;
import com.example.BookHub.User.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.example.BookHub.Util.SessionConst.LOGIN_USER;

@Controller
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    // 폴더 생성
    @PostMapping("/folder/create")
    public String createFolder(HttpSession session, @RequestParam String name) {
        Long userId = ((UserDTO) session.getAttribute(LOGIN_USER)).getId();
        FolderDTO dto = FolderDTO.builder()
                .userId(userId)
                .name(name)
                .build();
        folderService.createFolder(dto);
        return "redirect:/folder/list";
    }

    // 폴더 삭제
    @PostMapping("/folder/delete/{folderId}")
    public String deleteFolder(HttpSession session, @PathVariable Long folderId) {
        Long userId = ((UserDTO) session.getAttribute(LOGIN_USER)).getId();
        folderService.deleteFolder(userId, folderId);
        return "redirect:/folder/list";
    }

    // 폴더 이름 수정
    @PostMapping("/folder/update/{folderId}")
    public String updateFolderName(HttpSession session, @PathVariable Long folderId, String name) {
        Long userId = ((UserDTO) session.getAttribute(LOGIN_USER)).getId();
        folderService.updateFolderName(userId, folderId, name);
        return "redirect:/folder/list";
    }

    // 폴더 목록 조회
    @GetMapping("/folder/list")
    public String readFolderList(HttpSession session, Model model) {
        Long userId = ((UserDTO) session.getAttribute(LOGIN_USER)).getId();
        List<FolderDTO> folderList = folderService.readFolderList(userId);
        model.addAttribute("folderList", folderList);
        return "docs";
    }

    // 폴더 속 문서 목록 조회
    @GetMapping("/folder/{folderId}")
    public String getDocumentList(HttpSession session, @PathVariable Long folderId, Model model) {
        Long userId = ((UserDTO) session.getAttribute(LOGIN_USER)).getId();
        List<DocsDTO> documentList = folderService.readDocumentList(userId, folderId);
        model.addAttribute("documentList", documentList);
        return "docs";
    }
}
