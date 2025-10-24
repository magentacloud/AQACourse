package org.task3.api.students.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class StudentBaseResponse {

    private final int statusCode;
}
