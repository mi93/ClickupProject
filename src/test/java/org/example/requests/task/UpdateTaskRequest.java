package org.example.requests.task;

import io.restassured.response.Response;
import org.example.requests.BaseRequest;
import org.example.url.ClickupUrl;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class UpdateTaskRequest {

    public static Response updateTaskRequest(JSONObject body, String taskId) {
        return given()
                .spec(BaseRequest.requestSpec())
                .body(body.toString())
                .when()
                .put(ClickupUrl.getTaskUrl(taskId))
                .then()
                .extract()
                .response();
    }
}
