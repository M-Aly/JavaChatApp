/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jets.tests.gui.client;
import java.io.File;
import java.net.URL;

import com.jets.gui.controller.client.RegisterController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class RegisterFormTest extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
    	URL url = new File("C:\\Users\\PC\\Documents\\NetBeansProjects\\JavaChatApp\\Chat\\Gui\\src\\main\\resources\\client\\login\\Register.fxml").toURL();
        FXMLLoader loader = new FXMLLoader(url);
        loader.setController(new RegisterController());
        Parent root=loader.load();
        
        Scene scene = new Scene(root);
        stage.setTitle("Registeration Form");
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
