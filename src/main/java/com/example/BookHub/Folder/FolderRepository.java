package com.example.BookHub.Folder;

import com.example.BookHub.Docs.DocsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FolderRepository {
    // 폴더 생성
    void createFolder(FolderDTO dto);
    // 폴더 삭제
    void deleteFolder(Long userId, Long folderId);
    // 폴더 이름 수정
    void updateFolderName(Long userId, Long folderId, String name);
    // 폴더 목록 조회
    List<FolderDTO> readFolderList(Long userId);
    // 폴더 속 문서 목록 조회
    List<DocsDTO> readDocumentList(Long userId, Long folderId);
}
