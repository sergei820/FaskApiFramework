package org.example.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Utils {

    public static List<String> findJsonNode(String responseBody, String jsonNodeName) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(responseBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<String> jsonNodeValues = new ArrayList<>();
        if (node.isObject()) {
            JsonNode jsonNode = node.path(jsonNodeName);
            if (!jsonNode.isMissingNode()) {
                String countryCode = jsonNode.asText();
                jsonNodeValues.add(countryCode);
            }
            node.iterator().forEachRemaining(n -> jsonNodeValues.addAll(findJsonNode(n.toString(), jsonNodeName)));
        } else if (node.isArray()) {
            node.forEach(n -> jsonNodeValues.addAll(findJsonNode(n.toString(), jsonNodeName)));
        }
        return jsonNodeValues;
    }

    public static List<String> getNestedElementsList(String responseBody, String parentNode) {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONObject receiveObject = jsonObject.getJSONObject(parentNode);

        List<String> nestedElementsList = new ArrayList<>();
        Iterator<String> keys = receiveObject.keys();
        while (keys.hasNext()) {
            String fieldName = keys.next();
            nestedElementsList.add(fieldName);
        }

        return nestedElementsList;
    }
}
