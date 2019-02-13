package com.jets.tests.gui;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import com.jets.gui.controller.DashboardController;

public class DashboardGuiTest extends Application {

    private DashboardController controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dashboard/contact_design.fxml"));
        controller=new DashboardController();
        fxmlLoader.setController(controller);
        AnchorPane root=fxmlLoader.load();
        Scene scene=new Scene(root, 1210, 600);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
        controller.addMessageReceived("hello");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
