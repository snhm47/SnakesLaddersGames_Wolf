package model;

import java.util.ArrayList;
public class Board {
	private DiffLevel level;
    private int[][] squares;
    private ArrayList<Snakes> snakes;
    private ArrayList<Ladders> ladders;
    private ArrayList<Question> questions;
    private ArrayList<Player> players;


    public DiffLevel getLevel() {
		return level;
	}

	public void setLevel(DiffLevel level) {
		this.level = level;
	}

	public int[][] getSquares() {
		return squares;
	}

	public void setSquares(int[][] squares) {
		this.squares = squares;
	}

	public ArrayList<Snakes> getSnakes() {
		return snakes;
	}

	public void setSnakes(ArrayList<Snakes> snakes) {
		this.snakes = snakes;
	}

	public ArrayList<Ladders> getLadders() {
		return ladders;
	}

	public void setLadders(ArrayList<Ladders> ladders) {
		this.ladders = ladders;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public Board(DiffLevel level, int[][] squares, ArrayList<Snakes> snakes, ArrayList<Ladders> ladders, ArrayList<Question> questions) {
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
