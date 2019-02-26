package com.jets.tests.gui.server;

import com.jets.gui.controller.server.StatisticsController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StatisticsTest extends Application {
	
	
    @Override
    public void start(Stage stage) throws Exception {
    	
    	FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/server/Statisticsform.fxml"));
    	
    	StatisticsController controller = new StatisticsController();
    	fxmlLoader.setController(controller);
		controller.fillData();
    	
        Parent root= fxmlLoader.load();
        
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

	
	
	public static void main(String argc[])
	{
		launch(argc);
		
	}


}
