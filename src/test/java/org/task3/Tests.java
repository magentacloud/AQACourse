package org.task3;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.task3.api.students.responses.StudentGetResponse;
import org.task3.api.students.StudentsRepository;
import org.task3.api.students.entity.StudentDTO;
import org.task3.api.students.responses.StudentPostResponse;
import org.task3.api.students.responses.TopStudentResponse;

import java.util.ArrayList;
import java.util.List;

public class Tests {
    ArrayList<Integer> createdIDs;
    Faker faker = new Faker();

    @BeforeEach
    public void beforeEach(){
        createdIDs = new ArrayList<>();
    }

    @AfterEach
    public void afterEach(){
        createdIDs.forEach(this::deleteStudent);
    }

    //Проверяем требования 1, 3, 7 из задания #1. API Тесты. Сервис «Студент»
    @Test
    public void studentLifecycleTest(){
        int id = faker.number().randomDigit();
        String name = faker.name().firstName();
        List<Integer> marks = List.of(2, 3, 2, 3);

        StudentDTO student = new StudentDTO();
        student.setId(id);
        student.setName(name);
        student.setMarks(marks);

        StudentPostResponse postResponse = StudentsRepository.studentsApi.addStudent(student);
        Assertions.assertEquals(201, postResponse.getStatusCode(), "Неверный код статуса при создании");

        StudentGetResponse response = StudentsRepository.studentsApi.getStudent(id);

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertNotNull(response.getStudentDTO());
        Assertions.assertEquals(student.getName(), response.getStudentDTO().getName());

        int statusCode = StudentsRepository.studentsApi.deleteStudent(id);
        Assertions.assertEquals(200, statusCode, "Неверный код статуса при удалении");
    }

    //Проверяем требование 2
    @Test
    public void studentNotFoundTest(){
        StudentGetResponse response = StudentsRepository.studentsApi.getStudent(111);

        Assertions.assertNull(response.getStudentDTO());
        Assertions.assertEquals(404, response.getStatusCode());
    }

    //Проверяем требование 4
    @Test
    public void updateStudentWithSameIdTest(){
        int id = faker.number().randomDigit();
        String name = faker.name().firstName();
        List<Integer> marks = List.of(2, 3, 2, 3);

        String name1 = faker.name().firstName();
        List<Integer> marks1 = List.of(5, 5, 5, 4);

        createStudent(id, name, marks);

        StudentDTO student1 = new StudentDTO();
        student1.setId(id);
        student1.setName(name1);
        student1.setMarks(marks1);

        StudentPostResponse postResponse = StudentsRepository.studentsApi.addStudent(student1);

        Assertions.assertEquals(201, postResponse.getStatusCode());

        StudentGetResponse getResponse = StudentsRepository.studentsApi.getStudent(id);

        Assertions.assertEquals(200, getResponse.getStatusCode());
        Assertions.assertEquals(id, getResponse.getStudentDTO().getId());
        Assertions.assertEquals(name1, getResponse.getStudentDTO().getName());
        Assertions.assertEquals(marks1, getResponse.getStudentDTO().getMarks());
    }

    //Проверяем требование 5
    @Test
    public void createStudentWithNullIDTest(){
        String name = faker.name().firstName();
        List<Integer> marks = List.of(2, 3, 2, 3);

        StudentDTO student = new StudentDTO();
        student.setName(name);
        student.setMarks(marks);

        StudentPostResponse response = StudentsRepository.studentsApi.addStudent(student);

        Assertions.assertEquals(201, response.getStatusCode());
        Assertions.assertNotNull(response.getCreatedID());

        StudentsRepository.studentsApi.deleteStudent(response.getCreatedID());
    }

    //Проверяем требование 6
    @Test
    public void createStudentWithNullNameTest(){
        int id = faker.number().randomDigit();
        List<Integer> marks = List.of(2, 3, 2, 3);

        StudentDTO student = new StudentDTO();
        student.setId(id);
        student.setName(null);
        student.setMarks(marks);

        StudentPostResponse response = StudentsRepository.studentsApi.addStudent(student);
        Assertions.assertEquals(400, response.getStatusCode());
    }

