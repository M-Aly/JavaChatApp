/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.tests.gui.client;

import com.jets.gui.controller.client.DashboardController;
import com.jets.gui.controller.client.LoginController;
import com.jets.gui.controller.client.UpdateProfileController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class LoginFormTest extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
    	
    	FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/client/login/Login.fxml"));
    	
    	LoginController controller = new LoginController();
    	fxmlLoader.setController(controller);
		/*
		  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/dashboard/final.fxml"));
        dashboardController=new DashboardController();
        fxmlLoader.setController(dashboardController);
          GridPane root=fxmlLoader.load();
		 */
        Parent root= fxmlLoader.load();
        
        
        Scene scene = new Scene(root);
        
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
