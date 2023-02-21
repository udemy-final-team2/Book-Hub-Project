package com.example.BookHub.Docs;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DocsRepository {

    // 문서 작성
    void writeDocument(DocsDTO dto);
    // 문서 목록 조회
    List<DocsDTO> readDocumentList(Long folder_id);
    // 문서 조회
    DocsDTO readDocument(Long id);
    // 문서 삭제
    void deleteDocument(Long id);
}