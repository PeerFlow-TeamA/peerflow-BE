package com.peer.missionpeerflow.utils;

import org.springframework.data.util.Pair;

import java.util.ArrayList;

public class RequestBodyJsonGenerator {
    private ArrayList<Pair<String, String>> attributes = new ArrayList<>();

    public RequestBodyJsonGenerator addAttribute(String key, Object value) {
        attributes.add(Pair.of(key, value.toString()));
        return this;
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < attributes.size(); i++) {
            Pair<String, String> attribute = attributes.get(i);
            sb.append("\"");
            sb.append(attribute.getFirst());
            sb.append("\":");
            sb.append("\"");
            sb.append(attribute.getSecond());
            sb.append("\"");
            if (i != attributes.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        String result = sb.toString();
        attributes.clear();
        return result;
    }
}
