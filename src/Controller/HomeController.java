package Controller;

import java.io.IOException;
import java.awt.Window;
import java.io.*;
import java.lang.Thread;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.*;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeController {
	@FXML
	private Rectangle diceRec;
	@FXML
	private GridPane gridPane;
	@FXML
	private Rectangle cellRectangle;
	@FXML
	private VBox VBoxboard;
	@FXML
	private Button startgameBTN;
	
	private Label templbl;
	static Scene scene;

	FXMLLoader fxmlLoader = new FXMLLoader();
	public void onClick() {

        try { 
        	fxmlLoader.setLocation(getClass().getResource("../application/easyboard.fxml"));
        	scene = new Scene(fxmlLoader.load(), 1900, 900);
            Stage stage = new Stage();
            stage.setTitle("easy");
            stage.setScene(scene);
            stage.show();
            Stage prevst = (Stage) startgameBTN.getScene().getWindow();
            prevst.hide();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void roll() {
	          	templbl = (Label) scene.lookup("lbl21");
	            if(templbl != null) {
		            templbl.setText("Found! Using lookup()");
	            }
//	            System.out.println(gridPane.getRowConstraints()!=null?gridPane.getRowConstraints().size():0);
//	            initialize();
	}
	
//	@FXML
//    public void initialize() {
//        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.PURPLE};
//
//        // Access row and column count correctly
//        int rows = gridPane.getRowConstraints()!=null?gridPane.getRowConstraints().size():0;
//        int columns = gridPane.getColumnConstraints()!=null?gridPane.getColumnConstraints().size():0;
//
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                int colorIndex = (i * columns + j) % colors.length;
//                Rectangle cell = new Rectangle(100, 100);
//                cell.setFill(colors[colorIndex]);
//                gridPane.add(cell, j, i);
//            }
//        }
//    }
}
