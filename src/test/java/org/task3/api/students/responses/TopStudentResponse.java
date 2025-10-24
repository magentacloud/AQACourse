package org.task3.api.students.responses;

import lombok.Getter;
import org.task3.api.students.entity.StudentDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TopStudentResponse extends StudentBaseResponse{
    public TopStudentResponse(List<StudentDTO> studentDTOList, int statusCode) {
        super(statusCode);
        this.studentDTOList = studentDTOList;
    }

    private final List<StudentDTO> studentDTOList;

}
