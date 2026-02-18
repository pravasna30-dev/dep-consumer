package com.example.consumer;

import com.acme.arc.dep.test.LowOneMain;

/**
 * Consumer application that depends on the Bazel-built low-level-1 library.
 * Demonstrates cross-build-system composite build: Gradle consumer → Bazel producer.
 */
public class Application {

    public static void main(String[] args) {
        System.out.println("Consumer calling low-level-1 library:");
        LowOneMain.say();
    }
}
