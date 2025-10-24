package org.Task1.Tests;

import org.Task1.Student;
import org.Task1.Tests.Utils.StudentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Tests {
    private final String name = "Ivan";
    private final ArrayList<Integer> grades = new ArrayList<Integer>(Arrays.asList(new Integer[]{2, 3, 2}));
    private Student student;

    @BeforeEach
    public void setup(){
        StudentBuilder builder = new StudentBuilder();
        builder.setName(name);
        builder.setGrades(grades);
        this.student = builder.build();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    public void AddGradePositiveTest(int grade){
        student.addGrade(grade);
        grades.add(grade);

        Assertions.assertEquals(student.getGrades(), grades);
    }

    @Test
    public void AddGradeNegativeTest(){
        student.addGrade(4);
        grades.add(4);

        student.getGrades().add(5);
        student.getGrades().add(6);
        student.getGrades().add(31254);

        Assertions.assertEquals(student.getGrades(), grades);
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, 0, 1, 6, 7, Integer.MAX_VALUE})
    public void AddWrongGrade(Integer grade){
        Assertions.assertThrows(IllegalArgumentException.class, () -> student.addGrade(grade));
    }

    @Test
    public void ChangeNameTest(){
        String newName = "Vanya";
        student.setName(newName);

        Assertions.assertEquals(newName, student.getName());
    }

    @Test
    public void CheckStudentStringTest(){
        String stringViewOfStudent = "Student{" + "name=" + name + ", marks=" + grades + '}';

        Assertions.assertEquals(stringViewOfStudent, student.toString());
    }

    @Test
    public void CheckStudentsAreEqualTest(){
        Student student1 = student;

        Assertions.assertTrue(student.equals(student1));
    }

    @Test
    public void CheckStudentsAreNotEqualTest(){
        Student student1 = null;

        Assertions.assertFalse(student.equals(student1));
    }

    @Test
    public void CheckStudentsEqualNotSameClassTest(){
        String student1 = "null";

        Assertions.assertFalse(student.equals(student1));
    }

    @Test
    public void CheckDifferentStudentsIsNotEqual(){
        StudentBuilder builder = new StudentBuilder();
        builder.setName("Maria");
        builder.setGrades(new ArrayList<Integer>(Arrays.asList(new Integer[]{5, 5, 5, 5})));
        Student student1 = builder.build();

        Assertions.assertFalse(student.equals(student1));
    }

    @Test
    public void CheckDifferentStudentsWithSameNameIsNotEqual(){
        StudentBuilder builder = new StudentBuilder();
        builder.setName("Ivan");
        builder.setGrades(new ArrayList<Integer>(Arrays.asList(5, 5, 3, 4)));
        Student student1 = builder.build();

        Assertions.assertFalse(student.equals(student1));
    }

    @Test
    public void CheckStudentsHashTest(){
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(student.getName());
        hash = 13 * hash + Objects.hashCode(student.getGrades());

        Assertions.assertEquals(hash, student.hashCode());
    }
}
