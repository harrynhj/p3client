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
import javafx.util.Pair;

public class GameButton extends Button {
    public int x;
    public int y;

    public int player;

    GameButton(int x, int y) {
        this.x = x;
        this.y = y;
        this.player = 0;
    }

    public Pair<Integer,Integer> gravity(GameButton[][] matrix, int x, int y, int move) {
        if(x+1 == matrix.length) {
            matrix[x][y].setDisable(true);
            if(move % 2 == 0) {
                matrix[x][y].setStyle("-fx-background-color: #000000"); // *black 1
                matrix[x][y].setDisable(true);
                matrix[x][y].player = 1;
            }
            else {
                matrix[x][y].setStyle("-fx-background-color: #ff0000"); // *red 2
                matrix[x][y].setDisable(true);
                matrix[x][y].player = 2;
            }
            return new Pair<>(x,y);
        }

        if(matrix[x + 1][y].isDisabled()) {
            matrix[x][y].setDisable(true);
            if(move % 2 == 0) {
                matrix[x][y].setStyle("-fx-background-color: #000000"); // *black 1
                matrix[x][y].setDisable(true);
                matrix[x][y].player = 1;
            }
            else {
                matrix[x][y].setStyle("-fx-background-color: #ff0000"); // *red 2
                matrix[x][y].setDisable(true);
                matrix[x][y].player = 2;
            }
            return new Pair<>(x,y);
        }



        int i;
        for(i = x + 1; i < matrix.length;i++) {
            if(matrix[i][y].isDisabled())
                break;
        }

        if(move % 2 == 0) {
            matrix[i-1][y].setStyle("-fx-background-color: #000000"); // *black 1
            matrix[i-1][y].setDisable(true);
            matrix[i-1][y].player = 1;
        } else {
            matrix[i-1][y].setStyle("-fx-background-color: #ff0000"); // *red 2
            matrix[i-1][y].setDisable(true);
            matrix[i-1][y].player = 2;
        }

        matrix[x][y].setDisable(false);

        return new Pair<>(i-1,y);
    }


    public boolean evalWinning(Pair<Integer,Integer> xy, GameButton[][] matrix, int player) {
        int x = xy.getKey();
        int y = xy.getValue();
        int checkNum;
        if (player==0) {
            checkNum = 1;
        } else {
            checkNum = 2;
        }

        int rowCnt = 1;
        // check left row
        for (int i = y; i >= 0;i--) {
            if (matrix[x][i].player != checkNum) {
                break;
            }
            if (i < y) {
                rowCnt++;
            }
        }
        // check right row
        for (int i = y; i < 7;i++) {
            if (matrix[x][i].player != checkNum) {
                break;
            }
            if (i > y) {
                rowCnt++;
            }

        }

        if (rowCnt >= 4) {
            return true;
        }

        int colCnt = 1;
        // check up col
        for (int i = x; i >= 0;i--) {
            if (matrix[i][y].player != checkNum) {
                break;
            }
            if (i < x) {
                colCnt++;
            }
        }
        // check right row
        for (int i = x; i < 6;i++) {
            if (matrix[i][y].player != checkNum) {
                break;
            }
            if (i > x) {
                colCnt++;
            }

        }
        if (colCnt >= 4) {
            return true;
        }


        int inclineCnt = 1;
        for (int i = x,j=y;i >= 0;i--,j--) {
            if (j == -1) {
                break;
            }
            if (matrix[i][j].player != checkNum) {
                break;
            }
            if (i < x || j < y) {
                inclineCnt++;
            }
        }

        for (int i = x,j=y;j < 7;i++,j++) {
            if (i == 6) {
                break;
            }
            if (matrix[i][j].player != checkNum) {
                break;
            }
            if (i > x || j > y) {
                inclineCnt++;
            }
        }

        if (inclineCnt >= 4) {
            return true;
        }

        inclineCnt = 1;
        for (int i = x,j=y;i >= 0;i--,j++) {
            if (j == 7) {
                break;
            }
            if (matrix[i][j].player != checkNum) {
                break;
            }
            if (i < x || j > y) {
                inclineCnt++;
            }
        }

        for (int i = x,j=y;j >= 0;i++,j--) {
            if (i == 6) {
                break;
            }
            if (matrix[i][j].player != checkNum) {
                break;
            }
            if (i > x || j < y) {
                inclineCnt++;
            }
        }
        return inclineCnt >= 4;
    }

    // public int sendResult() {	}

}