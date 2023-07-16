package com.peer.missionpeerflow.exception.message;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class SuccessMessage {
    private String message;

    public SuccessMessage(String message) {
        this.message = message;
    }

    public static SuccessMessage of(String message) {
        return new SuccessMessage(message);
    }
}
