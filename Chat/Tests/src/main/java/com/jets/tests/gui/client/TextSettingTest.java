/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.tests.gui.client;
import com.jets.gui.controller.client.TextSettingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author lapshop
 */
public class TextSettingTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {   
    	
        System.out.println("done");
        Parent root = FXMLLoader.load(getClass().getResource("/client/dashboard/textSetting.fxml"));  
        
        Scene scene = new Scene(root);       
        stage.setScene(scene);
        stage.show();
    }
}
