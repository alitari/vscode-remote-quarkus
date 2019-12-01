package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ExampleResourceTest {

    @Test
    public void helloEndpoint() {
        given()
        .when()
            .get("/hello")
        .then()
            .statusCode(200)
            .body(is("hello\n"));
    }

    @Test
    public void countryEndpoint() {
        given()
        .when()
            .get("/hello/country/Germany")
        .then()
            .statusCode(200)
            .body(is("[{\"name\":\"Germany\",\"alpha2Code\":null,\"capital\":null,\"currencies\":null}]"));
    }

}