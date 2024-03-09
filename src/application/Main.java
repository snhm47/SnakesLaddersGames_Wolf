package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	 static MediaPlayer mediaPlayer;
	    
	    private static boolean isMusicMuted = false;

	    // Your existing code here...

	    public static void toggleMusic() {
	        isMusicMuted = !isMusicMuted;
	        if (isMusicMuted) {
	            mediaPlayer.pause(); // Pause the music
	        } else {
	            // Code to play or unmute the music
	        	mediaPlayer.play();
	        }
	    }

	    public static boolean isMusicMuted() {
	        return isMusicMuted;
	    }
	    
	    public static void stopMusic() {
	        if (mediaPlayer != null) {
	            mediaPlayer.stop();
	        }
	    }
	    
	    public static void startMusic() {
	        if (mediaPlayer != null) {
	            mediaPlayer.play();
	        }
	    }
	@Override
	public void start(Stage primaryStage) {
		try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/StartMenu.fxml"));
            Scene scene = new Scene(root);
            String musicFile = getClass().getResource("/Music/AngryBirdsTheme.mp3").toExternalForm();
            Media media = new Media(musicFile);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setVolume(0.3); // Set volume to 30%
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Snakes and Ladders");
			primaryStage.setResizable(false);
			primaryStage.show();

			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}