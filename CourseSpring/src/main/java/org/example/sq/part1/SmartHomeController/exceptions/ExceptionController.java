package org.example.sq.part1.SmartHomeController.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> response(DataAccessException  ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header("Cause", String.valueOf(ex.getCause()))
                .body("Can't access data." + "\n" + ex.getMessage());
    }
}
