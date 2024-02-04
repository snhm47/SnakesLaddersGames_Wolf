package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
public class Board {
	private DiffLevel level;
    private Square[][] squares;
    private HashMap<Integer,Square> sq;
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

	public ArrayList<Snakes> getsnakes() {
		return snakes;
	}

	public void setsnakes(ArrayList<Snakes> snakes) {
		this.snakes = snakes;
	}

	public ArrayList<Ladders> getladders() {
		return ladders;
	}

	public void setladders(ArrayList<Ladders> ladders) {
		this.ladders = ladders;
	}

	public ArrayList<Question> getquestions() {
		return questions;
	}

	public void setquestions(ArrayList<Question> questions) {
		this.questions = questions;
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
		this.snakes = new ArrayList<Snakes>();
		this.ladders = new ArrayList<Ladders>();
		this.questions = questions;
	}

	public void initializeBoard() {
		int size = 0 ;
		int quesCount = 0;
		int surpCount = 0 ;
		if(String.valueOf(level).equals(String.valueOf(DiffLevel.easy))) {
			size = 7;
			quesCount = 3 ; 
			surpCount = 0 ;
			System.out.println(size + " " + surpCount + " " + quesCount);
		}else if(String.valueOf(level).equals(String.valueOf(DiffLevel.medium))) {
			size = 10;
			quesCount = 3 ; 
			surpCount = 1 ;
			System.out.println(size + " " + surpCount + " " + quesCount);
		}else if(String.valueOf(level).equals(String.valueOf(DiffLevel.hard))) {
			size = 13;
			quesCount = 3 ; 
			surpCount = 2 ;
			System.out.println(size + " " + surpCount + " " + quesCount);
		}
		squares = new Square[size][size];
		
		Random random = new Random();
		int stRandj = 0;
		int stRandi = 0;
	    for (int i = size-1; i >= 0; i--) {
	        if ((i % 2 == 0)&&(size != 10 )) {
	            for (int j = 0; j < size; j++) {
		            squares[i][j] = new Square(SquareType.REGULAR);
	           		sq.put(squares[i][j].getNumber(),squares[i][j]);
	            }
	        } else if((i % 2 != 0)&&(size != 10 )) {
	            for (int j = size - 1; j >= 0; j--) {	            		            
		            squares[i][j] = new Square(SquareType.REGULAR);	            	
	           		sq.put(squares[i][j].getNumber(),squares[i][j]);
	            }
	        }else if((i % 2 == 0)&&(size == 10 )) {
	        	for (int j = size - 1; j >= 0; j--) {
	            	squares[i][j] = new Square(SquareType.REGULAR);
	            	sq.put(squares[i][j].getNumber(),squares[i][j]);
	            }
	        }else if((i % 2 != 0)&&(size == 10 )) {
	        	for (int j = 0; j < size; j++) {
	            	squares[i][j] = new Square(SquareType.REGULAR);
	            	sq.put(squares[i][j].getNumber(),squares[i][j]);
	            }
	        }
	    }
	    ArrayList<Integer> rowQues = new ArrayList<Integer>();
	    for(int k = 0; k<quesCount ; k++) {
	    	do {
		    	stRandi = random.nextInt(size);
		    	stRandj = random.nextInt(size);
	    	}while((stRandi == size && stRandj == 0)|| (rowQues.contains(stRandi))|| (stRandi == 0 && stRandj == size)) ;
	    	squares[stRandi][stRandj].setSquareType(SquareType.QUESTION);
	    	rowQues.add(stRandi);
	    }
	    for(int k = 0 ; k<surpCount ; k++) {
	    	do {
	    		stRandi = random.nextInt(size);
	    		stRandj = random.nextInt(size);
	    	}while(((stRandi == size && stRandj == 0) || (stRandi == 0 && stRandj == size)) && (!squares[stRandi][stRandj].getSquareType().equals(SquareType.QUESTION)) ) ;
	    	squares[stRandi][stRandj].setSquareType(SquareType.SURPRISE);
	    }
	    placeRandomsnakesAndLaddrs();
	}

