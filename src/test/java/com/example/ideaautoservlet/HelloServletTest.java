package com.example.ideaautoservlet;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;


class HelloServletTest {

    static void init() {
        baseURI = "http://localhost:8095/IdeaAutoServlet_war_exploded/users";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("firstName", "John");
        requestParams.put("lastName", "Snow");
        requestParams.put("birthDay", "2000-01-05");
        requestParams.put("company", "Nickelodeon");

        request.header("Content-Type", "application/json");
        request.body(requestParams.toString());
        request.post(baseURI);
        request.post(baseURI);
        request.post(baseURI);


    }

    @BeforeAll
    static void test() {
        init();
    }


    @Test
    void doPostPositive() {
        baseURI = "http://localhost:8095/IdeaAutoServlet_war_exploded/users";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("firstName", "John");
        requestParams.put("lastName", "Snow");
        requestParams.put("birthDay", "2000-01-05");
        requestParams.put("company", "Nickelodeon");
        request.header("Content-Type", "application/json");
        request.body(requestParams.toString());
        Response response = request.post(baseURI);
        Assertions.assertEquals("{\"id\":null,\"firstName\":\"John\",\"lastName\":\"Snow\",\"birthDay\":\"2000-01-05\",\"company\":\"Nickelodeon\"}", response.getBody().asString());
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void doPostNull() {
        baseURI = "http://localhost:8095/IdeaAutoServlet_war_exploded/users";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        request.header("Content-Type", "application/json");
        request.body(requestParams.toString());
        Response response = request.post(baseURI);
        Assertions.assertEquals("{\"id\":null,\"firstName\":null,\"lastName\":null,\"birthDay\":null,\"company\":null}", response.getBody().asString());
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void doPostNegative() {
        baseURI = "http://localhost:8095/IdeaAutoServlet_war_exploded/users";
        RequestSpecification request = RestAssured.given();
        Response response = request.post(baseURI);
        Assertions.assertEquals(500, response.statusCode());
    }

    @Test
    void doGetPositive() {
        baseURI = "http://localhost:8095/IdeaAutoServlet_war_exploded/users?id=";
        RequestSpecification request = RestAssured.given();
        Response response = request.get(baseURI + "0");
        Assertions.assertEquals("{\"id\":0,\"firstName\":\"John\",\"lastName\":\"Snow\",\"birthDay\":\"2000-01-05\",\"company\":\"Nickelodeon\"}", response.getBody().asString());
        Assertions.assertEquals(200, response.statusCode());

    }

    @Test
    void doGetNull() {
        baseURI = "http://localhost:8095/IdeaAutoServlet_war_exploded/users?id=";
        RequestSpecification request = RestAssured.given();
        Response response = request.get(baseURI + "null");
        Assertions.assertEquals(500, response.statusCode());
    }

    @Test
    void doGetNegative() {
        baseURI = "http://localhost:8095/IdeaAutoServlet_war_exploded/users?id=";
        RequestSpecification request = RestAssured.given();
        Response response = request.get(baseURI + "9999");
        Assertions.assertEquals(404, response.statusCode());
    }

    @Test
    void doPutPositive() {
        baseURI = "http://localhost:8095/IdeaAutoServlet_war_exploded/users?id=1&company=";
        RequestSpecification request = RestAssured.given();
        Response response = request.put(baseURI + "JamesCo");
        Assertions.assertEquals("{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Snow\",\"birthDay\":\"2000-01-05\",\"company\":\"JamesCo\"}", response.getBody().asString());
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void doPutNull() {
        baseURI = "http://localhost:8095/IdeaAutoServlet_war_exploded/users?id=1&company=";
        RequestSpecification request = RestAssured.given();
        Response response = request.put(baseURI);
        Assertions.assertEquals("{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Snow\",\"birthDay\":\"2000-01-05\",\"company\":\"\"}", response.getBody().asString());
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void doPutNull2() {
        baseURI = "http://localhost:8095/IdeaAutoServlet_war_exploded/users?id=1&compan";
        RequestSpecification request = RestAssured.given();
        Response response = request.put(baseURI);
        Assertions.assertEquals("{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Snow\",\"birthDay\":\"2000-01-05\",\"company\":null}", response.getBody().asString());
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void doPutNegative() {
        baseURI = "http://localhost:8095/IdeaAutoServlet_war_exploded/users";
        RequestSpecification request = RestAssured.given();
        Response response = request.put(baseURI);
        Assertions.assertEquals(500, response.statusCode());
    }

    //на повторные попытки будет ошибка
    @Test
    void doDeletePositive() {

        baseURI = "http://localhost:8095/IdeaAutoServlet_war_exploded/users?id=";
        RequestSpecification request = RestAssured.given();
        Response response = request.delete(baseURI + "2");
        Assertions.assertEquals("\"User 2 successfully deleted\"", response.getBody().asString());
        Assertions.assertEquals(200, response.statusCode());
        Response response2 = request.delete(baseURI + "2");
        Assertions.assertEquals("\"User 2 not possible to delete, no such user\"", response2.getBody().asString());
        Assertions.assertEquals(200, response2.statusCode());
    }

    @Test
    void doDeleteNull() {
        baseURI = "http://localhost:8095/IdeaAutoServlet_war_exploded/users?id=";
        RequestSpecification request = RestAssured.given();
        Response response = request.delete(baseURI);
        Assertions.assertEquals(500, response.statusCode());
    }

    @Test
    void doDeleteNegative() {
        baseURI = "http://localhost:8095/IdeaAutoServlet_war_exploded/users?id=";
        RequestSpecification request = RestAssured.given();
        Response response = request.delete(baseURI + "99999");
        Assertions.assertEquals("\"User 99999 not possible to delete, no such user\"", response.getBody().asString());
        Assertions.assertEquals(200, response.statusCode());
    }

}
