package org.kolobkevic.GB_cloud.ui.netty.common.dto;

public class GetFileListRequest implements BasicRequest {

    @Override
    public String getType() {
        return "GetFileListRequest";
    }
}
