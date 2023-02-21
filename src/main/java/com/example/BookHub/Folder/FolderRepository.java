package com.example.BookHub.Folder;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FolderRepository {
    // 폴더 생성
    void createFolder(FolderDTO dto);
    // 폴더 삭제
    void deleteFolder(Long id);
    // 폴더 이름 수정
    void updateFolderName(String name, Long id);
    // 폴더 이름 목록 조회
    List<FolderDTO> readFolderList(Long id);
    // 폴더 조회
    int selectFolder(Long id);
}
