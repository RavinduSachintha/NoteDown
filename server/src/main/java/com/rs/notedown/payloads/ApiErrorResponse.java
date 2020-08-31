package com.rs.notedown.payloads;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SuppressWarnings({"UnusedDeclaration"})
public class ApiErrorResponse extends ResponseEntity<Object> {

    public ApiErrorResponse(Object body, HttpStatus status) {
        super(body, status);
    }
}
