package model;

import java.util.ArrayList;
import java.util.Random;
public class Board {
	private DiffLevel level;
    private Square[][] squares;
    private ArrayList<Snakes> snakes;
    private ArrayList<Ladders> ladders;
    private ArrayList<Question> questions;


    public DiffLevel getLevel() {
		return level;
	}

	public void setLevel(DiffLevel level) {
		this.level = level;
	}

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
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

	public Board(DiffLevel level, Square[][] squares, ArrayList<Snakes> snakes, ArrayList<Ladders> ladders, ArrayList<Question> questions,ArrayList<Player> players) {
		super();
		this.level = level;
		this.squares = squares;
		this.snakes = snakes;
		this.ladders = ladders;
		this.questions = questions;
	}

	public void initializeBoard() {
		int size = 0 ;
		if(String.valueOf(level).equals(String.valueOf(DiffLevel.easy))) {
			size = 7;
			System.out.println(size);
		}else if(String.valueOf(level).equals(String.valueOf(DiffLevel.medium))) {
			size = 10;
			System.out.println(size);
		}else if(String.valueOf(level).equals(String.valueOf(DiffLevel.hard))) {
			size = 13;
			System.out.println(size);
		}
		squares = new Square[size][size];
		Random random = new Random();
		SquareType st = null ;
		int stRand = 0;
	    for (int i = size-1; i >= 0; i--) {
	        if ((i % 2 == 0)&&(size != 10 )) {
	            for (int j = 0; j < size; j++) {
	            	stRand = random.nextInt(3)+1;
	            	if(stRand == 1) {
	            		st = SquareType.REGULAR;
	            	}else if(stRand == 2) {
	            		st = SquareType.QUESTION;
	            	}else if(stRand == 3) {
	            		st = SquareType.SURPRISE;
	            	}
	                squares[i][j] = new Square(st);
	            }
	        } else if((i % 2 != 0)&&(size != 10 )) {
	            for (int j = size - 1; j >= 0; j--) {
	            	stRand = random.nextInt(3)+1;
	            	if(stRand == 1) {
	            		st = SquareType.REGULAR;
	            	}else if(stRand == 2) {
	            		st = SquareType.QUESTION;
	            	}else if(stRand == 3) {
	            		st = SquareType.SURPRISE;
	            	}
	                squares[i][j] = new Square(st);
	            }
	        }else if((i % 2 == 0)&&(size == 10 )) {
	        	for (int j = size - 1; j >= 0; j--) {
	        		stRand = random.nextInt(3)+1;
	            	if(stRand == 1) {
	            		st = SquareType.REGULAR;
	            	}else if(stRand == 2) {
	            		st = SquareType.QUESTION;
	            	}else if(stRand == 3) {
	            		st = SquareType.SURPRISE;
	            	}
	                squares[i][j] = new Square(st);
	            }
	        }else if((i % 2 != 0)&&(size == 10 )) {
	        	for (int j = 0; j < size; j++) {
	        		stRand = random.nextInt(3)+1;
	            	if(stRand == 1) {
	            		st = SquareType.REGULAR;
	            	}else if(stRand == 2) {
	            		st = SquareType.QUESTION;
	            	}else if(stRand == 3) {
	            		st = SquareType.SURPRISE;
	            	}
	                squares[i][j] = new Square(st);
	            }
	        }
	    }
	}

    public void placeRandomSnakesAndLadders() {
//    	int size = 0 ;
//    	Random random = new Random();
//    	int startCoor = 0 ;
//    	int endCoor = 0 ;
//		if(String.valueOf(level).equals(String.valueOf(DiffLevel.easy))) {
//			size = 7;
//			for(int i = 0 ; i <= 4 ; i++) {
//				startCoor = random.nextInt(size)+1 ; 
//				endCoor = random.nextInt(size)+1 ; 
//			}
//			System.out.println(size);
//		}else if(String.valueOf(level).equals(String.valueOf(DiffLevel.medium))) {
//			size = 10;
//			System.out.println(size);
//		}else if(String.valueOf(level).equals(String.valueOf(DiffLevel.hard))) {
//			size = 13;
//			System.out.println(size);
//		}
//		
    }

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
