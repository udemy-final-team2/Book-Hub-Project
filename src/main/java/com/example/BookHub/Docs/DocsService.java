package com.example.BookHub.Docs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

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

    // 문서 목록 조회
    public List<DocsDTO> readDocumentList(Long folder_id) {
        return repository.readDocumentList(folder_id);
    }

    // 문서 조회
    public DocsDTO readDocument(Long id) {
        return repository.readDocument(id);
    }

    // 문서 삭제
    public void deleteDocument(Long id) {
        repository.deleteDocument(id);
    }

}
