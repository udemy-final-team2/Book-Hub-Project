package com.example.BookHub.Security.Token;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Mapper
@Component("tokenrepository")
public interface TokenRepository {
    List<TokenDTO> findAllValidTokenByUser(Long id);

    Optional<TokenDTO> findByToken(String token);

    void save(TokenDTO token);

    void saveAll(List<TokenDTO> validUserTokens);

    TokenDTO findById(Long id);
}
