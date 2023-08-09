package com.fastcampus.boardproject.config;

import com.fastcampus.boardproject.domain.UserAccount;
import com.fastcampus.boardproject.repository.UserAccountRepository;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean
    private UserAccountRepository userAccountRepository;

    @BeforeTestMethod
    public void securitySetUp() {
        given(userAccountRepository.findById(anyString())).willReturn(Optional.of(UserAccount.of(
                "jinTest",
                "pw",
                "jinTest@email.com",
                "jin-test",
                "test memo"
        )));
    }
}
