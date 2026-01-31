package com.sevasaathi.platform;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Integration tests for SevaSaathi Application.
 * 
 * This test class verifies that the Spring Boot application context
 * loads successfully with all configurations.
 */
@SpringBootTest
@ActiveProfiles("test")
class SevaSaathiApplicationTests {

    @Test
    void contextLoads() {
        // This test verifies that the Spring application context loads successfully
        // If the context fails to load, this test will fail
    }
}