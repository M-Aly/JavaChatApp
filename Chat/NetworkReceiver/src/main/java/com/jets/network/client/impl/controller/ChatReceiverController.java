package com.jets.network.controller;

import java.io.File;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import com.jets.network.client.impl.ChatReceiver;

/**
gui and network controller for chat
@author Mohamed Ali
*/
public class ChatReceiverController {
	
	private ChatReceiver chatReceiver;
	private Stage stage;
	private double progress;
	
	public void setChatReceiver(ChatReceiver chatReceiver) {
		this.chatReceiver = chatReceiver;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public File saveFile(File file) {
		FileChooser chooser = new FileChooser();
        chooser.setTitle("Save file");
        File saveFile = chooser.showSaveDialog(stage);
        return saveFile;
	}

	public void updateProgress(double addProgress) {
		progress = progress+addProgress;
		// progress bar
	}

}
