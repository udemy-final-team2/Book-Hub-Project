package com.example.BookHub.Folder;

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
    List<String[]> selectFolderList(long id){
        return repository.selectFolderList(id);
    }

    // 폴더 이름 수정
    void updateFolderName(String name, long id){
        repository.updateFolderName(name, id);
    }

    // 폴더 삭제
    void deleteFolder(long id){
        repository.deleteFolder(id);
    }

    // 폴더 생성
    void createFolder(FolderDTO dto) {
        repository.createFolder(dto);
    }

    // 폴더 조회
    int selectFolder(long id) {
        return repository.selectFolder(id);
    }
}
