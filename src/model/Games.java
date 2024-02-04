package model;

import Utils.DiffLevel;

public class Games {
	private int currentPlayerIndex;
    private int numberOfPlayers;
    private DiffLevel difficultyLevel;
    
    
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

	public DiffLevel getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(DiffLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	
	public Games(int currentPlayerIndex, int numberOfPlayers, DiffLevel difficultyLevel) {
		super();
		this.currentPlayerIndex = currentPlayerIndex;
		startNewGame(numberOfPlayers,difficultyLevel);
	}

	// Start a new game with the specified number of players and difficulty level
	public void startNewGame(int numberOfPlayers, DiffLevel difficultyLevel) {

		this.numberOfPlayers = numberOfPlayers;
		this.difficultyLevel = difficultyLevel;
		
     }

	// Displays the current game
    public void displayGameStatus(){
    	System.out.println("currentPlayerIndex "+currentPlayerIndex+" numberOfPlayers "+numberOfPlayers+" difficultyLevel"+difficultyLevel);
    }

    /* this Function must be in Board class
    public void handleGameOver(String winnerName, int gameTime, String difficultyLevel) {
    }
    */
    public static final String BOARD_INITIALIZED = "The game board will be initialized in the size of _";
    public static final String SNAKES_AND_LADDERS_PLACED = "Snakes and ladders are placed on the board";
    public static final String QUESTION_BOX_MESSAGE = "You have landed on a question box. Get ready for the challenge";
    public static final String GAME_WON_MESSAGE = "Congratulations, you reached the end of the board and won the game";

}
