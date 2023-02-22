package com.example.BookHub.Folder;

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
    public String createFolder(@RequestParam String name, HttpSession session) {
        Long userId = (Long) session.getAttribute(LOGIN_USER);
        FolderDTO dto = FolderDTO.builder()
                .userId(userId)
                .name(name)
                .build();
        folderService.createFolder(dto);
        return "redirect:/docs";
    }

    // 폴더 삭제
    @PostMapping("/folder/delete")
    public String deleteFolder(@RequestParam Long id) {
        folderService.deleteFolder(id);
        return "redirect:/docs";
    }

    // 폴더 이름 수정
    @PostMapping("/folder/update/{id}")
    public String updateFolderName(String name , @PathVariable Long id) {
        folderService.updateFolderName(name, id);
        return "redirect:/docs";
    }

    // 폴더 목록 조회
    @GetMapping
    public String readFolderList(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute(LOGIN_USER);
        List<FolderDTO> folderList = folderService.readFolderList(userId);
        model.addAttribute("folderList", folderList);
        return "docs";
    }
}
