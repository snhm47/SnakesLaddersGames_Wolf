package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class WinnerMessageController {
    
    @FXML
    private Label winnerLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setWinnerName(String winnerName) {
        winnerLabel.setText(winnerName + " wins the game! Congratulations!");
    }

    @FXML
    public void returnToMainMenu(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../View/StartMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
