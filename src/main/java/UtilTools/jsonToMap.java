package UtilTools;



import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 递归调用解析json字符串到 map 中
 */

public class jsonToMap {
    public static void main(String[] args) throws Exception {
        String jsonString = "";

        // 使用 ObjectMapper 将 JSON 字符串转换为 Map
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper.readValue(jsonString, Map.class);

        // 用于存储最终的键值对
        Map<String, String> resultMap = new HashMap<>();

        // 递归方法，将每个键值对存入 resultMap
        extractKeyValuePairs(jsonMap, "", resultMap);

        // 输出最终的键值对
        resultMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    // 递归提取键值对
    private static void extractKeyValuePairs(Map<String, Object> map, String prefix, Map<String, String> resultMap) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = prefix.isEmpty() ? entry.getKey() : entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map) {
                // 如果值是 Map 类型，递归调用
                extractKeyValuePairs((Map<String, Object>) value, key, resultMap);
            } else if (value instanceof List) {
                // 如果值是 List 类型，遍历每个元素
                int index = 0;
                for (Object listItem : (List<?>) value) {
                    extractKeyValuePairs((Map<String, Object>) listItem, key + "[" + index + "]", resultMap);
                    index++;
                }
            } else {
                // 如果值是基本类型，直接放入 resultMap
                resultMap.put(key, value.toString());
            }
        }
    }
}
