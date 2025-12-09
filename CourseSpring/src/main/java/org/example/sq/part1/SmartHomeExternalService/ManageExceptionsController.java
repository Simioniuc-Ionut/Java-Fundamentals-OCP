package org.example.sq.part1.SmartHomeExternalService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManageExceptionsController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> response(Exception ex) {

        String body =
                "Can't save account. Try again later." +
                ex.getMessage();

        return ResponseEntity.badRequest()
                .body(body);
    }
}
