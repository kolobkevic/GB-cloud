package org.kolobkevic.GB_cloud.ui.common.dto;

public class AuthResponse implements BasicResponse {
    private String result;
    private String clientHomeDir = "";
    public String getResult() {
        return result;
    }

    public AuthResponse(String result) {
        this.result = result;
    }
    @Override
    public String getType() {
        return "AuthResponse";
    }

    public String getClientHomeDir() {
        return clientHomeDir;
    }
    public void setClientHomeDir(String clientHomeDir) {
        this.clientHomeDir = clientHomeDir;
    }
}
