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

public class GameButton extends Button {
    public int x;
    public int y;

    GameButton(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void gravity(GameButton[][] matrix, int x, int y, int move) {
        if(x+1 == matrix.length) {
            matrix[x][y].setDisable(true);
            return;
        }

        // if(matrix[x+1][y].isDisabled() == true)
        //     matrix[x+1][y].setDisable(true);

        
        int i;
        for(i = x + 1; i < matrix.length;i++) {
            if(matrix[i][y].isDisabled())
                break;
        }

        if(move % 2 == 0) {
            matrix[i-1][y].setStyle("-fx-background-color: #000000"); // *black 1
            matrix[i-1][y].setDisable(true);
        }
        else {
            matrix[i-1][y].setStyle("-fx-background-color: #ff0000"); // *red 2
            matrix[i-1][y].setDisable(true);
        }

        matrix[x][y].setDisable(false);

    }

    // public int sendResult() {    }

}