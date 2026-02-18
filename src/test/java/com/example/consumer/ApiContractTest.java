package com.example.consumer;

import com.acme.arc.dep.test.LowOneMain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * API contract tests using reflection to verify exact method signatures.
 * These tests detect breaking changes even before runtime by checking
 * that the expected methods exist with the correct signatures.
 */
@DisplayName("LowOneMain API Contract Verification")
class ApiContractTest {

    @Test
    @DisplayName("LowOneMain should have public static void say() method")
    void verifySayMethodSignature() throws NoSuchMethodException {
        Method method = LowOneMain.class.getMethod("say");

        assertThat(method.getReturnType())
                .as("say() should return void")
                .isEqualTo(void.class);

        assertThat(Modifier.isStatic(method.getModifiers()))
                .as("say() should be static")
                .isTrue();

        assertThat(Modifier.isPublic(method.getModifiers()))
                .as("say() should be public")
                .isTrue();

        assertThat(method.getParameterCount())
                .as("say() should take no parameters")
                .isZero();
    }

    @Test
    @DisplayName("LowOneMain should have public static void main(String[]) method")
    void verifyMainMethodSignature() throws NoSuchMethodException {
        Method method = LowOneMain.class.getMethod("main", String[].class);

        assertThat(method.getReturnType())
                .as("main() should return void")
                .isEqualTo(void.class);

        assertThat(Modifier.isStatic(method.getModifiers()))
                .as("main() should be static")
                .isTrue();

        assertThat(Modifier.isPublic(method.getModifiers()))
                .as("main() should be public")
                .isTrue();
    }

    @Test
    @DisplayName("LowOneMain class should be in com.acme.arc.dep.test package")
    void verifyClassPackage() {
        assertThat(LowOneMain.class.getPackageName())
                .isEqualTo("com.acme.arc.dep.test");
    }

    @Test
    @DisplayName("LowOneMain class should be public")
    void verifyClassIsPublic() {
        assertThat(Modifier.isPublic(LowOneMain.class.getModifiers()))
                .as("LowOneMain should be a public class")
                .isTrue();
    }
}
