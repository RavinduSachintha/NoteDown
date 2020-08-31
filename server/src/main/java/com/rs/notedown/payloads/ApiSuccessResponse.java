package com.rs.notedown.payloads;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SuppressWarnings({"UnusedDeclaration"})
public class ApiSuccessResponse extends ResponseEntity<Object> {
    public ApiSuccessResponse(Object body) {
        super(body, HttpStatus.OK);
    }
}
