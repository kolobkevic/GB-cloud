package org.kolobkevic.GB_cloud.ui.common.dto;

public class GetFileListRequest implements BasicRequest {
    private String clientDir;
    public String getClientCurrentDir() {
        return clientDir;
    }
    public GetFileListRequest(String clientDir) {
        this.clientDir = clientDir;
    }
    @Override
    public String getType() {
        return "GetFileListRequest";
    }
}
