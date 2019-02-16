/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author lapshop
 */
public class ListFriendController implements Initializable {

    @FXML
    private ListView<VBox> listNotification;

    private VBox itemListView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadNotification();

    }

    public void loadNotification() {
        for (int i = 0; i <7; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLItemRequestNotification.fxml"));
            try {
                itemListView = fxmlLoader.load();
                listNotification.getItems().add(itemListView);

            } catch (IOException e1) {

                e1.printStackTrace();
            }
        }

    }

}
