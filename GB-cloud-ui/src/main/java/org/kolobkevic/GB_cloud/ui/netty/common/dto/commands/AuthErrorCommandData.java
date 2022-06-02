package org.kolobkevic.GB_cloud.ui.netty.common.dto.commands;

import java.io.Serializable;

public class AuthErrorCommandData implements Serializable {

    private final String errorMessage;

    public AuthErrorCommandData(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
