/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.tests.gui.server;

import com.jets.gui.controller.server.ServerController;
import com.jets.gui.controller.server.StatisticsController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class MainServer extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader();
    	ServerController controller = new ServerController();
    	loader.setController(controller);
        Parent root = loader.load(getClass().getResource("/server/MainServerPage.fxml").openStream());
       //loader.setController(controller);
       // controller.fillData();
       
        Scene scene = new Scene(root);
        stage.setTitle("Server form");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
