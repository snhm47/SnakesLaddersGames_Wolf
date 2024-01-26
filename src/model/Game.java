package model;

public class Game {
	private int currentPlayerIndex;
    private int numberOfPlayers;
    private String difficultyLevel;
    
    
    public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}

	public void setCurrentPlayerIndex(int currentPlayerIndex) {
		this.currentPlayerIndex = currentPlayerIndex;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	
	public Game(int currentPlayerIndex, int numberOfPlayers, String difficultyLevel) {
		super();
		this.currentPlayerIndex = currentPlayerIndex;
		this.numberOfPlayers = numberOfPlayers;
		this.difficultyLevel = difficultyLevel;
	}

	public void startNewGame(int numberOfPlayers, String difficultyLevel) {
     }

    public void displayGameStatus() {
     }

    public void handleGameOver(String winnerName, int gameTime, String difficultyLevel) {
     }

    public static final String BOARD_INITIALIZED = "The game board will be initialized in the size of _";
    public static final String SNAKES_AND_LADDERS_PLACED = "Snakes and ladders are placed on the board";
    public static final String QUESTION_BOX_MESSAGE = "You have landed on a question box. Get ready for the challenge";
    public static final String GAME_WON_MESSAGE = "Congratulations, you reached the end of the board and won the game";

}
