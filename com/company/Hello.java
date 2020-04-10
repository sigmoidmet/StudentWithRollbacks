package com.company;

import com.company.studentGrades.Student;
import com.company.studentGrades.exceptions.GradeValidationFailed;
import com.company.studentGrades.exceptions.RollbackException;
import com.company.studentGrades.rollback.IRollback;
import com.company.studentGrades.rollback.SavingStateRollback;

public class Hello {

    public static void main(String[] args) throws GradeValidationFailed, RollbackException {
        Student<Integer> student = new Student<>("Mark Twen");
        IRollback rollback = new SavingStateRollback();
        student.putGrade(1);
        student.putGrade(5);
        rollback.saveState(student.getCurrentState());
        student.putGrade(4);

        printStudentGrades(student);

        rollback.saveCurrentStateAndDoRollback(student.getCurrentState());

        printStudentGrades(student);

        rollback.saveCurrentStateAndCancelLastRollback(student.getCurrentState());

        printStudentGrades(student);
    }

    private static <T> void printStudentGrades(Student<T> student) {
        for (int i = 0; i < student.gradesCount(); ++i) {
            System.out.println(student.getGrade(i));
        }
        System.out.println();
    }
}
