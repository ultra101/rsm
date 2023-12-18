package org.example.task3.api;

public enum Status {
    SUCCESS("Success"),
    FAILURE("Failure");

    private String value;
    private Status(String value) {
        this.value = value;
    }
}
