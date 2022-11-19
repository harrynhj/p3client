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
	Client clientConnection;
	
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

}
