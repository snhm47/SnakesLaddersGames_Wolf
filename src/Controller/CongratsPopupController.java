package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CongratsPopupController {

    @FXML
    private Label messageLabel;

    @FXML
    private Button okButton;

    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    @FXML
    private void handleClose() {
        // Close the pop-up window
        okButton.getScene().getWindow().hide();
    }
}
