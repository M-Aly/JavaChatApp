package com.jets.gui.controller;

import org.controlsfx.control.Notifications;
import org.controlsfx.tools.Platform;
import com.jets.gui.controller.DashboardController;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.Friend;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.*;
import javafx.scene.control.Label;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javax.swing.text.Position;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class DashboardController implements Initializable {

	@FXML
	private GridPane chatArea;
	@FXML
	private TextArea sendArea;
	@FXML
	private Button sendButton;
	private int counter = 1;
	private HBox chat;

	@FXML
	private ListView<VBox> contactArea;

	@FXML
	private TextField searchArea;
	@FXML
	private ImageView addFriendButton;
	@FXML
	private ImageView changeStatuesButton;
	@FXML
	private ImageView groupButton;
	@FXML
	private ImageView notificationStatuesButton;
	@FXML
	private ImageView requestsNotificationButton;
	@FXML
	private ImageView signOutButton;
	@FXML
	private ImageView profileIcone;

	private VBox contactBox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		loadContacts();
		setMenu();
		sendArea.setOnKeyTyped((event) -> {

			if ((event.getCharacter().equals("\n") || event.getCharacter().equals("\r"))) {
				if (sendArea.getText().equals("\n")) {
					sendArea.clear();
				} else {
					addMessageSent(sendArea.getText());
					sendArea.clear();
				}
			}
		});
		sendButton.setOnMouseClicked((event) -> {
			if (!sendArea.getText().equals("")) {
				addMessageSent(sendArea.getText());
				sendArea.clear();
			}
		});

	}

	private void loadMessage(String message) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dashboard/message.fxml"));
			chat = fxmlLoader.load();
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
		chat.setStyle("-fx-translate-x: " + 80);
		GridPane.setConstraints(chat, 0, counter, 2, 1);
		counter++;
	}

	public void addMessageReceived(String message) {
		loadMessage(message);
		chat.setStyle("-fx-translate-x: " + 100);
		GridPane.setConstraints(chat, 1, counter, 2, 1);
		counter++;
	}

	public void loadContacts()// Friend user)
	{
		for (int i = 0; i < 10; i++) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dashboard/contact.fxml"));
			try {
				contactBox = fxmlLoader.load();
				contactArea.getItems().add(contactBox);
			} catch (IOException e1) {

				e1.printStackTrace();
			}

		}
		

	}

	public void showProfile() {
		profileIcone.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				FXMLLoader chatloader = new FXMLLoader();
				Parent loader;
				try {
					loader = chatloader.load(getClass().getResource("/dashboard/profileDesgin.fxml").openStream());
					DashboardController controller = new DashboardController();
					chatloader.setController(controller);

					Stage newStage = new Stage();
					newStage.setScene(new Scene(loader));
					newStage.show();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
			}
		});

	}

	public void updateStatus() {
		changeStatuesButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

			}
		});

	}

	public void showGroups() {
		groupButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

			}
		});

	}

	public void addFriend() {
		addFriendButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

			}
		});

	}

	public void requestNotification() {
		requestsNotificationButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

			}
		});

	}

	public void notificationStatus() {
		notificationStatuesButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				Notifications notification = Notifications.create().title("download completed !")
						.text("saved in : E/newfolder").hideAfter(Duration.seconds(5)).graphic(null)
						.position(Pos.BOTTOM_RIGHT);

				notification.showInformation();

			}
		});

	}

	public void signOut() {
		signOutButton.setOnMouseClicked((event) -> {
			
		});

	}

	public void searchByName() {

	}

	public void setMenu() {
		final ContextMenu contextMenu = new ContextMenu();
		contextMenu.setMinSize(30, 200);
		MenuItem add = new MenuItem("Add to Group");
		MenuItem Delete = new MenuItem("Delete");		
		contextMenu.getItems().addAll(add, Delete);
		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Cut...");
			}
		});
		Delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Copy...");
			}
		});
		
		
		contactArea.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				contactBox = contactArea.getSelectionModel().getSelectedItem(); 
				if (event.isSecondaryButtonDown()) {
					contextMenu.show(contactBox, event.getScreenX(), event.getScreenY());
				}
				if(event.isPrimaryButtonDown())
				{
					contextMenu.hide();
				}
			}
		});

	}
}
