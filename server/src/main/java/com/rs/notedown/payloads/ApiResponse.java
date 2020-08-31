package com.rs.notedown.payloads;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SuppressWarnings({"UnusedDeclaration"})
public class ApiResponse extends ResponseEntity<Object> {
    public ApiResponse(Object body) {
        super(body, HttpStatus.OK);
    }
}
