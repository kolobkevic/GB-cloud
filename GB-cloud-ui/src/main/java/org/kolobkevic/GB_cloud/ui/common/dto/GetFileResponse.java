package org.kolobkevic.GB_cloud.ui.common.dto;

import org.kolobkevic.GB_cloud.ui.client.FileMessage;

public class GetFileResponse implements BasicResponse {
    private FileMessage fileMessage;
    private String destPath;

    public FileMessage getFileMessage() {
        return fileMessage;
    }
    public String getDestPath() { return destPath; }

    public GetFileResponse(FileMessage fileMessage, String destPath) {
        this.fileMessage = fileMessage;
        this.destPath = destPath;
    }

    @Override
    public String getType() {
        return "GetFileResponse";
    }
}
