package com.company.studentGrades.exceptions;

public class RollbackException extends Throwable {
    public RollbackException(String message, Exception e) {
        super(message, e);
    }

    public RollbackException(String message) {
        super(message);
    }
}
