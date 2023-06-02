package org.example.requests.list;

import io.restassured.response.Response;
import org.example.requests.BaseRequest;
import org.example.url.ClickupUrl;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateListRequest {

    public static Response createListRequest(JSONObject body, String spaceId) {
        return given()
                .spec(BaseRequest.requestSpec())
                .body(body.toString())
                .when()
                .post(ClickupUrl.getListsUrl(spaceId))
                .then()
                .extract()
                .response();
    }
}
