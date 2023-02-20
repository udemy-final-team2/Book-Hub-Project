package com.example.BookHub.Storage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Component("storagedto")
public class StorageDTO {

    private int id, document_id;
    private String url;
    private LocalDateTime save_at;
}
