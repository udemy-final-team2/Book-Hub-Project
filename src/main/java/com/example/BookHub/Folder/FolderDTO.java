package com.example.BookHub.Folder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component("folderdto")
public class FolderDTO {

    private Long id, user_id;
    private String name;
}
