package dd.projects.ddshop.controllers;

import dd.projects.ddshop.exceptions.EntityDoesNotExist;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityDoesNotExist.class})
    protected ResponseEntity<Object> entityDoesNotExist(RuntimeException runtimeException, WebRequest webRequest){
        String bodyOfResponse = runtimeException.getMessage();
        return handleExceptionInternal(runtimeException, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }
}
