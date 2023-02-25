package com.definexpracticum.loanapplicationsystem.exception;

import java.util.Date;
import java.util.List;

public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;
    private List<String> errors;

    public ErrorDetails(Date timestamp, String message, String details, List<String> errors) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.errors = errors;

    }

    public List<String> getErrors() {
        return errors;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
