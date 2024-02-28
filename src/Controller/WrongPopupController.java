package Controller; 
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class WrongPopupController {

    @FXML
    private Label messageLabel;

    @FXML
    private Button okButton;

    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    @FXML
    private void handleClose() {
        // Close the popup window
        okButton.getScene().getWindow().hide();
    }
}
