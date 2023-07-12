package com.peer.missionpeerflow.exception.errormessage;

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
}
