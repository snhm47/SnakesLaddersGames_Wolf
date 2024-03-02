package Controller;
import java.io.IOException;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    void loginButtonClicked1(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("admin") && password.equals("admin123")) {
            
            // Main.loadScene("OtherScreen.fxml");
            System.out.println("Login successful"); // Placeholder for navigation code
        } else {
            errorLabel.setText("Wrong username/password");
        }
    }
   
    @FXML
    public void returnBackButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Home2.fxml"));
        Parent mainPageParent = loader.load();
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        mainPageParent.setTranslateY(window.getHeight());
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), mainPageParent);
        transition.setToY(0); 
        transition.play();
        
        System.out.println("Returning back"); 
    }

}
