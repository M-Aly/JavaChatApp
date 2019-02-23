package com.jets.tests.gui.client;


import com.jets.gui.controller.client.DashboardController;

import animatefx.animation.Swing;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DashboardGuiTest extends Application {

    private DashboardController dashboardController;

    @Override
    public void start(Stage primaryStage) throws Exception{
    	
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/dashboard/final.fxml"));
        dashboardController=new DashboardController();
        fxmlLoader.setController(dashboardController);
      
        GridPane root=fxmlLoader.load();
        Swing swingMotion = new Swing(root);
        swingMotion.play();
        Scene scene=new Scene(root, 1210, 700);
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(700);
        primaryStage.setTitle("Chat Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        dashboardController.addMessageReceived("hello");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
