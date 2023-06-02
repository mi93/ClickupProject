package org.example.tests.space;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.requests.space.CreateSpaceRequest;
import org.example.requests.space.DeleteSpaceRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class CreateSpaceTest {

    @Test
    void createSpaceTest() {

        JSONObject body = new JSONObject();
        body.put("name", "Orion");

        Response response = CreateSpaceRequest.createSpaceRequest(body);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo("Orion");
        String spaceId = json.getString("id");

        //DELETE SPACE
        Response deleteResponse = DeleteSpaceRequest.deleteSpaceRequest(spaceId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }
}
