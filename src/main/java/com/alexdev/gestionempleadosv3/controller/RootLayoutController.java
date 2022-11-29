package com.alexdev.gestionempleadosv3.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class RootLayoutController {

    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void handleHelp(ActionEvent actionEvent) {
        Alert a = new Alert (Alert.AlertType.INFORMATION);
        a.setTitle("Información");
        a.setHeaderText("Información de la aplicación");
        a.setContentText("Esta aplicación ha sido desarrollada por Alex G y Devesh");
        a.showAndWait();
    }
}