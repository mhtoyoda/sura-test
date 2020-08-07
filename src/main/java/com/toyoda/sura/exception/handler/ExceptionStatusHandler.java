package com.toyoda.sura.exception.handler;

import com.toyoda.sura.exception.PedidoErrorException;
import com.toyoda.sura.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionStatusHandler {

    @Autowired
    private MessageSource messageSource;

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        log.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(ResourceNotFoundException exception) {
        return error(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler({PedidoErrorException.class})
    public ResponseEntity<String> handleNotFoundException(PedidoErrorException exception) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

}
