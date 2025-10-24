package org.task3.api.students.responses;

import lombok.Getter;

@Getter
public class StudentPostResponse extends StudentBaseResponse{
    private final Integer createdID;

    public StudentPostResponse(int responseCode, Integer createdID) {
        super(responseCode);
        this.createdID = createdID;
    }
}
