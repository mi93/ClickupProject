package org.example.requests.space;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.properties.ClickupProperties;
import org.example.url.ClickupUrl;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateSpaceRequest {

    public static Response createSpaceRequest(JSONObject body) {
        return given()
                .header("Authorization", ClickupProperties.getToken())
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post(ClickupUrl.getSpacesUrl(ClickupProperties.getTeamId()))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
