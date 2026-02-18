package com.example.consumer;

import com.acme.arc.dep.test.LowOneMain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests that verify the low-level-1 library API contract.
 * These tests run from the Gradle consumer against a Bazel-built JAR,
 * serving as an early warning system for breaking changes.
 *
 * BREAKING CHANGE DETECTION:
 * - Compile-time: If say() is renamed or removed, this file won't compile
 * - Runtime: If say() output changes, assertions will fail
 */
@DisplayName("LowOneMain Integration Tests")
class LowOneMainIntegrationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("say() should print 'Low-level-1'")
    void sayShouldPrintExpectedOutput() {
        LowOneMain.say();

        assertThat(outContent.toString().trim()).isEqualTo("Low-level-1");
    }

    @Test
    @DisplayName("main() should invoke say()")
    void mainShouldInvokeSay() {
        LowOneMain.main(new String[]{});

        assertThat(outContent.toString().trim()).isEqualTo("Low-level-1");
    }

    @Test
    @DisplayName("say() should be callable multiple times")
    void sayShouldBeCallableMultipleTimes() {
        LowOneMain.say();
        LowOneMain.say();

        String output = outContent.toString();
        assertThat(output).contains("Low-level-1");
        // Two calls produce two lines
        assertThat(output.trim().split("\n")).hasSize(2);
    }
}
