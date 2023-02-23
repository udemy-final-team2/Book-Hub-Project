package com.example.BookHub.Folder;

import com.example.BookHub.Docs.DocsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FolderService {

    private final FolderRepository repository;

    // 폴더 목록 조회
    List<FolderDTO> readFolderList(Long userId){
        return repository.readFolderList(userId);
    }

    // 폴더 이름 수정
    void updateFolderName(Long userId, Long folderId, String name){
        repository.updateFolderName(userId, folderId, name);
    }

    // 폴더 삭제
    void deleteFolder(Long userId, Long folderId){
        repository.deleteFolder(userId, folderId);
    }

    // 폴더 생성
    void createFolder(FolderDTO dto) {
        repository.createFolder(dto);
    }

    // 폴더 속 문서 목록 조회
    List<DocsDTO> readDocumentList(Long userId, Long folderId) {
        return repository.readDocumentList(userId, folderId);
    }
}
