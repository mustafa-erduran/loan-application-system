package com.definexpracticum.loanapplicationsystem.exception;

import java.util.Date;
import java.util.List;

public record ErrorDetails(Date timestamp, String message, String details,
                           List<String> errors) {

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
