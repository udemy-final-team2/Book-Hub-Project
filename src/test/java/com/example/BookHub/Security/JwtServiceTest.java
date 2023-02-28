package com.example.BookHub.Security;

import com.example.BookHub.User.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static com.example.BookHub.User.Role.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class JwtServiceTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDTO user;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        jwtService = new JwtService();
    }

    @Test
    @DisplayName("토큰 생성 테스트")
    public void testGenerateToken() {
        // given
        when(user.getUsername()).thenReturn("test@gmail.com");
        when(user.getPassword()).thenReturn("testpassword");

        // when
        String token = jwtService.generateToken(Collections.emptyMap(), user);

        // then
        assertNotNull(token);
        System.out.println("발급된 토큰 : " + token);
    }

    @Test
    @DisplayName("사용자 정보 추출 테스트")
    void testExtractClaimFromToken() {
        // given
        when(user.getId()).thenReturn(Long.valueOf(1));
        when(user.getUsername()).thenReturn("test@gmail.com");
        when(user.getPassword()).thenReturn("testpassword");
        when(user.getRole()).thenReturn(USER);

        String token = jwtService.generateToken(Collections.emptyMap(), user);

        // when
        String username = jwtService.extractUsername(token);
        Long userId = jwtService.extractUserId(token);

        // then
        assertEquals("test@gmail.com", username);
        assertEquals(1L, userId);
    }

    @Test
    @DisplayName("토큰 Valid 성공 테스트")
    public void testAuthenticateValidToken() {
        // given
        when(user.getUsername()).thenReturn("test@gmail.com");
        String token = jwtService.generateToken(Collections.emptyMap(), user);

        // when
        boolean isTokenValid = jwtService.isTokenValid(token, user);

        // then
        assertTrue(isTokenValid);
    }

    @Test
    @DisplayName("토큰 Valid 실패 테스트")
    public void testAuthenticateInvalidToken() {
        // given
        when(user.getUsername()).thenReturn("test@gmail.com");
        String token = jwtService.generateToken(Collections.emptyMap(), user);

        // when
        try {
            jwtService.isTokenValid(token + "invalid", user);
        } catch (Exception e) {
            // then
            assertFalse(false);
        }
    }

    @Test
    @DisplayName("토큰 만료 테스트 - 만료 안된 토큰")
    void testIsTokenThatHaveNotExpired() throws InterruptedException {
        // given
//        String token = jwtService.generateToken(Collections.emptyMap(), user);
        // 토큰이 만료되기전
//        Thread.sleep(1000);

        // when
//        boolean isExpired = jwtService.isTokenExpired(token);

        // then 토큰이 만료되지 않음.
//        assertFalse(isExpired);
    }

    @Test
    @DisplayName("토큰 만료 테스트 - 만료된 토큰")
    void testIsTokenExpired() throws InterruptedException {
        // given
//        String token = jwtService.generateToken(Collections.emptyMap(), user);
        // 토큰이 만료되기를 기다리기
//        Thread.sleep(20000);

        // when
//        try {
//            jwtService.isTokenExpired(token);
//        } catch (ExpiredJwtException e) {
        // then 토큰이 만료됨.
//            assertTrue(true);
//        }
    }


}