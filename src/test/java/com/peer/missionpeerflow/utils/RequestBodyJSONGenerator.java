package com.peer.missionpeerflow.utils;

import org.springframework.data.util.Pair;

import java.util.ArrayList;

public class RequestBodyJSONGenerator {
    private ArrayList<Pair<String, String>> attributes = new ArrayList<>();

    public RequestBodyJSONGenerator addAttribute(String key, Object value) {
        attributes.add(Pair.of(key, value.toString()));
        return this;
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Pair<String, String> attribute : attributes) {
            sb.append("\"");
            sb.append(attribute.getFirst());
            sb.append("\":\"");
            sb.append(attribute.getSecond());
            sb.append("\",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        String result = sb.toString();
        attributes.clear();
        return result;
    }
}
