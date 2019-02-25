package com.jets.gui.controller.client;

import javafx.scene.media.Media;
import javafx.scene.control.ScrollPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;
import org.controlsfx.tools.Platform;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.InvitationStatus;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.gui.controller.client.DashboardController;
import com.jets.gui.controller.client.notifications.ListFriendController;
import com.jets.gui.controller.client.notifications.ListStatusController;
import com.jets.network.common.serverservice.UserSettingsInt;
import com.jets.network.exception.StatusChangeFailedException;
import com.jets.network.exception.UpdateUserFailedException;
import com.jets.network.server.impl.IntroduceUser;
import com.jets.network.server.impl.Search;
import com.jets.network.server.impl.UserSettings;
import com.jets.*;
import animatefx.animation.Bounce;
import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import animatefx.animation.Flash;
import animatefx.animation.Pulse;
import animatefx.animation.Swing;

import com.jets.database.dal.dao.impl.FriendsDao;
import com.jets.database.dal.dao.impl.UserDao;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javafx.util.Duration;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.scene.image.*;
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
import javafx.geometry.NodeOrientation;
public class DashboardController implements Initializable {

	// @FXML
	// private GridPane chatArea;
	@FXML
	private TextArea sendArea;
	@FXML
	private Button sendButton;
	@FXML
	private ScrollPane scrollPaneArea;
	@FXML
	private VBox chatArea;
	@FXML
	private Text friendName;
	@FXML
	private ImageView friendImage;
	@FXML
	private ImageView onlineImage;
	@FXML
	private  Text massageText;
	private int counter = 0;
	private HBox chat;

	private Text chatText;
	private static final boolean MESSAGE_SENT = true;
	private static final boolean MESSAGE_RECEIVED = false;
	//private boolean messageFlag;

	@FXML
	private ListView<VBox> contactArea;

	@FXML
	private TextField searchArea;
	@FXML
	private ImageView addFriendButton;
	@FXML
	private ImageView sendFileButton;
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
	@FXML
	private ImageView textSetting;
	private VBox contactBox;
	private Media sound;
	private MediaPlayer player;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		loadContacts();
		setMenu();
		Tooltip requestsNotification = new Tooltip("add requests Notification");
		Tooltip.install(requestsNotificationButton, requestsNotification);

		Tooltip addFriend = new Tooltip("add Friend");
		Tooltip.install(addFriendButton, addFriend);

		Tooltip changeStatues = new Tooltip("change your Status");
		Tooltip.install(changeStatuesButton, changeStatues);

		Tooltip groups = new Tooltip("your groups");
		Tooltip.install(groupButton, groups);

		Tooltip notificationStatues = new Tooltip("Statues notification");
		Tooltip.install(notificationStatuesButton, notificationStatues);

		Tooltip signOut = new Tooltip("sign Out");
		Tooltip.install(signOutButton, signOut);

		Tooltip profile = new Tooltip("your profile");
		Tooltip.install(profileIcone, profile);
		chatArea = new VBox ();
		scrollPaneArea.setContent(chatArea);

		// if we pressed on enter key
		sendArea.setOnKeyTyped((event) -> {
			if (event.getCharacter().equals("\n") || event.getCharacter().equals("\r")) {

				String message = sendArea.getText().trim();
				
				if (message.equals("")) 
				{
					sendArea.clear();
				} 
				else 
				{
					addMessageSent(message);

					sendArea.clear();
				}
			}
		});

