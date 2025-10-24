package org.task3.api;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import org.task3.env.config.ServerConfig;

import java.util.List;

@RequiredArgsConstructor
public class _BaseApi {
    protected final ServerConfig CONFIG;

    protected RequestSpecification jsonAutoAuth(){
        return buildRequest(ContentType.JSON);
    }

    protected RequestSpecification buildRequest(ContentType contentType){
        return RestAssured.given()
                .log().all()
                .config(createConfig())
                .relaxedHTTPSValidation()
                .baseUri(CONFIG.serverUrl())
                .contentType(contentType)
                .filters(getFilters());
    }

    private RestAssuredConfig createConfig(){
        return RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 5000));
    }

    private List<Filter> getFilters(){
        return List.of(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter()
        );
    }
}
