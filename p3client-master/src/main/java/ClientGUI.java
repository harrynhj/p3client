import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ClientGUI extends Application {

	
	// TextField s1,s2,s3,s4, c1;
	// Button serverChoice,clientChoice,b1;
	// HashMap<String, Scene> sceneMap;
	// GridPane grid;
	// HBox buttonBox;
	// VBox clientBox;
	// Scene startScene;
	// BorderPane startPane;
	// Server serverConnection;
	Client clientConnection;
	
	// ListView<String> listItems, listItems2;
	TextField ip, port;
    Label welcome, sep;
    Button quit, connect;

    String ipString;
    int portInt;

	VBox vb;
    HBox hb1;
    HBox hb2;
    HBox hb3;

	public static void main(String[] args) { launch(args); }

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Client Screen One");
        ip = new TextField();
        port = new TextField();
        quit = new Button("Quit");
        connect = new Button("Connect");
        sep = new Label(":");				
        welcome = new Label("Welcome");
        welcome.setDisable(true);
        
        ip.setPromptText("IP");
        port.setPromptText("Port");

        vb = new VBox();
        hb1 = new HBox();
        hb2 = new HBox(10);
        hb3 = new HBox(75);
        
        hb1.setAlignment(Pos.CENTER);
        hb2.setAlignment(Pos.CENTER);
        hb3.setAlignment(Pos.CENTER);


        hb1.getChildren().addAll(welcome);
        hb2.getChildren().addAll(ip, sep, port);
        
        hb3.getChildren().addAll(quit, connect);
        
        vb.getChildren().addAll(hb1, hb2, hb3);
        vb.setPadding(new Insets(100, 0, 0, 0));
        vb.setSpacing(100);

        quit.setOnAction((event) -> {
            Platform.exit();
            System.exit(0);
		});

        connect.setOnAction((event) -> {
            ipString = ip.getText();
            portInt = Integer.parseInt(port.getText());

            clientConnection = new Client(data -> {
                Platform.runLater(() -> { }); } , ipString, portInt);
                clientConnection.start();
        });
        

        Scene scene = new Scene(vb, 700, 700);
        primaryStage.setScene(scene);
		primaryStage.show();
	};
		
		
	// 	this.clientChoice = new Button("Client");
	// 	this.clientChoice.setStyle("-fx-pref-width: 300px");
	// 	this.clientChoice.setStyle("-fx-pref-height: 300px");
		
		// this.clientChoice.setOnAction(e-> {primaryStage.setScene(sceneMap.get("client"));
		// 									primaryStage.setTitle("This is a client");
		// 									clientConnection = new Client(data->{
		// 					Platform.runLater(()->{listItems2.getItems().add(data.toString());
		// 									});
		// 					});
							
	// 										clientConnection.start();
	// 	});
		
	// 	this.buttonBox = new HBox(400, serverChoice, clientChoice);
	// 	startPane = new BorderPane();
	// 	startPane.setPadding(new Insets(70));
	// 	startPane.setCenter(buttonBox);
		
	// 	startScene = new Scene(startPane, 800,800);
		
	// 	listItems = new ListView<String>();
	// 	listItems2 = new ListView<String>();
		
	// 	c1 = new TextField();
	// 	b1 = new Button("Send");
	// 	b1.setOnAction(e->{clientConnection.send(c1.getText()); c1.clear();});
		
	// 	sceneMap = new HashMap<String, Scene>();
		
	// 	sceneMap.put("server",  createServerGui());
	// 	sceneMap.put("client",  createClientGui());
		
	// 	primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
    //         @Override
    //         public void handle(WindowEvent t) {
    //             Platform.exit();
    //             System.exit(0);
    //         }
    //     });
		
		 
		
	// 	primaryStage.setScene(startScene);
	// 	primaryStage.show();
		
	// }
	
	// public Scene createServerGui() {
		
	// 	BorderPane pane = new BorderPane();
	// 	pane.setPadding(new Insets(70));
	// 	pane.setStyle("-fx-background-color: coral");
		
	// 	pane.setCenter(listItems);
	
	// 	return new Scene(pane, 500, 400);
		
		
	// }
	
	// public Scene createClientGui() {
		
	// 	clientBox = new VBox(10, c1,b1,listItems2);
	// 	clientBox.setStyle("-fx-background-color: blue");
	// 	return new Scene(clientBox, 400, 300);
		
	// }

}
