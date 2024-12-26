package com.alex_d_bondarev.hello_spring.contacts.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.time.LocalDateTime;

public class ErrorResponse {

    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp2;

    public ErrorResponse(String message) {
        this.timestamp2 = LocalDateTime.now();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp2() {
        return timestamp2;
    }

    public void setTimestamp2(LocalDateTime timestamp2) {
        this.timestamp2 = timestamp2;
    }
}
