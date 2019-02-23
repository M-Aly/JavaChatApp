/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.gui.controller.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.dialog.ProgressDialog;

/**
 *
 * @author Zainab
 */
public class DownloadFileController implements Initializable {
    
    @FXML
    private Label label; 
    
    @FXML
    private Button startButton;
               
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        startButton.setOnAction(e -> {
            ProgressForm pForm = new ProgressForm();

            // In real life this task would do something useful and return 
            // some meaningful result:
            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() throws InterruptedException {
                    for (int i = 0; i < 10; i++) {
                        updateProgress(i, 10);
                        Thread.sleep(200);
                    }
                    updateProgress(10, 10);
                    return null ;
                }
            };
            pForm.activateProgressBar(task);

            // in real life this method would get the result of the task
            // and update the UI based on its value:
            task.setOnSucceeded(event -> {                
                pForm.getDialogStage().close();
                startButton.setDisable(false);
            });

            startButton.setDisable(true);
            pForm.getDialogStage().show();

            Thread thread = new Thread(task);
            thread.start();
        });
    }
    
    public static class ProgressForm {
        private final Stage dialogStage;
        private final ProgressBar pb = new ProgressBar();
        private final ProgressIndicator pin = new ProgressIndicator();

        public ProgressForm() {
            dialogStage = new Stage();
            dialogStage.initStyle(StageStyle.UTILITY);
            dialogStage.setResizable(false);
            dialogStage.initModality(Modality.APPLICATION_MODAL);

            // PROGRESS BAR
            final Label label = new Label();
            label.setText("alerto");

            pb.setProgress(-1F);
            pin.setProgress(-1F);

            final HBox hb = new HBox();
            hb.setSpacing(5);
            hb.setAlignment(Pos.CENTER);
            hb.getChildren().addAll(pb, pin);

            Scene scene = new Scene(hb);
            dialogStage.setScene(scene);
        }

        public void activateProgressBar(final Task<?> task)  {
            pb.progressProperty().bind(task.progressProperty());
            pin.progressProperty().bind(task.progressProperty());
            dialogStage.show();
            }

        public Stage getDialogStage() {
            return dialogStage;
        }
    }
                 
}
