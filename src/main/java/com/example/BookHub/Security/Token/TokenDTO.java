package com.example.BookHub.Security.Token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component("tokendto")
public class TokenDTO {

    public Long id;
    public Long userId;
    public String token;
    public TokenType tokenType;
    public boolean revoked;
    public boolean expired;

}