    public void placeRandomsnakesAndLaddrs() {
    	int size = 0;
    	int startSnake = 0;
        int endSnake = 0;
        int startlevel = 0;
        int startLadder = 0;
        int endLadder = 0;
		ArrayList<Integer>ends = new ArrayList<Integer>();
		ArrayList<Integer>starts = new ArrayList<Integer>();
		ArrayList<Integer>startl = new ArrayList<Integer>();
        Random random = new Random();
        if (String.valueOf(level).equals(String.valueOf(DiffLevel.easy))) {
            size = 7;   
           for (int i = 0; i < 4; i++) {
               // Ensure snakes and ladders don't start or end in the same square
        	   do {
	               startSnake = random.nextInt(size) + 1;
	               endSnake = random.nextInt(size);           
               	   startlevel = random.nextInt(size - i) + 1 ;
               }while((startlevel+i+1 > size) || (endSnake+1 + ((startlevel+i+1)*size) >= size*size) || starts.contains(startSnake + (startlevel*size)) || (ends.contains(endSnake+1 + ((startlevel+i+1)*size))));
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
               Snakes s = new Snakes(c, startSnake + (startlevel*size), endSnake+1 + ((startlevel+i+1)*size));
               snakes.add(s);
               ends.add(endSnake+1 + ((startlevel+i+1)*size));
               starts.add(startSnake + (startlevel*size));
               
           }
           for(int i = 0 ; i < 4 ; i++) {
       		do {
       			startLadder = random.nextInt(size);
       			endLadder = random.nextInt(size);
       			startlevel = random.nextInt(size-i)+1;
       		}while((startlevel+i+1 > size) || (endLadder+1 + ((startlevel+i+1)*size) >= size*size) || starts.contains(startLadder + (startlevel*size)) || (ends.contains(endLadder+1 + ((startlevel+i+1)*size))) || (startl.contains(startLadder + (startlevel*size))));
       		
       		LadderType lt = null ;
       		if(i == 0) {
       			lt = LadderType.TYPE_1;
       		}else if(i == 1) {
       			lt = LadderType.TYPE_2;
       		}else if(i == 2) {
       			lt = LadderType.TYPE_3;
       		}else {
       			lt = LadderType.TYPE_4;
       		}
       		Ladders l = new Ladders(lt, startLadder + (startlevel*size), endLadder+1+((startlevel+1+i)*size));
       		ladders.add(l);
       		startl.add(startLadder + (startlevel*size));
       	}
        } else if (String.valueOf(level).equals(String.valueOf(DiffLevel.medium))) {
            size = 10;
            for (int i = 0; i < 6; i++) {
                // Ensure snakes and ladders don't start or end in the same square
         	   do {
 	               startSnake = random.nextInt(size) + 1;
 	               endSnake = random.nextInt(size);           
                	   startlevel = random.nextInt(size - i) + 1 ;
                }while((startlevel+i+1 > size) || (endSnake+1 + ((startlevel+i+1)*size) >= size*size) || starts.contains(startSnake + (startlevel*size)) || (ends.contains(endSnake+1 + ((startlevel+i+1)*size))));
                Color c = null;
                if (i == 0 || i == 1 ) { 
             	   c = Color.YELLOW;
                }else if(i == 2 || i == 3) {
             	   c = Color.GREEN;
                }else if(i == 4) {
             	   c = Color.BLUE;
                }else {
             	   c = Color.RED;
                }
                Snakes s = new Snakes(c, startSnake + (startlevel*size), endSnake+1 + ((startlevel+i+1)*size));
                snakes.add(s);
                ends.add(endSnake+1 + ((startlevel+i+1)*size));
                starts.add(startSnake + (startlevel*size));
            }
            for(int i = 0 ; i < 6 ; i++) {
           		do {
           			startLadder = random.nextInt(size);
           			endLadder = random.nextInt(size);
           			startlevel = random.nextInt(size-i)+1;
           		}while((startlevel+i+1 > size) || (endLadder+1 + ((startlevel+i+1)*size) >= size*size) || starts.contains(startLadder + (startlevel*size)) || (ends.contains(endLadder+1 + ((startlevel+i+1)*size))) || (startl.contains(startLadder + (startlevel*size))));
           		
           		LadderType lt = null ;
           		if(i == 0) {
           			lt = LadderType.TYPE_1;
           		}else if(i == 1) {
           			lt = LadderType.TYPE_2;
           		}else if(i == 2) {
           			lt = LadderType.TYPE_3;
           		}else if(i == 3){
           			lt = LadderType.TYPE_4;
           		}else if(i==4) {
           			lt = LadderType.TYPE_5;
           		}else if(i==5) {
           			lt = LadderType.TYPE_6;
           		}
           		Ladders l = new Ladders(lt, startLadder + (startlevel*size), endLadder+1+((startlevel+1+i)*size));
           		ladders.add(l);
           		startl.add(startLadder + (startlevel*size));
            }
        } else if (String.valueOf(level).equals(String.valueOf(DiffLevel.hard))) {
            size = 13;
            for (int i = 0; i < 8; i++) {
                // Ensure snakes and ladders don't start or end in the same square
         	   do {
 	               startSnake = random.nextInt(size) + 1;
 	               endSnake = random.nextInt(size);           
                	   startlevel = random.nextInt(size - i) + 1 ;
                }while((startlevel+i+1 > size) || (endSnake+1 + ((startlevel+i+1)*size) >= size*size) || starts.contains(startSnake + (startlevel*size)) || (ends.contains(endSnake+1 + ((startlevel+i+1)*size))));
                Color c = null;
                if (i == 0 || i == 1 ) { 
             	   c = Color.YELLOW;
                }else if(i == 2 || i == 3) {
             	   c = Color.GREEN;
                }else if(i == 4 || i == 5) {
             	   c = Color.BLUE;
                }else {
             	   c = Color.RED;
                }
                Snakes s = new Snakes(c, startSnake + (startlevel*size), endSnake+1 + ((startlevel+i+1)*size));
                snakes.add(s);
                ends.add(endSnake+1 + ((startlevel+i+1)*size));
                starts.add(startSnake + (startlevel*size));
            }
            for(int i = 0 ; i < 8 ; i++) {
           		do {
           			startLadder = random.nextInt(size);
           			endLadder = random.nextInt(size);
           			startlevel = random.nextInt(size-i)+1;
           		}while((startlevel+i+1 > size) || (endLadder+1 + ((startlevel+i+1)*size) >= size*size) || starts.contains(startLadder + (startlevel*size)) || (ends.contains(endLadder+1 + ((startlevel+i+1)*size))) || (startl.contains(startLadder + (startlevel*size))));
           		
           		LadderType lt = null ;
           		if(i == 0) {
           			lt = LadderType.TYPE_1;
           		}else if(i == 1) {
           			lt = LadderType.TYPE_2;
           		}else if(i == 2) {
           			lt = LadderType.TYPE_3;
           		}else if(i == 3){
           			lt = LadderType.TYPE_4;
           		}else if(i==4) {
           			lt = LadderType.TYPE_5;
           		}else if(i==5) {
           			lt = LadderType.TYPE_6;
           		}else if(i==6) {
           			lt = LadderType.TYPE_7;
           		}else {
           			lt = LadderType.TYPE_8;
           		}
           		Ladders l = new Ladders(lt, startLadder + (startlevel*size), endLadder+1+((startlevel+1+i)*size));
           		ladders.add(l);
           		startl.add(startLadder + (startlevel*size));
            }
        }
        
	    for(Snakes s : snakes) {
	    	sq.get(s.getStartSnake()).setOccupi(true);
	    	sq.get(s.getEndSnake()).setOccupi(true);
	    }
	    for(Ladders l : ladders) {
	    	sq.get(l.getStartLadder()).setOccupi(true);
	    	sq.get(l.getEndLadder()).setOccupi(true);
	    }
    }
//    public void placeRandomladder() {
//    	int size = 0;
//    	int startLadder = 0;
//        int endLadder = 0;
//        int startlevel = 0;
//        Random random = new Random();
//        if (String.valueOf(level).equals(String.valueOf(DiffLevel.easy))) {
//        	size = 7;
//        	for(int i = 0 ; i < 4 ; i++) {
//        		do {
//        			startLadder = random.nextInt(size);
//        			endLadder = random.nextInt(size);
//        			startlevel = random.nextInt(size-i)+1;
//        		}while((startlevel+i+1 > size) || (endLadder+1 + ((startlevel+i+1)*size) >= size*size) || (sq.get(squares[startlevel+i][endLadder].getNumber()).getOccupi()));
//        		
//        		LadderType lt = null ;
//        		if(i == 0) {
//        			lt = LadderType.TYPE_1;
//        		}else if(i == 1) {
//        			lt = LadderType.TYPE_2;
//        		}else if(i == 2) {
//        			lt = LadderType.TYPE_3;
//        		}else {
//        			lt = LadderType.TYPE_4;
//        		}
//        		Ladders l = new Ladders(lt, startLadder + (startlevel*size), endLadder+1+((startlevel+1+i)*size));
//        		ladders.add(l);
//        	}
//        }else if (String.valueOf(level).equals(String.valueOf(DiffLevel.medium))) {
//            size = 10;;
//        } else if (String.valueOf(level).equals(String.valueOf(DiffLevel.hard))) {
//            size = 13;
//        }
//               
//    }
//    private void placesnakesAndladders(int numsnakesAndladders, int size, Random random) {
//    	 int startSnake = 0;
//         int endSnake = 0;
//         int startLadder = 0;
//         int endLadder = 0;
//         int startlevel = 0;
//        for (int i = 0; i < numsnakesAndladders; i++) {
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
//            // Example: snakes.add(new snakes(startSnake, endSnake));
//            // Example: ladders.add(new ladders(startLadder, endLadder));
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
