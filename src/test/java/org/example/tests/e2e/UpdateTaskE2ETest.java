package org.example.tests.e2e;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.requests.list.CreateListRequest;
import org.example.requests.space.CreateSpaceRequest;
import org.example.requests.space.DeleteSpaceRequest;
import org.example.requests.task.CreateTaskRequest;
import org.example.requests.task.UpdateTaskRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class UpdateTaskE2ETest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTaskE2ETest.class);
    private String spaceId;
    private String listId;
    private String taskId;
    String updatedTaskName = "Gliese 179 b";

    @Test
    void updateTaskE2ETest() {

        spaceId = createSpaceStep();
        LOGGER.info("Space created with id: {}", spaceId);

        listId = createListStep();
        LOGGER.info("List created with id: {}", listId);

        taskId = createTaskStep();
        LOGGER.info("Task created with id: {}", taskId);

        closeTaskStep();

        updatedTaskName = updateTaskStep();
        LOGGER.info("Task updated with name: {}", updatedTaskName);

        deleteSpaceStep();
    }
    private String createSpaceStep() {

        final String spaceName = "Orion";
        JSONObject body = new JSONObject();
        body.put("name", spaceName);

        Response response = CreateSpaceRequest.createSpaceRequest(body);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(spaceName);
        return json.getString("id");
    }

    private String createListStep() {

        final String listName = "Betelgeuse";
        JSONObject body = new JSONObject();
        body.put("name", listName);

        Response response = CreateListRequest.createListRequest(body, spaceId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(listName);
        return json.getString("id");
    }

    private String createTaskStep() {

        final String taskName = "Gliese";
        final String taskDescription = "The Long Way to a Small, Angry Planet";

        JSONObject body = new JSONObject();
        body.put("name", taskName);
        body.put("description", taskDescription);
        body.put("status", "to do");
        body.put("priority", JSONObject.NULL);
        body.put("parent", JSONObject.NULL);
        body.put("time_estimate", JSONObject.NULL);
        body.put("assignees", "{}");
        body.put("archived", false);

        Response response = CreateTaskRequest.createTaskRequest(body, listId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(taskName);
        Assertions.assertThat(json.getString("description")).isEqualTo(taskDescription);
        return json.getString("id");
    }

    private String updateTaskStep() {

        JSONObject body = new JSONObject();
        body.put("name", updatedTaskName);

        Response response = UpdateTaskRequest.updateTaskRequest(body, taskId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(updatedTaskName);
        return json.getString("name");
    }

    private void closeTaskStep() {

        JSONObject body = new JSONObject();
        body.put("status", "complete");

        Response response = UpdateTaskRequest.updateTaskRequest(body, taskId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("status.status")).isEqualTo("complete");
    }

    private void deleteSpaceStep() {

        Response deleteResponse = DeleteSpaceRequest.deleteSpaceRequest(spaceId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }
}
