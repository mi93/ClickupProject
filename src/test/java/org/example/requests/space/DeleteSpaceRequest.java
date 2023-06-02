package org.example.requests.space;

import io.restassured.response.Response;
import org.example.properties.ClickupProperties;
import org.example.requests.BaseRequest;
import org.example.url.ClickupUrl;

import static io.restassured.RestAssured.given;

public class DeleteSpaceRequest {

    public static Response deleteSpaceRequest(String spaceId) {
        return given()
                .spec(BaseRequest.requestSpec())
                .when()
                .delete(ClickupUrl.getSpaceUrl(ClickupProperties.getTeamId(), spaceId))
                .then()
                .extract()
                .response();
    }
}
