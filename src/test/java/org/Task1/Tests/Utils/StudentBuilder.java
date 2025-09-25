package org.Task1.Tests.Utils;

import org.Task1.Student;

import java.util.List;

public class StudentBuilder {
    private String name;
    private List<Integer> grades;

    public void setName(String name) {
        this.name = name;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    public Student build(){
        Student result = new Student(name);
        grades.forEach(grade -> result.addGrade(grade));

        return result;
    }
}
