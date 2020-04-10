package com.company.studentGrades;

import com.company.studentGrades.exceptions.GradeValidationFailed;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class Student<T> {
    private final List<T> grades;
    private String name;
    private Predicate<T> gradesValidator = (x) -> true;


    public Student(String name) {
        grades = new ArrayList<>();
        this.name = name;
    }

    public void setValidator(Predicate<T> gradesValidator) {
        this.gradesValidator = gradesValidator;
    }

    public int gradesCount() {
        return grades.size();
    }

    public T getGrade(int position) {
        return grades.get(position);
    }

    public void putGrade(@NotNull T grade) throws GradeValidationFailed {
        validate(grade);
        grades.add(grade);
    }

    public void putGrade(int index, @NotNull T grade) throws GradeValidationFailed {
        validate(grade);
        grades.add(index, grade);
    }


    public void changeGrade(int index, @NotNull T newGrade) throws GradeValidationFailed {
        validate(newGrade);
        T oldGrade = grades.get(index);
        grades.set(index, newGrade);
    }

    private void validate(T grade) throws GradeValidationFailed {
        if (!gradesValidator.test(grade))
            throw new GradeValidationFailed( "This object can't contain " + grade.toString());
    }

    public T removeGrade(int index) {
        return grades.remove(index);
    }

    public State getCurrentState() {
        return new StudentState();
    }

    private class StudentState implements State {
        private final List<T> gradesState;
        private final String nameState;

        private StudentState() {
            this.gradesState = new ArrayList<>(grades);
            this.nameState = name;
        }

        @Override
        public void doRollback() {
            grades.clear();
            grades.addAll(gradesState);
            name = nameState;
        }
    }

}
