package com.company.studentGrades.exceptions;

public class GradeValidationFailed extends Throwable {
    public GradeValidationFailed(String message) {
        super(message);
    }
}
