package utils;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

public class JsonMessage {
    final private String message;

    private JsonMessage(String message) {
        this.message = message;
    }

    public static JsonNode make(String message){
        return Json.toJson((new JsonMessage(message)));
    }

    public String getMessage() {
        return message;
    }
}
