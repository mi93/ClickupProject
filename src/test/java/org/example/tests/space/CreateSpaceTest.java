package org.example.tests.space;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.requests.space.CreateSpaceRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class CreateSpaceTest {

    @Test
    void createSpaceTest() {

        JSONObject body = new JSONObject();
        body.put("name", "Proxima Centauri");

        Response response = CreateSpaceRequest.createSpaceRequest(body);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo("Proxima Centauri");

    }

}
