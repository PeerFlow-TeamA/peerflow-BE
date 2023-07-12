package com.peer.missionpeerflow;

import org.springframework.context.annotation.Bean;
import org.springframework.data.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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
