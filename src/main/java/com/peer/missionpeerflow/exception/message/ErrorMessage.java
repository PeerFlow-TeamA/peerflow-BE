package com.peer.missionpeerflow.exception.message;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class ErrorMessage {
    private String message;

    public ErrorMessage(Exception e) {
        this.message = e.getMessage();
    }

    public static ErrorMessage of(Exception e) {
        return new ErrorMessage(e);
    }

    public static ErrorMessage of(String message) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.message = message;
        return errorMessage;
    }
}
