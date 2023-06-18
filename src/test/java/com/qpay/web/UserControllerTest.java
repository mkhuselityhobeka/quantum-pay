package com.qpay.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qpay.dto.RolesDTO;
import com.qpay.dto.UserDTO;
import io.restassured.http.ContentType;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class UserControllerTest {



    ObjectMapper objectMapper =  new ObjectMapper();
     List<RolesDTO>rolesDTOS;
     UserDTO dtoUser;
     RolesDTO dtoRoles;

    @BeforeEach
    void setUp() {

        dtoRoles  = RolesDTO.builder().name("tester").description("Testing").build();
        rolesDTOS  = List.of(dtoRoles);

        dtoUser = UserDTO.builder().firstname("Mkhuseli").lastname("Tyhobeka").username("mkhuselityhobeka@standardbank.co.za")
                .password("mkhuselityhobeka@88").lastlogon("20230810").idNumber("8454564654564").email("mkhuselityhobeka@standardbank.co.za")
                .status("active").rolesDTO(rolesDTOS).build();
    }

    @Test
    void createUser() throws JsonProcessingException {

       String jsonBody = objectMapper.writeValueAsString(dtoUser);
        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .log()
                .all()
                .when()
                .post("http://localhost:8080/qpay/v1/create/user")
                .then()
                .assertThat()
                .statusCode(201).extract().response();
    }

    @Test
    void disableAccount() throws JsonProcessingException {


        String jsonBody = objectMapper.writeValueAsString(dtoUser);
        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .log()
                .all()
                .when()
                .post("http://localhost:8080/qpay/v1/create/user");

        dtoUser.setStatus("disabled");
         jsonBody = objectMapper.writeValueAsString(dtoUser);
        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .put("http://localhost:8080/qpay/v1/disable/user")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

    }

    @Test
    void enableAccount() throws JsonProcessingException {

        dtoUser.setStatus("disabled");
        String jsonBody = objectMapper.writeValueAsString(dtoUser);
        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .log()
                .all()
                .when()
                .post("http://localhost:8080/qpay/v1/create/user");

        dtoUser.setStatus("active");
        jsonBody = objectMapper.writeValueAsString(dtoUser);
        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .put("http://localhost:8080/qpay/v1/enable/user")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
    }


    @Test
    void getUser() throws JsonProcessingException {

        String jsonBody = objectMapper.writeValueAsString(dtoUser);
        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .log()
                .all()
                .when()
                .post("http://localhost:8080/qpay/v1/create/user");

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8080/qpay/v1/get/user/mkhuselityhobeka@standardbank.co.za")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
    }

    @Test
    void getAllUserAccounts() throws JsonProcessingException {

        String jsonBody = objectMapper.writeValueAsString(dtoUser);
        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .log()
                .all()
                .when()
                .post("http://localhost:8080/qpay/v1/create/user");

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8080/qpay/v1/get/users")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
    }
}