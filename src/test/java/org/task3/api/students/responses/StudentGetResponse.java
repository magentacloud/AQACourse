package org.task3.api.students.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.task3.api.students.entity.StudentDTO;

@Getter
public class StudentGetResponse extends StudentBaseResponse{
    private final StudentDTO studentDTO;

    public StudentGetResponse(StudentDTO studentDTO,int responseCode) {
        super(responseCode);
        this.studentDTO = studentDTO;
    }
}
