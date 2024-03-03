package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Admin;

public class AdminController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	Admin admin = new Admin("admin","admin");
	
	@FXML
    private TextField aTf;

    @FXML
    private PasswordField passTf;
    
	@FXML
	public void LoginAdmin(ActionEvent event) throws IOException {
	    if (admin.getUserName().equals(aTf.getText()) && admin.getPass().equals(passTf.getText())) {
	        root = FXMLLoader.load(getClass().getResource("/View/StartMenuAfterAdmin.fxml"));
	        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        this.stage.hide();
	        this.stage.close();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    } else {
	        // Display an error message if the username or password is incorrect
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Login Failed");
	        alert.setHeaderText(null);
	        alert.setContentText("Incorrect username or password. Please try again.");
	        alert.showAndWait();
	    }
	}
    
	
    private void setFullscreen() {
        stage.setResizable(false);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
    }


}
