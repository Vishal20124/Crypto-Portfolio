package com.bridgelabz.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CustomExceptions {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException implements Serializable {
        private static final long serialVersionUID = 1L;
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class BadRequestException extends RuntimeException implements Serializable {
        private static final long serialVersionUID = 1L;
        public BadRequestException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class ApiException extends RuntimeException implements Serializable {
        private static final long serialVersionUID = 1L;
        public ApiException(String message) {
            super(message);
        }
    }
}
