package com.example.BookHub.Docs;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DocsRepository {

    // 문서 작성
    void writeDocument(DocsDTO dto);
    // 문서 단건 조회
    DocsDTO readDocument(Long userId, Long documentId);
    // 문서 단일 삭제
    void deleteDocument(Long userId, Long documentId);
    // 문서 개수 조회

}