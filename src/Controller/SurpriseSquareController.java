package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SurpriseSquareController {

	private GameSetupController gameController;

	public void setGameController(GameSetupController gameController) {
		this.gameController = gameController;
	}

	@FXML
	private Button surpriseButton;

	@FXML
	private void handleSurprise() {
		if (gameController != null) {
			// gameController.movePlayer(10);
			// Move the player 10 squares
		}
	}
}
