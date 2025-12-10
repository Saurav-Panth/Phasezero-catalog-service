package com.phasezero.code.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.phasezero.code.dto.ExceptionStructure;
import com.phasezero.code.enums.Category;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ExceptionStructure<String>> handleIDNFE(IdNotFoundException exception) {
        ExceptionStructure<String> response = new ExceptionStructure<>();
        response.setData(exception.getMessage());
        response.setStausCode(HttpStatus.NOT_FOUND.value());
        response.setMessage("ID NOT FOUND");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicatePartNumberException.class)
    public ResponseEntity<ExceptionStructure<String>> handleDPNE(DuplicatePartNumberException exception) {
        ExceptionStructure<String> response = new ExceptionStructure<>();
        response.setData(exception.getMessage());
        response.setStausCode(HttpStatus.CONFLICT.value());
        response.setMessage("DUPLICATE PART NUMBER");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NegativePriceException.class)
    public ResponseEntity<ExceptionStructure<String>> handleNPE(NegativePriceException exception) {
        ExceptionStructure<String> response = new ExceptionStructure<>();
        response.setData(exception.getMessage());
        response.setStausCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("NEGATIVE PRICE NOT ALLOWED");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NegativeStockException.class)
    public ResponseEntity<ExceptionStructure<String>> handleNSE(NegativeStockException exception) {
        ExceptionStructure<String> response = new ExceptionStructure<>();
        response.setData(exception.getMessage());
        response.setStausCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("NEGATIVE STOCK NOT ALLOWED");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoRecordException.class)
    public ResponseEntity<ExceptionStructure<String>> handleNRE(NoRecordException exception) {
        ExceptionStructure<String> response = new ExceptionStructure<>();
        response.setData(exception.getMessage());
        response.setStausCode(HttpStatus.NOT_FOUND.value());
        response.setMessage("NO RECORD FOUND");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PartNameNotFoundException.class)
    public ResponseEntity<ExceptionStructure<String>> handlePNNFE(PartNameNotFoundException exception) {
        ExceptionStructure<String> response = new ExceptionStructure<>();
        response.setData(exception.getMessage());
        response.setStausCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("PART NAME IS REQUIRED");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionStructure<String>> handleWCE(HttpMessageNotReadableException exception) {

        String allowedCategories = Arrays.toString(Category.values());

        ExceptionStructure<String> response = new ExceptionStructure<>();
        response.setData("Invalid category. Allowed values: " + allowedCategories);
        response.setStausCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("INVALID CATEGORY");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    
    @ExceptionHandler(MissingFieldException.class)
    public ResponseEntity<ExceptionStructure<String>> handleMPNFE(MissingFieldException exception) {
        ExceptionStructure<String> response = new ExceptionStructure<>();
        response.setData(exception.getMessage());
        response.setStausCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Missing Field");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
