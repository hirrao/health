package com.hirrao.health.integration;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class HealthAdviceTest {
    @Autowired
    private MockMvc mockMvc;

    @Nested
    class getByIdTest {
        @Test
        public void getByIdSuccess() throws Exception {
            mockMvc.perform(
                           get("/health-advice/1000000000000000001").contentType(
                                   "application/json"))
                   .andExpectAll(status().isOk(),
                                 content().contentType("application/json"),
                                 jsonPath("$.data.authorName").value("hirrao"));
        }
    }

}
