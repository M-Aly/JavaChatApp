/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.gui.controller.server;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;

/**
 *
 * @author PC
 */
public class StatisticsController implements Initializable {
	

    @FXML
    private TableView countriesTable;

    @FXML
    private TextField onlineTxt;

    @FXML
    private TextField offlineTxt;

    @FXML
    private TextField femaleTxt;

    @FXML
    private TextField maleTxt;

    @FXML
    private Pane paneID;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // TableColumn countryCol = new TableColumn("Country");
       // TableColumn usersCol = new TableColumn("Users");
      //  countryCol.setCellValueFactory( new PropertyValueFactory<String,String>("firstName"));

    }

    public void fillData() {

    }

}
