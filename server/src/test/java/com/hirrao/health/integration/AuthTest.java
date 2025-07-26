package com.hirrao.health.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hirrao.health.common.request.RegisterRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    class RegisterTest {
        @Test
        public void registerSuccess() throws Exception {
            var request = new RegisterRequest("test_user0001",
                                              "test0001@test.com",
                                              "testPassword");
            AuthTest.this.mockMvc.perform(
                                 post("/auth/register").contentType("application/json")
                                                       .content(
                                                               objectMapper.writeValueAsBytes(
                                                                       request)))
                                 .andExpect(status().isOk());
        }
    }
}
