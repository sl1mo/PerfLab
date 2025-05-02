package test3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Test3 {
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);

        System.out.println("Введите путь к файлу values.json: ");
        String valuesPath = in.nextLine();

        System.out.println("Введите путь к файлу tests.json: ");
        String testsPath = in.nextLine();

        System.out.println("Введите путь к файлу report.json: ");
        String reportPath = in.nextLine();

        in.close();

        ObjectMapper mapper = new ObjectMapper();

        Map<Integer, String> valuesMap = new HashMap<>();
        JsonNode valueJN = mapper.readTree(new File(valuesPath));

        if (valueJN.has("values") && valueJN.get("values").isArray()) {
            ArrayNode valuesArray = (ArrayNode) valueJN.get("values");
            for (JsonNode valueNode : valuesArray) {
                if (valueNode.isObject() && valueNode.has("id") && valueNode.has("value")) {
                    int id = valueNode.get("id").asInt();
                    String value = valueNode.get("value").asText();
                    valuesMap.put(id, value);
                }
            }
        }

        JsonNode testsJN = mapper.readTree(new File(testsPath));
        populateValues(testsJN, valuesMap);

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportPath), testsJN);

        System.out.println("Файл report.json готов: " + reportPath);
    }
    private static void populateValues(JsonNode testsJN, Map<Integer, String> valuesMap) {
        if (testsJN.isObject()) {
            if (testsJN.has("id")) {
                int id = testsJN.get("id").asInt();
                if (valuesMap.containsKey(id)) {
                    ((ObjectNode) testsJN).put("value", valuesMap.get(id));
                }
            }

            Iterator<String> fieldNames = testsJN.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode fieldValue = testsJN.get(fieldName);
                populateValues(fieldValue, valuesMap);
            }
        } else if (testsJN.isArray()) {
            for (int i = 0; i < testsJN.size(); i++) {
                populateValues(testsJN.get(i), valuesMap);
            }
        }
    }
}
