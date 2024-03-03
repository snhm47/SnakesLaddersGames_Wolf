package model;

import java.util.ArrayList;
import java.util.Random;

import Utils.Color;
import Utils.DiffLevel;
import Utils.LadderType;
import Utils.SquareType;

public class HardBoard extends Board{
	
	public static HardBoard instanceHardBoard;
	public static synchronized HardBoard getInstance() {
		if (instanceHardBoard == null)
			instanceHardBoard = new HardBoard();

	    return instanceHardBoard;
	}

	public HardBoard() {
		super(DiffLevel.hard);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeBoard() {

		int size = 13;
		int quesCount = 3 ; 
		int surpCount = 2 ;
		System.out.println(size + " " + surpCount + " " + quesCount);
		
		setSquares(new Square[size][size]);
		
		Random random = new Random();
		int stRandj = 0;
		int stRandi = 0;
	    for (int i = size-1; i >= 0; i--) {
	    	 	if (i % 2 == 0) {
		         	for (int j = 0; j < size; j++) {
		         		getSquares()[i][j] = new Square(SquareType.REGULAR);
		           		getSq().put(getSquares()[i][j].getNumber(),getSquares()[i][j]);
		            }
		        } else if(i % 2 != 0) {
		            for (int j = size - 1; j >= 0; j--) {	            		            
		            	getSquares()[i][j] = new Square(SquareType.REGULAR);	            	
		           		getSq().put(getSquares()[i][j].getNumber(),getSquares()[i][j]);
		            }
		        }	 
	    }
	    ArrayList<Integer> rowQues = new ArrayList<Integer>();
	    for(int k = 0; k<quesCount ; k++) {
	    	do {
		    	stRandi = random.nextInt(size);
		    	stRandj = random.nextInt(size);
	    	}while((stRandi == size-1 && stRandj == 0)|| (rowQues.contains(stRandi))|| (stRandi == 0 && stRandj == size-1)) ;
	    	getSquares()[stRandi][stRandj].setSquareType(SquareType.QUESTION);
	    	rowQues.add(stRandi);
	    }
	    for(int k = 0 ; k<surpCount ; k++) {
	    	do {
	    		stRandi = random.nextInt(size);
	    		stRandj = random.nextInt(size);
	    	}while(((stRandi == size-1 && stRandj == 0) || (stRandi == 0 && stRandj == size-1)) && (!getSquares()[stRandi][stRandj].getSquareType().equals(SquareType.QUESTION)) ) ;
	    	getSquares()[stRandi][stRandj].setSquareType(SquareType.SURPRISE);
	    }
	    placeRandomsnakesAndLaddrs();
	}

	@Override
	public void placeRandomsnakesAndLaddrs() {
		int size = 13;
    	int startSn = 0;
    	int startSnake = 0;
    	int endSnake = 0;
//        int endSn = 0;
        int startlevel = 0;
        int startla = 0;
        int startLadder = 0;
        int endLadder = 0;
		ArrayList<Integer>ends = new ArrayList<Integer>();
		ArrayList<Integer>starts = new ArrayList<Integer>();
		ArrayList<Integer>startl = new ArrayList<Integer>();
		ArrayList<Integer>endl = new ArrayList<Integer>();
        Random random = new Random();
		
        
        for (int i = 0; i < 8; i++) {
            // Ensure snakes and ladders don't start or end in the same square
        	Color c = null;
            if (i == 0 || i == 1 ) { 
         	   c = Color.YELLOW;
            }else if(i == 2 || i == 3) {
         	   c = Color.GREEN;
            }else if(i == 4 || i == 5) {
         	   c = Color.BLUE;
            }else if(i == 6 || i == 7){
         	   c = Color.RED;
            }
     	   do {
               startSn = random.nextInt(size);          
//             startlevel = random.nextInt(size - i) + 1 ;
               startlevel = random.nextInt(size - (i+1)) ;
     			if(startSn > size-1) {
       				continue;
       			}
       			if(startlevel+i+1 > size-1) {
       				continue;
       				
       			}
//       			if(c.equals(Color.RED)) {
//       				startSn = 0;
//       				endSnake = squares[12][startSn].getNumber();
//       			}else if((i==1) || (i==3) || (i==5) )  {
//       				endSnake = squares[startlevel+i][startSn].getNumber();
//       			}else {
//  	                endSnake = squares[startlevel+i+1][startSn].getNumber();
//       			}
       			if(c.equals(Color.YELLOW)) {
       				endSnake = getSquares()[startlevel+1][startSn].getNumber();
       			}else if(c.equals(Color.GREEN)) {
       				endSnake = getSquares()[startlevel+2][startSn].getNumber();
       			}else if(c.equals(Color.BLUE)) {
       				endSnake = getSquares()[startlevel+3][startSn].getNumber();
       			}else {
       				startSn = 0;
       				endSnake = getSquares()[12][startSn].getNumber();
       			}
       			startSnake = getSquares()[startlevel][startSn].getNumber();
//    	   }while((startSnake == size*size) || (startlevel+i+1 > size-1) || (ends.contains(startSnake)) || (starts.contains(startSnake)) || (starts.contains(endSnake)));
    	   }while((startSnake == size*size)||(startlevel+1>size-1 && c.equals(Color.YELLOW))||(startlevel+2>size-1 && c.equals(Color.GREEN)) ||(startlevel+3>size-1 && c.equals(Color.BLUE))||(ends.contains(startSnake))||(starts.contains(startSnake))||(starts.contains(endSnake)));

       			
            Snakes s = new Snakes(c, startSnake, endSnake);
            getsnakes().add(s);
            ends.add(endSnake);
            starts.add(startSnake);
        }
        for(int i = 0 ; i < 8 ; i++) {
       		do {
       			startla = random.nextInt(size);
       			startlevel = random.nextInt(size - (i+1)) ;	
       			if(startla > size-1) {
       				continue;
       			}
       			if(startlevel+i+1 > size-1) {
       				continue;
       			}
       			startLadder = getSquares()[startlevel+i+1][startla].getNumber();
       			endLadder = getSquares()[startlevel][startla].getNumber();
       		}while((startLadder == 1) ||(endLadder == size*size) ||(endl.contains(endLadder)) || (ends.contains(endLadder)) || (startlevel+i+1 > size-1) || (startl.contains(startLadder)) || (startl.contains(endLadder)) || (starts.contains(startLadder)) || (endl.contains(startLadder)) || (ends.contains(startLadder)));
       		
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
       		Ladders l = new Ladders(lt, startLadder, endLadder);
       		getladders().add(l);
       		startl.add(startLadder);
       		endl.add(endLadder);
        }
	}
	
	
	

}
