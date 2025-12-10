package com.phasezero.code.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.phasezero.code.dto.ExceptionStrucutre;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ExceptionStrucutre<String>> handleIDNFE(IdNotFoundException exception) {
        ExceptionStrucutre<String> response = new ExceptionStrucutre<>();
        response.setData(exception.getMessage());
        response.setStausCode(HttpStatus.NOT_FOUND.value());
        response.setMessage("ID NOT FOUND");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicatePartNumberException.class)
    public ResponseEntity<ExceptionStrucutre<String>> handleDPNE(DuplicatePartNumberException exception) {
        ExceptionStrucutre<String> response = new ExceptionStrucutre<>();
        response.setData(exception.getMessage());
        response.setStausCode(HttpStatus.CONFLICT.value());
        response.setMessage("DUPLICATE PART NUMBER");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NegativePriceException.class)
    public ResponseEntity<ExceptionStrucutre<String>> handleNPE(NegativePriceException exception) {
        ExceptionStrucutre<String> response = new ExceptionStrucutre<>();
        response.setData(exception.getMessage());
        response.setStausCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("NEGATIVE PRICE NOT ALLOWED");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NegativeStockException.class)
    public ResponseEntity<ExceptionStrucutre<String>> handleNSE(NegativeStockException exception) {
        ExceptionStrucutre<String> response = new ExceptionStrucutre<>();
        response.setData(exception.getMessage());
        response.setStausCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("NEGATIVE STOCK NOT ALLOWED");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoRecordException.class)
    public ResponseEntity<ExceptionStrucutre<String>> handleNRE(NoRecordException exception) {
        ExceptionStrucutre<String> response = new ExceptionStrucutre<>();
        response.setData(exception.getMessage());
        response.setStausCode(HttpStatus.NOT_FOUND.value());
        response.setMessage("NO RECORD FOUND");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PartNameNotFoundException.class)
    public ResponseEntity<ExceptionStrucutre<String>> handlePNNFE(PartNameNotFoundException exception) {
        ExceptionStrucutre<String> response = new ExceptionStrucutre<>();
        response.setData(exception.getMessage());
        response.setStausCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("PART NAME IS REQUIRED");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<ExceptionStrucutre<String>> handleBNFE(BrandNotFoundException exception) {
        ExceptionStrucutre<String> response = new ExceptionStrucutre<>();
        response.setData(exception.getMessage());
        response.setStausCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("BRAND IS REQUIRED");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongCategoryException.class)
    public ResponseEntity<ExceptionStrucutre<String>> handleWCE(WrongCategoryException exception) {
        ExceptionStrucutre<String> response = new ExceptionStrucutre<>();
        response.setData(exception.getMessage());
        response.setStausCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("INVALID CATEGORY");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
