package com.example.junitrestapiapplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RestApiRunnerTests {

    @Test
    void demoTestMethod() {
        assertTrue(true);
    }

}
