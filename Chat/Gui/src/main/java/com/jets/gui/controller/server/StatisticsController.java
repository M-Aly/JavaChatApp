/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.gui.controller.server;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.jets.database.dal.dto.enums.Country;
import com.jets.network.server.Statistics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.scene.control.TableView;

/**
 *
 * @author PC
 */
public class StatisticsController implements Initializable {
	

	@FXML
	private PieChart CountriesPiChart;

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
    
    Statistics statistics = new Statistics(); 
    Map<Country,Integer> countries = new HashMap<Country, Integer>(); 
    //TableColumn<TableData,?> countryColumn;
    //TableColumn<TableData,?> NumberColumn;
    //TableData data;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    	fillData();
    }

    public void fillData() {
    	
    	onlineTxt.setText(""+statistics.countOnlineUsers());
        offlineTxt.setText(""+statistics.countOfflineUsers());
        maleTxt.setText(""+statistics.countMaleUsers());
        femaleTxt.setText(""+statistics.countFemaleUsers());
    	
         //countryColumn = new TableColumn("Country");
         //countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
			
			
         //NumberColumn = new TableColumn("Number");
         //NumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        
        //countriesTable.getColumns().addAll(countryColumn , NumberColumn);
        
        
        countries = statistics.countUserCountries();
        int value;
        
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        
        
        for(Country country:countries.keySet())
        {
        	value = countries.get(country);
        	data.add(new PieChart.Data(""+country , value));
        }
        
        CountriesPiChart.setData(data);
        
    }
    
}
