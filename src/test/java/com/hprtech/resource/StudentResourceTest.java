package com.hprtech.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentResourceTest {

    @Order(1)
    @Test
    void addStudent() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("StudentId", 10L)
                .add("name", "Elon Musk")
                .add("branch", "CS")
                .build();

        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .when()
                .post("addStudent")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode());
    }

    @Order(2)
    @Test
    void getStudentById() {
        RestAssured.given()
                .when()
                .get("student/10")
                .then()
                .body("StudentId", equalTo(10))
                .body("name", equalTo("Elon Musk"))
                .body("branch", equalTo("CS"));
    }

    @Order(3)
    @Test
    void getStudentList() {
        RestAssured.given()
                .when()
                .get("getAllStudent")
                .then()
                .body("size()", equalTo(6));
    }
}