/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.gui.controller.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lapshop
 */
public class TextSettingController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private ComboBox<?> comboFontColor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        FontPicker comboFontSetting = new FontPicker();
        //listener for font selection
        comboFontSetting.valueProperty().addListener(observable -> {
            System.out.println(comboFontSetting.getValue());
        });

        comboFontSetting.setPrefWidth(150);
        comboFontSetting.setLayoutX(196);
        comboFontSetting.setLayoutY(60);

        pane.getChildren().add(comboFontSetting);
    }

}
