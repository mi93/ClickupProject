package org.example.requests.space;

import io.restassured.response.Response;
import org.example.properties.ClickupProperties;
import org.example.requests.BaseRequest;
import org.example.url.ClickupUrl;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateSpaceRequest {

    public static Response createSpaceRequest(JSONObject body) {
        return given()
                .spec(BaseRequest.requestSpec(body))
                .when()
                .post(ClickupUrl.getSpacesUrl(ClickupProperties.getTeamId()))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
