package com.app.exception.advice;

import com.app.exception.BadRequestException;
import com.app.exception.InternalServerException;
import com.app.rest.response.ResponseMessage;
import com.app.service.IStorageService;
import com.monitorjbl.xlsx.exceptions.MissingSheetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionAdvice {

    @Autowired
    private IStorageService storageService;

    @SuppressWarnings("unused")
    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<ResponseMessage> handle(BadRequestException e, HttpServletResponse response) {
        storageService.delete(response.getHeader("fileName"));
        return ResponseEntity.badRequest().body(new ResponseMessage(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(value = {MissingSheetException.class})
    public ResponseEntity<ResponseMessage> handle(MissingSheetException e, HttpServletResponse response) {
        storageService.delete(response.getHeader("fileName"));
        return ResponseEntity.badRequest().body(new ResponseMessage(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(value = {InternalServerException.class})
    public ResponseEntity<ResponseMessage> handle(InternalServerException e, HttpServletResponse response) {
        storageService.delete(response.getHeader("fileName"));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }

}
