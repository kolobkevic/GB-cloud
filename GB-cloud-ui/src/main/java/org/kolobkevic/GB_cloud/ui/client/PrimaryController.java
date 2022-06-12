package org.kolobkevic.GB_cloud.ui.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import org.kolobkevic.GB_cloud.ui.common.ControllerRegistry;
import org.kolobkevic.GB_cloud.ui.common.dto.AuthRequest;
import org.kolobkevic.GB_cloud.ui.common.dto.BasicRequest;
import org.kolobkevic.GB_cloud.ui.common.dto.RegRequest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {
    private final Network network = Network.getInstance();
    @FXML
    TextField loginField, resultField;
    @FXML
    PasswordField passwordField;

    @FXML
    public void btnExitAction(ActionEvent actionEvent) {
        network.close();
        Platform.exit();
    }

    @FXML
    private void btnAuth() throws IOException {
        String log = loginField.getText();
        String pass = passwordField.getText();
        if (log == null || log.isEmpty() || log.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Вы не указали логин", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        BasicRequest request = new AuthRequest(log, pass);

        try {
            network.sendRequest(request);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerRegistry.register(this);
    }
    public void btnRegAction(ActionEvent actionEvent) {
        String login = loginField.getText();
        String password = passwordField.getText();
        if (login == null || login.isEmpty() || login.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Вы не указали логин", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        BasicRequest request = new RegRequest(login, password);

        try {
            network.sendRequest(request);
        } catch (InterruptedException e) {
            System.out.println("Обработка метода network.sendRequest из PrimaryController привела к исключению");
            throw new RuntimeException(e);
        }
    }
    public void setResultField(String msg) {
        resultField.setText(msg);
    }
}