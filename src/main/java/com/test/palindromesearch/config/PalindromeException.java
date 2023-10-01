package com.test.palindromesearch.config;

import com.test.palindromesearch.service.PalindromeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class PalindromeException extends RuntimeException {

    private HttpStatus httpStatus;

    private String message;

    Logger logger = LoggerFactory.getLogger(PalindromeException.class);


    public PalindromeException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
        logger.error(message);
    }

    public PalindromeException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
        logger.error(message);

    }



    public PalindromeException(Throwable cause, HttpStatus httpStatus, String message) {
        super(cause);
        this.httpStatus = httpStatus;
        this.message = message;
        logger.error(message);

    }

    public PalindromeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus httpStatus, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.httpStatus = httpStatus;
        this.message = message1;
        logger.error(message);

    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
