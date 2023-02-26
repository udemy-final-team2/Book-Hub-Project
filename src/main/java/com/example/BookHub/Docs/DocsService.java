package com.example.BookHub.Docs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DocsService {

    private final DocsRepository repository;

    // 문서 작성
    public void writeDocument(DocsDTO dto) {
        repository.writeDocument(dto);
    }

    // 문서 단건 조회
    public DocsDTO readDocument(Long userId, Long documentId) {
        return repository.readDocument(userId, documentId);
    }

    // 문서 단일 삭제
    public void deleteDocument(Long userId, Long documentId) {
        repository.deleteDocument(userId, documentId);
    }

}
