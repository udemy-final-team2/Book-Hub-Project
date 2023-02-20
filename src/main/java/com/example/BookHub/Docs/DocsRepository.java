package com.example.BookHub.Docs;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DocsRepository {

    void writeDocument(DocsDTO dto);
    List<DocsDTO> readDocumentList(Long folder_id);
    DocsDTO readDocument(Long id);
    void deleteDocument(Long id);
}