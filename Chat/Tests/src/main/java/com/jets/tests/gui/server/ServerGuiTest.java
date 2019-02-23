package com.jets.tests.gui.server;

import com.jets.gui.controller.server.ServerController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ServerGuiTest extends Application {

    private ServerController controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
    	
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/server/MainServerPage.fxml"));
        controller=new ServerController();
        fxmlLoader.setController(controller);
      
        GridPane root=fxmlLoader.load();
        Scene scene=new Scene(root, 1000, 1000);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(800);
        primaryStage.setTitle("Server services");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}