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
    
    Statistics statistics = new Statistics(); 
    Map<Country,Integer> countries = new HashMap<Country, Integer>(); 
    TableColumn countryColumn;
    TableColumn NumberColumn;
    TableData data;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }

    public void fillData() {
    	
    	onlineTxt.setText(""+statistics.countOnlineUsers());
        offlineTxt.setText(""+statistics.countOfflineUsers());
        maleTxt.setText(""+statistics.countMaleUsers());
        femaleTxt.setText(""+statistics.countFemaleUsers());
    	
         countryColumn = new TableColumn("Country");
         NumberColumn = new TableColumn("Number");
        
        countriesTable.getColumns().addAll(countryColumn , NumberColumn);
        
        countries = statistics.countUserCountries();
        int value;
        
        for(Country country:countries.keySet())
        {
        	value = countries.get(country);
        	data = new TableData(country, value);
        	countriesTable.getItems().add(data);
        }
        
    }
    
    private class TableData{
    	
    	Country country ;
    	Integer number;
    	
    	public TableData(Country country , Integer number)
    	{
    		this.country=country;
    		this.number = number;
    	}
    	
    	void setCountry(Country country)
    	{
    		this.country= country;
    	}
    	
    	void setNumber(Integer number)
    	{
    		this.number =number;
    	}
    	
    	Country getCountry()
    	{
    		return country;
    	}
    	Integer getNumber()
    	{
    		 
    		return number;
    	}
    	
    	
    }
    
    
    
    
    
    

}
