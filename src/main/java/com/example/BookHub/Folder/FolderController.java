package com.example.BookHub.Folder;

import com.example.BookHub.Docs.DocsDTO;
import com.example.BookHub.Security.OAuth2.CustomOAuth2UserService;
import com.example.BookHub.User.UserDTO;
import com.example.BookHub.User.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.example.BookHub.Util.SessionConst.LOGIN_USER;

@Controller
@Slf4j
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;
    private final UserService userService;
    private final CustomOAuth2UserService customOAuth2UserService;

    // 폴더 생성
    @PostMapping("/folder/create")
    public String createFolder(HttpSession session, Authentication authentication, @RequestParam String name) {

        UserDTO userDto;
        Long userId;
        if (authentication != null && !authentication.getName().equals("anonymousUser")) {
            String socialId = customOAuth2UserService.getUserId(authentication);
            userDto = userService.findBySocialId(socialId);
            userId = userDto.getId();
        } else {
            userId = ((UserDTO) session.getAttribute(LOGIN_USER)).getId();
        }
        FolderDTO dto = FolderDTO.builder()
         .userId(userId)
         .name(name)
         .build();
        folderService.createFolder(dto);
        return "redirect:/folder/list";
    }

    // 폴더 삭제
    @ResponseBody
    @PostMapping("/folder/delete")
    public void deleteFolder(@RequestParam Long folderId) {
        folderService.deleteDocumentList(folderId);
        folderService.deleteFolder(folderId);
    }

    // 폴더 이름 수정
    @PostMapping("/folder/update/{folderId}")
    public String updateFolderName(HttpSession session, Authentication authentication, @PathVariable Long folderId, String name) {
        UserDTO userDto;
        Long userId;
        if (authentication != null && !authentication.getName().equals("anonymousUser")) {
            String socialId = customOAuth2UserService.getUserId(authentication);
            userDto = userService.findBySocialId(socialId);
            userId = userDto.getId();
        } else {
            userId = ((UserDTO) session.getAttribute(LOGIN_USER)).getId();
        }
        folderService.updateFolderName(userId, folderId, name);
        return "redirect:/folder/list";
    }

    // 폴더 목록 조회
    @GetMapping("/folder/list")
    public String readFolderList(HttpSession session, Authentication authentication, Model model) {
        UserDTO userDto;
        Long userId;
        if (authentication != null && !authentication.getName().equals("anonymousUser")) {
            String socialId = customOAuth2UserService.getUserId(authentication);
            userDto = userService.findBySocialId(socialId);
            userId = userDto.getId();
        } else {
            userId = ((UserDTO) session.getAttribute(LOGIN_USER)).getId();
        };
        List<FolderDTO> folderList = folderService.readFolderList(userId);
        model.addAttribute("folderList", folderList);
        return "document";
    }

    // 폴더 속 문서 목록 조회
    @ResponseBody
    @GetMapping("/folderlist")
    public List<DocsDTO> getDocumentList(@RequestParam Long folderId) {
        return folderService.readDocumentList(folderId);
    }
}
