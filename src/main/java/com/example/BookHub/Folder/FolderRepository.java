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
    void deleteFolder(long id);
    // 폴더 이름 수정
    void updateFolderName(String name, long id);
    // 폴더 이름 목록 조회
    List<String[]> selectFolderList(long id);
    // 폴더 조회
    int selectFolder(long id);
}
