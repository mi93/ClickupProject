package org.example.tests.space;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.requests.space.CreateSpaceRequest;
import org.example.requests.space.DeleteSpaceRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CreateSpaceWithParamsTest {

    @ParameterizedTest(name = "Name: {0}")
    @DisplayName("Create space with valid space name")
    @MethodSource("createSpaceData")
    void createSpaceTest(String spaceName) {

        JSONObject body = new JSONObject();
        body.put("name", spaceName);

        Response response = CreateSpaceRequest.createSpaceRequest(body);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(spaceName);
        String spaceId = json.getString("id");

        //DELETE SPACE
        Response deleteResponse = DeleteSpaceRequest.deleteSpaceRequest(spaceId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);

    }

    private static Stream<Arguments> createSpaceData() {

        return Stream.of(
                Arguments.of("SPACE"),
                Arguments.of("space233"),
                Arguments.of("space_12")
        );
    }
}
