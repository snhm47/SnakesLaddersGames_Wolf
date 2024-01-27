package model;

import java.util.List;
public class Board {
	private String level;
    private int[][] squares;
    private List<Snakes> snakes;
    private List<Ladders> ladders;
    private List<Question> questions;


    public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int[][] getSquares() {
		return squares;
	}

	public void setSquares(int[][] squares) {
		this.squares = squares;
	}

	public List<Snakes> getSnakes() {
		return snakes;
	}

	public void setSnakes(List<Snakes> snakes) {
		this.snakes = snakes;
	}

	public List<Ladders> getLadders() {
		return ladders;
	}

	public void setLadders(List<Ladders> ladders) {
		this.ladders = ladders;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Board(String level, int[][] squares, List<Snakes> snakes, List<Ladders> ladders, List<Question> questions) {
		super();
		this.level = level;
		this.squares = squares;
		this.snakes = snakes;
		this.ladders = ladders;
		this.questions = questions;
	}

	public void initializeBoard(int size) {  }

    public void placeRandomSnakesAndLadders() { }

    public void displayBoard() { }

    public String getSquareContent(int row, int col) {
        return null;}

    // Messages
    public static final String PLAYER_TURN_MESSAGE = "It's the player's turn. Roll the dice.";
    public static final String DICE_RESULT_MESSAGE = "The result of the roll is: ";
    public static final String QUESTION_TIME_MESSAGE = "It's time for a question! Answer correctly to advance.";
    public static final String WRONG_ANSWER_MOVE_BACK_MESSAGE = "Oops! Your answer is wrong. Move back _ steps.";
    public static final String CORRECT_ANSWER_MOVE_FORWARD_MESSAGE = "Well done! Your answer is correct. Move forward _ steps.";
    public static final String CORRECT_ANSWER_MESSAGE = "Well done! Your answer is correct.";
    public static final String GAME_OVER_WINNER_MESSAGE = "The game is over, and the winner is: ";

}
