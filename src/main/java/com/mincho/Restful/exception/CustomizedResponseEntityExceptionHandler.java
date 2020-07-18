package com.mincho.Restful.exception;

import com.mincho.Restful.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//When Error occured, all errors are requried on this logic
@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
        //ResponseEntity is similar with ResponseBody => exchange to XML JSON
        public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
            ExceptionResponse exceptionResponse =
                    new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));
            return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    //if user is not found.
    //this object if from User directory.
    @ExceptionHandler(UserNotFoundException.class)
    //ResponseEntity is similar with ResponseBody => exchange to XML JSON
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @Override //ResponseEntity -> transfer / but make class + transfer
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                "Validation Failed",ex.getBindingResult().toString());
        //make responseEntity<> transfer exceptionResponse
        return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
}

//@ResponseBody나 ResponseEntity를 return 하는거나 결과적으로는 같은 기능이지만..그 구현 방법이 틀리죠..
// 예를 들어 header 값을 변경시켜야 할 경우엔 @ResponseBody의 경우 파라미터로 Response 객체를 받아서 이 객체에서
// header를 변경시켜야 하고.
// .ResponseEntity에서는 이 클래스 객체를 생성한뒤 객체에서 header 값을 변경시키면 되죠.