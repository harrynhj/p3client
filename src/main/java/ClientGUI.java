import java.util.HashMap;
import java.util.logging.Handler;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ClientGUI extends Application {
	Client clientConnection;
	
	VBox vb, vb2;
    HBox hb1, hb2, hb3, hb4, hb5, hb6;
	GridPane gp;
	Button quit, connect;

	TextField ip, port;
    Label welcome, sep, error, turnOrder, prevMove;
    
    String ipString;
    int portInt, row, col, move;
	
	public static void main(String[] args) { launch(args); }

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Connect Four!");
        ip = new TextField();
        port = new TextField();
        quit = new Button("Quit");
        connect = new Button("Connect");
        sep = new Label(":");				
        welcome = new Label("Welcome");
        welcome.setDisable(true);
        error = new Label();

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
				// clientConnection.send("Connecting");

				// if(ip.getText() != "127.0.0.1" && port.getText() != "5555") {
				// 	System.out.println(ip.getText());
				// 	System.out.println(port.getText());
				// 	error.setText("IP or Port Invalid!");
				// 	error.setVisible(true);
				// 	error.setTextFill(Color.color(1,0,0));
				// 	error.setDisable(false);
				// 	port.clear();
				// 	port.setDisable(false);
				// } else {
				
				gp = new GridPane();
				turnOrder = new Label();
				turnOrder.setStyle("-fx-font-size: 24px; -fx-background-radius: 20px");
				prevMove = new Label();
				prevMove.setStyle("-fx-font-size: 24px; -fx-background-radius: 20px");
				
				move = 0;
				
				EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						GameButton b = (GameButton)e.getSource();
						row = Math.abs(GridPane.getRowIndex(b) - 5);
						col = GridPane.getColumnIndex(b);

						
						b.setDisable(true);
						if(move % 2 == 0) {
							b.setStyle("-fx-background-color: #000000"); // *black 1
							turnOrder.setText("Player 1 Turn");
							prevMove.setText("Player 1 moved " + row + "," + col);
						}
						else {
							b.setStyle("-fx-background-color: #ff0000"); // *red 2
							turnOrder.setText("Player 2 Turn");
							prevMove.setText("Player 2 moved " + row + "," + col);
						}
						move++;
					}
				};

				for(int i = 0; i < 7; i++) {
					for(int j = 0; j < 6; j++) {
						GameButton b = new GameButton();
						b.setMinSize(75,75);
						b.setOnAction(handler);
						gp.add(b, i, j);
					}
				}
				gp.setHgap(10);
				gp.setVgap(10);

				vb2 = new VBox();
				hb4 = new HBox();
				hb5 = new HBox();
				hb6 = new HBox();

				hb4.getChildren().add(turnOrder);
				hb5.getChildren().add(prevMove);
				hb6.getChildren().add(gp);
				
				hb4.setAlignment(Pos.CENTER);
				hb5.setAlignment(Pos.CENTER);
				hb6.setAlignment(Pos.CENTER);
				vb2.setPadding(new Insets(60, 0, 0, 0));
				vb2.setSpacing(30);

				vb2.getChildren().addAll(hb4, hb5, hb6);

				Scene sceneTwo = new Scene(vb2, 700, 700);
				primaryStage.setScene(sceneTwo);
				primaryStage.show();

			
			// }

        });


        Scene scene = new Scene(vb, 700, 700);
        primaryStage.setScene(scene);
		primaryStage.show();
	};
		

}