    //Проверяем требование 8
    @Test
    public void deleteNotExistingStudentTest(){
        int statusCode = StudentsRepository.studentsApi.deleteStudent(666);

        Assertions.assertEquals(404, statusCode);
    }

    //Проверяем требование 9
    @Test
    public void getTopStudentWithEmptyDBTest(){
        TopStudentResponse response = StudentsRepository.studentsApi.getTopStudent();

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertNull(response.getStudentDTOList());
    }

    //Проверяем требование 10
    @Test
    public void getTopStudentWithEmptyMarksTest(){
        int id = faker.number().randomDigit();
        String name = faker.name().firstName();

        int id1  = faker.number().randomDigit();
        String name1 = faker.name().firstName();

        createStudent(id, name, null);
        createStudent(id1, name1, null);

        TopStudentResponse response = StudentsRepository.studentsApi.getTopStudent();

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertNull(response.getStudentDTOList());
    }

    //Проверяем требование 11 получение студента с самым большим числом оценок с одинаковой средней
    @Test
    public void getTopStudentTest(){
        int id = faker.number().randomDigit();
        String name = faker.name().firstName();
        List<Integer> marks = List.of(5, 4, 5, 5);

        int id1 = faker.number().randomDigit();
        String name1 = faker.name().firstName();
        List<Integer> marks1 = List.of(2, 3, 2, 3);

        createStudent(id, name, marks);
        createStudent(id1, name1, marks1);

        TopStudentResponse response = StudentsRepository.studentsApi.getTopStudent();

        Assertions.assertEquals(200, response.getStatusCode());

        Assertions.assertEquals(id, response.getStudentDTOList().get(0).getId());
        Assertions.assertEquals(name, response.getStudentDTOList().get(0).getName());
        Assertions.assertEquals(marks, response.getStudentDTOList().get(0).getMarks());
    }

    //Проверяем требование 11(получение студента с самым большим числом оценок с одинаковой средней)
    @Test
    public void getTopStudentWithSimilarAverageTest(){
        int id = faker.number().randomDigit();
        String name = faker.name().firstName();
        List<Integer> marks = List.of(4, 4, 4, 4);

        int id1 = faker.number().randomDigit();
        String name1 = faker.name().firstName();
        List<Integer> marks1 = List.of(4, 4, 4, 4, 4);

        createStudent(id, name, marks);
        createStudent(id1, name1, marks1);

        TopStudentResponse response = StudentsRepository.studentsApi.getTopStudent();

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(id1, response.getStudentDTOList().get(0).getId());
        Assertions.assertEquals(name1, response.getStudentDTOList().get(0).getName());
        Assertions.assertEquals(marks1, response.getStudentDTOList().get(0).getMarks());
    }

    //Проверяем требование 12
    @Test
    public void getMultipleTopStudentsTest(){
        int id = faker.number().randomDigit();
        String name = faker.name().firstName();
        List<Integer> marks = List.of(4, 4, 4, 4);

        int id1 = faker.number().randomDigit();
        String name1 = faker.name().firstName();
        List<Integer> marks1 = List.of(2, 3, 2, 3, 2);

        int id2 = faker.number().randomDigit();
        String name2 = faker.name().firstName();
        List<Integer> marks2 = List.of(4, 4, 4, 4);

        createStudent(id, name, marks);
        createStudent(id1, name1, marks1);
        createStudent(id2, name2, marks2);

        TopStudentResponse response = StudentsRepository.studentsApi.getTopStudent();

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(2, response.getStudentDTOList().size());
    }

    private void createStudent(Integer id, String name, List<Integer> marks){
        Integer resultId = id;

        StudentDTO student = new StudentDTO();
        student.setId(id);
        student.setName(name);
        student.setMarks(marks);

        StudentPostResponse postResponse = StudentsRepository.studentsApi.addStudent(student);

        if(resultId == null){
            resultId = postResponse.getCreatedID();
        }

        this.createdIDs.add(resultId);
    }

    private void deleteStudent(int id){
        StudentsRepository.studentsApi.deleteStudent(id);
    }
}