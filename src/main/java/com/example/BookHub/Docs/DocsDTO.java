package com.example.BookHub.Docs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Component("docsdto")
public class DocsDTO {

    private int id, user_id;
    private String title, memo;

}
