package com.teachmeskills.lesson_23.exception;

public class BookProcessingException extends Exception {
    public enum ErrorType {
        FILE_NOT_FOUND("The file was not found!"),
        PARSING_ERROR("Parsing error!"),
        CONVERSION_ERROR("Conversion error!"),
        VALIDATION_ERROR("Data validation error!"),
        UNKNOWN_ERROR("Unknown error!");

        private final String description;

        ErrorType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    private final ErrorType errorType;

    public BookProcessingException(ErrorType errorType) {
        super(errorType.getDescription());
        this.errorType = errorType;
    }

    public BookProcessingException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public BookProcessingException(ErrorType errorType, Throwable cause) {
        super(cause);
        this.errorType = errorType;
    }

    public BookProcessingException(ErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    @Override
    public String toString() {
        return "BookProcessingException{" +
                "errorType=" + errorType +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}