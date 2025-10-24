package org.task3.api.students;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.task3.api._BaseApi;
import org.task3.api.students.entity.StudentDTO;
import org.task3.api.students.responses.*;
import org.task3.env.Env;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StudentsApi extends _BaseApi {
    public StudentsApi() {
        super(Env.API.SERVER_CONFIG);
    }

    public StudentGetResponse getStudent(int id){
        StudentDTO studentDTO;

        log.info("Получаем студента с ID=" + id);

        String URI = "/student/" + id;

        Response response = jsonAutoAuth().basePath(URI).get();

        try {
            studentDTO = response.as(StudentDTO.class);
        } catch (IllegalStateException e) {
            log.info("Не удалось преобразовать ответ");
            studentDTO = null;
        }

        return new StudentGetResponse(studentDTO, response.statusCode());
    }

    public StudentPostResponse addStudent(StudentDTO studentDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        String payload;

        log.info("Создаём студента с именем " + studentDTO.getName() + "и ID " + studentDTO.getId());

        String URI = "/student";

        try {
            payload = objectMapper.writeValueAsString(studentDTO);
        } catch (JsonProcessingException e) {
            log.error("Не удалось преобразовать объект студента в JSON");
            throw new RuntimeException(e);
        }

        Response response = jsonAutoAuth().basePath(URI).body(payload).post();
        Integer idFromResponse;

        try {
            idFromResponse = response.as(Integer.class);
        }catch (IllegalStateException exception){
            idFromResponse = null;
        }

        return new StudentPostResponse(response.statusCode(), idFromResponse);
    }

    public int deleteStudent(int id){
        log.info("Удаляем студента с ID=" + id);

        String URI = "/student/" + id;

        Response response = jsonAutoAuth().basePath(URI).delete();

        return response.statusCode();
    }

    public TopStudentResponse getTopStudent(){
        String URI = "/topStudent/";
        List<StudentDTO> studentDTOList;

        log.info("Получаем лучшего студента");

        Response response = jsonAutoAuth().basePath(URI).get();

        try {
            studentDTOList = List.of(response.as(StudentDTO[].class));
        } catch (IllegalStateException e) {
            log.info("Не удалось преобразовать ответ");
            studentDTOList = null;
        }

        return new TopStudentResponse(studentDTOList, response.statusCode());
    }
}
