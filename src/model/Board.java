package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
public class Board {
	private DiffLevel level;
    private Square[][] squares;
    private HashMap<Integer,Square> sq;
    private ArrayList<Snakes> Snakes;
    private ArrayList<Ladders> Ladders;
    private ArrayList<Question> Questions;


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
		return Snakes;
	}

	public void setSnakes(ArrayList<Snakes> snakes) {
		this.Snakes = snakes;
	}

	public ArrayList<Ladders> getLadders() {
		return Ladders;
	}

	public void setLadders(ArrayList<Ladders> ladders) {
		this.Ladders = ladders;
	}

	public ArrayList<Question> getQuestions() {
		return Questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.Questions = questions;
	}
    public HashMap<Integer, Square> getSq() {
		return sq;
	}

	public void setSq(HashMap<Integer, Square> sq) {
		this.sq = sq;
	}

	public Board(DiffLevel level, Square[][] squares,HashMap<Integer, Square> sq , ArrayList<Snakes> snakes, ArrayList<Ladders> ladders, ArrayList<Question> questions,ArrayList<Player> players) {
		super();
		this.level = level;
		this.squares = squares;
		this.sq = new HashMap<Integer ,Square>();;
		this.Snakes = snakes;
		this.Ladders = ladders;
		this.Questions = questions;
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
	            	if((i==size-1 && j==0) || (i==0 && j == size-1)) {
	            		squares[i][j] = new Square(SquareType.REGULAR);
	            		sq.put(squares[i][j].getNumber(),squares[i][j]);
	            	}else {
	            		squares[i][j] = new Square(st);
	            		sq.put(squares[i][j].getNumber(),squares[i][j]);
	            	}
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
	            	if((i==size-1 && j==0) || (i==0 && j == size-1)) {
	            		squares[i][j] = new Square(SquareType.REGULAR);
	            		sq.put(squares[i][j].getNumber(),squares[i][j]);
	            	}else {
	            		squares[i][j] = new Square(st);
	            		sq.put(squares[i][j].getNumber(),squares[i][j]);
	            	}
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
	            	if((i==size-1 && j==0) || (i==0 && j==0)) {
	            		squares[i][j] = new Square(SquareType.REGULAR);
	            		sq.put(squares[i][j].getNumber(),squares[i][j]);
	            	}else {
	            		squares[i][j] = new Square(st);
	            		sq.put(squares[i][j].getNumber(),squares[i][j]);
	            	}
	                
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
	            	if((i==size-1 && j==0) || (i==0 && j==0 )) {
	            		squares[i][j] = new Square(SquareType.REGULAR);
	            		sq.put(squares[i][j].getNumber(),squares[i][j]);
	            	}else {
	            		squares[i][j] = new Square(st);
	            		sq.put(squares[i][j].getNumber(),squares[i][j]);
	            	}
	            }
	        }
	    }
	    placeRandomSnakesAndLadders();
	    for(Snakes s : Snakes) {
	    	sq.get(s.getStartSnake()).setOccupi(true);
	    	sq.get(s.getEndSnake()).setOccupi(true);
	    }
	}

    public void placeRandomSnakesAndLadders() {
    	int size = 0;
    	int startSnake = 0;
        int endSnake = 0;
        int startLadder = 0;
        int endLadder = 0;
        int startlevel = 0;
        Random random = new Random();
        if (String.valueOf(level).equals(String.valueOf(DiffLevel.easy))) {
            size = 7;
            
           for (int i = 0; i < 4; i++) {
               // Ensure snakes and ladders don't start or end in the same square
                   startSnake = random.nextInt(size) + 1;
                   endSnake = random.nextInt(size) + 1;
               do {
               	startlevel = random.nextInt(size - i) + 1 ;
               }while((startlevel+i+1 > size) || (endSnake + ((startlevel+i+1)*size) >= size*size));
               Color c = null;
               if (i == 0 ) { 
            	   c = Color.YELLOW;
               }else if(i == 1) {
            	   c = Color.GREEN;
               }else if(i == 2) {
            	   c = Color.BLUE;
               }else {
            	   c = Color.RED;
               }
               Snakes s = new Snakes(c, startSnake + (startlevel*size), endSnake + ((startlevel+i+1)*size));
               Snakes.add(s);
               
               do {
                   startLadder = random.nextInt(size - 1) + 1;
                   endLadder = random.nextInt(size - 1) + 1;
               } while (startLadder == endLadder || startLadder == startSnake || startLadder == endSnake);

               // Ensure start is greater than end for snakes
               if (startSnake < endSnake) {
                   int temp = startSnake;
                   startSnake = endSnake;
                   endSnake = temp;
               }
           }
        } else if (String.valueOf(level).equals(String.valueOf(DiffLevel.medium))) {
            size = 10;;
        } else if (String.valueOf(level).equals(String.valueOf(DiffLevel.hard))) {
            size = 13;
        }
		
    }
//    private void placeSnakesAndLadders(int numSnakesAndLadders, int size, Random random) {
//    	 int startSnake = 0;
//         int endSnake = 0;
//         int startLadder = 0;
//         int endLadder = 0;
//         int startlevel = 0;
//        for (int i = 0; i < numSnakesAndLadders; i++) {
//            // Ensure snakes and ladders don't start or end in the same square
//            do {
//                startSnake = random.nextInt(size - 1) + 1;
//                endSnake = random.nextInt(size - 1) + 1;
//            } while (startSnake == endSnake);
//            do {
//            	startlevel = random.nextInt(size - 1) + 1 ;
//            }while(startlevel+i+1 > size-1);
//
//            do {
//                startLadder = random.nextInt(size - 1) + 1;
//                endLadder = random.nextInt(size - 1) + 1;
//            } while (startLadder == endLadder || startLadder == startSnake || startLadder == endSnake);
//
//            // Ensure start is greater than end for snakes
//            if (startSnake < endSnake) {
//                int temp = startSnake;
//                startSnake = endSnake;
//                endSnake = temp;
//            }
//
//            // Ensure start is smaller than end for ladders
//            if (startLadder > endLadder) {
//                int temp = startLadder;
//                startLadder = endLadder;
//                endLadder = temp;
//            }
//
//            // Ensure snakes and ladders don't end on the last square
//            if (endSnake == size * size) {
//                endSnake = size * size - 1;
//            }
//
//            if (endLadder == size * size) {
//                endLadder = size * size - 1;
//            }
//
//            // TODO: Create Snake and Ladder objects and add them to the respective ArrayLists
//            // Example: snakes.add(new Snakes(startSnake, endSnake));
//            // Example: ladders.add(new Ladders(startLadder, endLadder));
//        }
//    }

    
    public void displayBoard() { }

    public String getSquareContent(int row, int col) {
        return null;
    }

	// Messages
    public static final String PLAYER_TURN_MESSAGE = "It's the player's turn. Roll the dice.";
    public static final String DICE_RESULT_MESSAGE = "The result of the roll is: ";
    public static final String QUESTION_TIME_MESSAGE = "It's time for a question! Answer correctly to advance.";
    public static final String WRONG_ANSWER_MOVE_BACK_MESSAGE = "Oops! Your answer is wrong. Move back _ steps.";
    public static final String CORRECT_ANSWER_MOVE_FORWARD_MESSAGE = "Well done! Your answer is correct. Move forward _ steps.";
    public static final String CORRECT_ANSWER_MESSAGE = "Well done! Your answer is correct.";
    public static final String GAME_OVER_WINNER_MESSAGE = "The game is over, and the winner is: ";

}
