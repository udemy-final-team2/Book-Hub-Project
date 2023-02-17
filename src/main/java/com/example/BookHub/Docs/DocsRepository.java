package com.example.BookHub.Docs;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("docsrepository")
public interface DocsRepository {

    int insertDocs(DocsDTO dto);
}
