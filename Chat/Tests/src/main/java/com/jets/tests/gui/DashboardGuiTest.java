package com.jets.tests.gui;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import com.jets.gui.controller.DashboardController;

/**
 * test of the dashboard (not completed)
 * @author Mohamed Ali
 */
public class DashboardGuiTest extends Application {

    private DashboardController controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
    	
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dashboard/contact_design.fxml"));
        controller=new DashboardController();
        fxmlLoader.setController(controller);
      
        BorderPane root=fxmlLoader.load();
        Scene scene=new Scene(root, 1210, 700);
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(700);
        primaryStage.setTitle("Chat Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
        controller.addMessageReceived("hello");
        controller.addMessageSent("hi");
        controller.addMessageSent("hi");
        controller.addMessageReceived("hi");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
