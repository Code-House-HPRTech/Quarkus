package com.hprtech.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Tag("integration")
class StudentResourceTest {

    @Test
    void getStudentList() {
        RestAssured
                .given()
                .when()
                .get("/getStudentList")
                .then()
                .body("size()", equalTo(4))
                .body("name", hasItems("Rahul","Mohit"))
                .body("branch",hasItems("CS","EE"));

    }

    @Test
    void getCSStudentList() {
        RestAssured
                .given()
                .when()
                .get("/getCSStudentList")
                .then()
                .body("size()", equalTo(2))
                .body("name", hasItems("Rahul","Aakanksha"));
    }
}