package com.hprtech.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;


import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentResourceTest {

    @Order(1)
    @Test
    @TestSecurity(authorizationEnabled = false)
    void addStudent() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("name", "Elon Musk")
                .add("branch", "CS")
                .build();

        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .when()
                .post("addStudent")
                .then()
                .log().all()
                .statusCode(Response.Status.CREATED.getStatusCode());
    }

    @Order(2)
    @Test
    @TestSecurity(user = "testUser",roles = "teacher")
    void getStudentById() {
        RestAssured.given()
                .when()
                .get("student/6")
                .then()
                .log().all()
                .body("studentId", equalTo(6))
                .body("name", equalTo("Elon Musk"))
                .body("branch", equalTo("CS"));
    }

    @Order(3)
    @Test
    @TestSecurity(user = "testUser",roles = "admin")
    void getStudentList() {
        RestAssured.given()
                .when()
                .get("getAllStudent")
                .then()
                .log().all()
                .body("size()", equalTo(6));
    }
}