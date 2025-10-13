package org.Task2;

import com.google.common.collect.Lists;
import org.Task2.Utils.WrongGradeProvider;
import org.junit.jupiter.api.*;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.FieldSource;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class Tests {
    private WireMockServer wireMockServer;
    private Student student;
    private ArrayList<Integer> referenceGradesList = Lists.newArrayList(2,3,3,4,2);
    private static ArrayList<Integer> validGrades = Lists.newArrayList(2,3,4,5);

    @BeforeEach
    public void beforeEach(){
        wireMockServer = new WireMockServer(5352);
        wireMockServer.stubFor(get(urlPathEqualTo("/checkGrade"))
                .withQueryParam("grade", matching("[2-5]"))
                .willReturn(aResponse().withStatus(200).withBody("true")));
        wireMockServer.stubFor(get(urlPathEqualTo("/checkGrade"))
                .withQueryParam("grade", matching("[^2-5]"))
                .willReturn(aResponse().withStatus(404).withBody("false")));
        wireMockServer.start();

        student = new Student("Ivan");
    }
    @AfterEach
    public void teardown(){
        wireMockServer.stop();
    }

    @FieldSource("validGrades")
    @ParameterizedTest
    public void addGradeWithEmptyListPositiveTest(int grade){
        student.addGrade(grade);
        Assertions.assertEquals(grade, student.getGrades().get(0));
    }

    @CsvFileSource(resources = "/task2-test-data.csv")
    @ParameterizedTest
    public void addGradeWithExistingListPositiveTest(int grade){
        referenceGradesList.stream().forEach(integer -> student.addGrade(integer));

        student.addGrade(grade);
        referenceGradesList.add(grade);
        Assertions.assertEquals(referenceGradesList, student.getGrades());
    }

    @ArgumentsSource(WrongGradeProvider.class)
    @ParameterizedTest
    public void addGradeWithEmptyListNegativeTest(int grade){
        Assertions.assertThrows(IllegalArgumentException.class, () -> student.addGrade(777));
        Assertions.assertTrue(student.getGrades().isEmpty());
    }

    @Test
    public void addGradeWithExistingListNegativeTest(){
        referenceGradesList.stream().forEach(integer -> student.addGrade(integer));

        Assertions.assertThrows(IllegalArgumentException.class, () -> student.addGrade(777));
        Assertions.assertEquals(referenceGradesList, student.getGrades());
    }
}