		// if we pressed on button
		sendButton.setOnMouseClicked((event) -> 
		{
			Flash f = new Flash(sendButton);
			f.play();
			String message = sendArea.getText().trim();
			if (!message.equals("")) 
			{
				addMessageSent(message);

				sendArea.clear();
			}
		});
	}

	private void loadMessage(String message, boolean flag) {
		try {
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/dashboard/message.fxml"));
			chat = fxmlLoader.load();
			VBox chatBox = (VBox) chat.getChildren().get(1);
			chatText = (Text) chatBox.getChildren().get(0);
			chatText.setText(message);
				
			if (flag == MESSAGE_SENT) 
			{
				//chat.setAlignment(Pos.BASELINE_RIGHT);
				chat.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
				chatArea.getChildren().add(chat);
				chatArea.setSpacing(15);
			} 
			else if (flag == MESSAGE_RECEIVED) 
			{
				//chat.setAlignment(Pos.BASELINE_LEFT);
				//chatArea.setAlignment(Pos.BASELINE_LEFT);
				chat.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
				chatArea.getChildren().add(chat);
				chatArea.setSpacing(15);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addMessage(String message, boolean flag) {

		if (flag == MESSAGE_SENT && chatText != null) 
		{
			
			//chatText.setText(chatText.getText() + "\n" + message);
			loadMessage(message , flag);
		} 
		else if (flag == MESSAGE_RECEIVED) 
		{
			
			loadMessage(message , flag);

		}

	}

	public void addMessageSent(String message) {
		addMessage(message, MESSAGE_SENT);
	}

	public void addMessageReceived(String message) {
		addMessage(message, MESSAGE_RECEIVED);
	}

	
	public void loadContacts(List<Friend> listfriends)//session managment
	{
		UserSettings userSetting =new UserSettings(); 
		
		//////////
		UserDao currentUser = new UserDao();
		User user = currentUser.retrieveByPhoneNumber("9380283098");//session managmenttttttttttttttttt
		//////////
	///edit on network interace -__-
		
		FriendsDao friendsDao= new FriendsDao(user);
		 = friendsDao.retrieveAllFriends();
		
		
		for (int i = 0; i < listfriends.size(); i++) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/dashboard/contact.fxml"));
			DashboardController controller = new DashboardController();
			fxmlLoader.setController(controller);
			try {
				contactBox = fxmlLoader.load();
			    friendName.setText(listfriends.get(i).getName());
			    Image image = new Image(listfriends.get(i).getPicture().toString());
			    friendImage.setImage(image);
			    Image imageStates=null;
			    if(listfriends.get(i).getStatus()==UserStatus.AVAILABLE)
			    {
			    	imageStates = new Image("/client/dashboard/online.png");
				    
			    }
			    else if (listfriends.get(i).getStatus()==UserStatus.AWAY)
			    {
			    	 imageStates = new Image("/client/dashboard/away.png");
				 
			    }
			    else if (listfriends.get(i).getStatus()==UserStatus.OFFLINE)
			    {
			    	imageStates = new Image("/client/dashboard/offline.png");
				   
			    }
			    friendImage.setImage(imageStates);
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
				Swing swing = new Swing(profileIcone);
				swing.play();
				FXMLLoader chatloader = new FXMLLoader();
				Parent loader;
				try {
					UpdateProfileController controller = new UpdateProfileController();
					chatloader.setController(controller);
					loader = chatloader
							.load(getClass().getResource("/client/dashboard/profileDesgin.fxml").openStream());

					Stage newStage = new Stage();
					newStage.setScene(new Scene(loader));
					newStage.show();
					new FadeIn(loader).play();
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}
		});

	}

	public void updateStatus() {
		Swing swing = new Swing(changeStatuesButton);
		swing.play();
		UserSettings userSettings = new UserSettings();
		
		////////
		UserDao currentUser = new UserDao();
		User retreivedUser = currentUser.retrieveByPhoneNumber("9380283098");//session managmenttttttttttttttttt
		////////
		
		final ContextMenu contextMenu = new ContextMenu();
		contextMenu.setMinSize(30, 200);
		
		MenuItem Available = new MenuItem("Available");
		MenuItem busy = new MenuItem("busy");
		MenuItem away = new MenuItem("Away");
		MenuItem appearOffline = new MenuItem("Appear Offline");
		
		contextMenu.getItems().addAll(Available, busy,appearOffline);
		
		Available.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				
				try {
					userSettings.updateStatus(UserStatus.AVAILABLE);
				}  catch (RemoteException e) {
					
					e.printStackTrace();
				} catch (UpdateUserFailedException e) {
					
					e.printStackTrace();
				}
			}
		});
		busy.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				
				try {
					userSettings.updateStatus(UserStatus.BUSY);
				}  catch (RemoteException e) {
					
					e.printStackTrace();
				} catch (UpdateUserFailedException e) {
					
					e.printStackTrace();
				}
			}
		});
		away.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {  
				
				try {
					userSettings.updateStatus(UserStatus.AWAY);
				}  catch (RemoteException e) {
					
					e.printStackTrace();
				} catch (UpdateUserFailedException e) {
					
					e.printStackTrace();
				}
			}
		});
		appearOffline.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {  
				
				try {
					userSettings.updateStatus(UserStatus.OFFLINE);
				}  catch (RemoteException e) {
					
					e.printStackTrace();
				} catch (UpdateUserFailedException e) {
					
					e.printStackTrace();
				}
			}
		});
		changeStatuesButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				contactBox = contactArea.getSelectionModel().getSelectedItem();
				
				if (event.isSecondaryButtonDown()) {
					contextMenu.show(contactBox, event.getScreenX(), event.getScreenY());
				}
				if (event.isPrimaryButtonDown()) {
					contextMenu.hide();
				}
			}
		});

	}

	public void addGroups() 
	{
		groupButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Swing swing = new Swing(groupButton);
				swing.play();
				FXMLLoader chatloader = new FXMLLoader();
				Parent loader;
				try {
					AddNewGroupController controller = new AddNewGroupController();
					chatloader.setController(controller);
					loader = chatloader
							.load(getClass().getResource("/client/dashboard/addNewGroup.fxml").openStream());
					Stage newStage = new Stage();
					newStage.setScene(new Scene(loader));
					newStage.setMaxHeight(400);
					newStage.setMaxWidth(700);
					newStage.setMinHeight(400);
					newStage.setMinWidth(700);
					newStage.show();
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}
		});

	}

	public void addFriend() {
		addFriendButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Swing swing = new Swing(addFriendButton);
				swing.play();
				FXMLLoader chatloader = new FXMLLoader();
				Parent loader;
				try {
					AddNewFriendController controller = new AddNewFriendController();
					chatloader.setController(controller);
					loader = chatloader
							.load(getClass().getResource("/client/dashboard/addAnotherPhoneDesgin.fxml").openStream());
					Stage newStage = new Stage();
					newStage.setScene(new Scene(loader));
					newStage.setMaxHeight(400);
					newStage.setMaxWidth(700);
					newStage.setMinHeight(400);
					newStage.setMinWidth(700);
					newStage.show();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});

	}

	public void requestNotification() //add requests
	{
		requestsNotificationButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Swing swing = new Swing(requestsNotificationButton);
				swing.play();
				FXMLLoader chatloader = new FXMLLoader();
				Parent loader;
				try {
					ListFriendController controller = new ListFriendController();
					chatloader.setController(controller);
					loader = chatloader
							.load(getClass().getResource("/client/requestNotification/FXMLlistFriendNotification.fxml").openStream());
					Stage newStage = new Stage();
					newStage.setScene(new Scene(loader));
					newStage.setMaxHeight(400);
					newStage.setMaxWidth(700);
					newStage.setMinHeight(400);
					newStage.setMinWidth(700);
					newStage.show();
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}
		});

	}

	public void notificationStatus() {
		notificationStatuesButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Swing swing = new Swing(notificationStatuesButton);
				swing.play();
				/*sound = new Media(new File(
						"C:\\Users\\PC\\Documents\\NetBeansProjects\\JavaChatApp\\Chat\\Gui\\src\\main\\resources\\client\\dashboard\\sound.mp3")
								.toURI().toString());
				player = new MediaPlayer(sound);
				player.play();
				Notifications notification = Notifications.create().title("download completed !")
						.text("saved in : E/newfolder").hideAfter(Duration.seconds(5)).graphic(null)
						.position(Pos.BOTTOM_RIGHT);

				notification.showInformation();*/
				
				FXMLLoader chatloader = new FXMLLoader();
				Parent loader;
				try {
					ListStatusController controller = new ListStatusController();
					chatloader.setController(controller);
					loader = chatloader
							.load(getClass().getResource("/client/statuesNotification/FXMLItemStatusNotification.fxml").openStream());
					Stage newStage = new Stage();
					newStage.setScene(new Scene(loader));
					newStage.setMaxHeight(400);
					newStage.setMaxWidth(700);
					newStage.setMinHeight(400);
					newStage.setMinWidth(700);
					newStage.show();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});

	}
	/**
	 * for sign out from the application
	 */
	public void signOut() {
		signOutButton.setOnMouseClicked((event) -> {
			Swing swing = new Swing(signOutButton);
			swing.play();
			
			////////
			UserDao currentUser = new UserDao();
			User retreivedUser = currentUser.retrieveByPhoneNumber("9380283098");//session managmenttttttttttttttttt
			////////
			IntroduceUser user = new IntroduceUser();
			try {
				
				user.signOut(retreivedUser);
				
			} catch (RemoteException e) {
				
				e.printStackTrace();
				
			} catch (StatusChangeFailedException e) {
				
				e.printStackTrace();
			}
			

		});

	}
	/**
	 * for search in friend list by his name
	 */
	public void searchByName() 
	{
		Search searchByName = new Search();
		try {
			List<Friend> retrievedList = searchByName.searchFriend(searchArea.getText());
			for (int i = 0; i < retrievedList.size(); i++) 
			{				
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/dashboard/contact.fxml"));
				DashboardController controller = new DashboardController();
				fxmlLoader.setController(controller);
				
				try {
						contactBox = fxmlLoader.load();
						
					    friendName.setText(retrievedList.get(i).getName());
					    Image image = new Image(retrievedList.get(i).getPicture().toString());
					    friendImage.setImage(image);
					    
					    Image imageStates=null;
					    
					    if(retrievedList.get(i).getStatus()==UserStatus.AVAILABLE)
					    {
					    	imageStates = new Image("/client/dashboard/online.png");
						    
					    }
					    else if (retrievedList.get(i).getStatus()==UserStatus.AWAY)
					    {
					    	 imageStates = new Image("/client/dashboard/away.png");
						 
					    }
					    else if (retrievedList.get(i).getStatus()==UserStatus.OFFLINE)
					    {
					    	imageStates = new Image("/client/dashboard/offline.png");
						   
					    }
					    friendImage.setImage(imageStates);
						contactArea.getItems().add(contactBox);
		
				} catch (IOException e1) {

					e1.printStackTrace();
				}

			}
			
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * for animation only
	 */
	public void setMenu() {
		contactArea.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Pulse pulse = new Pulse(contactArea);
				pulse.play();
				
			}
		});
	}
	public void addTextSetting()	
	{
		textSetting.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Swing swing = new Swing(textSetting);
				swing.play();				
				FXMLLoader chatloader = new FXMLLoader();
				Parent loader;
				try {
					TextSettingController controller = new TextSettingController ();
					chatloader.setController(controller);
					loader = chatloader
							.load(getClass().getResource("/client/dashboard/textSetting.fxml").openStream());
					Stage newStage = new Stage();
					newStage.setScene(new Scene(loader));
					newStage.setMaxHeight(400);
					newStage.setMaxWidth(700);
					newStage.setMinHeight(400);
					newStage.setMinWidth(700);
					newStage.show();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				


			}
		});
	}
	/**
	 * for sending files by current user
	 */
	public void sendFile() {
			
	}
}
