package com.example.BookHub.Docs;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Component("docsdto")
public class DocsDTO {

    private Long id, folder_id;
    private String title, memo, url;
    private LocalDateTime save_at;

    @Builder
    public DocsDTO(Long folder_id, String title, String memo, String url) {
        this.folder_id = folder_id;
        this.title = title;
        this.memo = memo;
        this.url = url;
    }
}
