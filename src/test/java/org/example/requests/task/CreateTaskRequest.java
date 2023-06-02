package org.example.requests.task;

import io.restassured.response.Response;
import org.example.requests.BaseRequest;
import org.example.url.ClickupUrl;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateTaskRequest {

    public static Response createTaskRequest(JSONObject body, String listId) {
        return given()
                .spec(BaseRequest.requestSpec())
                .body(body.toString())
                .when()
                .post(ClickupUrl.getTasksUrl(listId))
                .then()
                .extract()
                .response();
    }
}
