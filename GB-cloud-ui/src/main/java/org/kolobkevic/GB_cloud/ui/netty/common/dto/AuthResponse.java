package org.kolobkevic.GB_cloud.ui.netty.common.dto;

public class AuthResponse implements BasicResponse {
    public String getResult() {
        return result;
    }

    private String result;

    public AuthResponse(String result) {
        this.result = result;
    }

    @Override
    public String getType() {
        return "AuthResponse";
    }
}
