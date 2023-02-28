package com.example.BookHub.Docs;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DocsRepository {

    // 문서 작성
    void writeDocument(DocsDTO dto);
    // 문서 단건 조회
    DocsDTO readDocument(Long id);
    // 문서 단일 삭제
    void deleteDocument(Long id);
    // 문서 개수 조회

}