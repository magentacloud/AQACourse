package org.Task2.Utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Stream;

public class WrongGradeProvider implements ArgumentsProvider {
    private int getWrongGrade(){
        return (int)(Math.random() * 1000 - 1500);
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(getWrongGrade()),
                Arguments.of(getWrongGrade()),
                Arguments.of(getWrongGrade()),
                Arguments.of(getWrongGrade())
        );
    }
}
