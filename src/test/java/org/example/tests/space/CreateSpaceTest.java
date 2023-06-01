package org.example.tests.space;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.properties.ClickupProperties;
import org.example.url.ClickupUrl;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateSpaceTest {

    @Test
    void createSpaceTest() {

        JSONObject body = new JSONObject();
        body.put("name", "Proxima Centauri");

        Response response = given()
                .header("Authorization", ClickupProperties.getToken())
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post(ClickupUrl.getSpacesUrl(ClickupProperties.getTeamId()))
                .then()
                .extract()
                .response();

        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo("Proxima Centauri");

    }

}
