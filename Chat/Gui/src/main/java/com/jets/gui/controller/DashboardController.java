package com.jets.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;

public class DashboardController implements Initializable{

    @FXML
    private GridPane chatArea;
    @FXML
    private TextArea sendArea;
    @FXML
    private Button sendButton;
    private int counter=1;
    private HBox chat;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sendArea.setOnKeyTyped((event)->{
            if((event.getCharacter().equals("\n")||event.getCharacter().equals("\r"))) {
                if(sendArea.getText().equals("\n")){
                    sendArea.clear();
                }
                else{
                    addMessageSent(sendArea.getText());
                    sendArea.clear();
                }
            }
        });
        sendButton.setOnMouseClicked((event)->{
            if(!sendArea.getText().equals("")) {
                addMessageSent(sendArea.getText());
                sendArea.clear();
            }
        });
    }
    
    private void loadMessage(String message){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dashboard/message.fxml"));
            chat=fxmlLoader.load();
            TextArea chatText = (TextArea) chat.getChildren().get(1);
            chatText.setEditable(false);
            chatText.setText(message);
            chatArea.getChildren().add(chat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addMessageSent(String message) {
        loadMessage(message);
        chat.setStyle("-fx-translate-x: "+80);
        GridPane.setConstraints(chat, 0, counter, 2, 1);
        counter++;
    }
    public void addMessageReceived(String message) {
        loadMessage(message);
        chat.setStyle("-fx-translate-x: "+100);
        GridPane.setConstraints(chat, 1, counter, 2, 1);
        counter++;
    }
}
