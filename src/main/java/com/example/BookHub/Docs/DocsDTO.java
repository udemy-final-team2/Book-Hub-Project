package com.example.BookHub.Docs;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@ToString
@Component("docsdto")
public class DocsDTO {

    private Long id, folderId;
    private String title, memo, s3Key, saveAt;

    @Builder
    public DocsDTO(Long folderId, String title, String memo, String s3Key) {
        this.folderId = folderId;
        this.title = title;
        this.memo = memo;
        this.s3Key = s3Key;
    }
}
