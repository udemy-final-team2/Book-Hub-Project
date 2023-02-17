package com.example.BookHub.Docs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("docsservice")
@RequiredArgsConstructor
public class DocsService {

    private final DocsRepository repository;

    public int insertDocs(DocsDTO dto) {
        return repository.insertDocs(dto);
    }
}
