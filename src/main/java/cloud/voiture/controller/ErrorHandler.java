package cloud.voiture.controller;

import java.util.Set;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cloud.voiture.model.ResponseWrap;
import jakarta.validation.ConstraintViolation;

@ControllerAdvice
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrap> handleException(Exception ex) {
        String errorMessage = ex.getMessage();
        if(ex instanceof ConstraintViolationException){
            errorMessage = ((ConstraintViolationException)ex).getErrorMessage();
            System.out.println("makato ve? "+errorMessage);
        }
        ResponseWrap response = ResponseWrap.error(errorMessage);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}