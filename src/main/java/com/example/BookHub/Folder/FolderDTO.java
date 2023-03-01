package com.example.BookHub.Folder;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@Component("folderdto")
public class FolderDTO {

    private Long id, userId;
    private String name;

    @Builder
    public FolderDTO(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
