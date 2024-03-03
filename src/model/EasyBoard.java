package model;

import java.util.ArrayList;
import java.util.Random;

import Utils.Color;
import Utils.DiffLevel;
import Utils.LadderType;
import Utils.SquareType;

public class EasyBoard extends Board{
	
	public static EasyBoard instanceEasyBoard;
	public static synchronized EasyBoard getInstance() {
		if (instanceEasyBoard == null)
			instanceEasyBoard = new EasyBoard();

	    return instanceEasyBoard;
	}
	

	public EasyBoard() {
		super(DiffLevel.easy);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeBoard() {
		int size = 7;
		int quesCount = 3 ; 
		System.out.println(size + " "  + quesCount);
		setSquares(new Square[size][size]);
		
		//random Question pos
		Random random = new Random();
		int stRandj = 0;
		int stRandi = 0;
		
		for(int i= size-1 ; i>=0 ; i--) {
			 if (i % 2 == 0){
				 for (int j = 0; j < size; j++) {
					 getSquares()[i][j] = new Square(SquareType.REGULAR);
					 getSq().put(getSquares()[i][j].getNumber(),getSquares()[i][j]);
				 }
			 }else if(i % 2 != 0) {
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
		    
		placeRandomsnakesAndLaddrs();

	}

	@Override
	public void placeRandomsnakesAndLaddrs() {
    	int size = 7;
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
        
        
           for (int i = 0; i < 4; i++) {
               // Ensure snakes and ladders don't start or end in the same square
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
        	   do {
	               startSn = random.nextInt(size);          
//                 startlevel = random.nextInt(size - i) + 1 ;
                   startlevel = random.nextInt(size - (i+1)) ;
          			if(startSn > size-1) {
           				continue;
           			}
           			if(startlevel+i+1 > size-1) {
           				continue;
           			}
           			if(c.equals(Color.RED)) {
           				startSn = 0;
           				endSnake = getSquares()[6][startSn].getNumber();
           			}else {
                        endSnake = getSquares()[startlevel+i+1][startSn].getNumber();

           			}
//               }while((startlevel+i+1 > size) || (endSnake+1 + ((startlevel+i+1)*size) >= size*size) || starts.contains(startSnake + (startlevel*size)) || (ends.contains(endSnake+1 + ((startlevel+i+1)*size))));
//              System.out.println(startSnake + " " + endSnake + " " + startlevel);
//              System.out.println(squares[startlevel][startSn].getNumber());
//              System.out.println(squares[startlevel+i+1][startSn].getNumber());
                   startSnake = getSquares()[startlevel][startSn].getNumber();
        	   }while((startSnake == size*size) ||(startl.contains(endSnake)) || (startlevel+i+1 > size-1) || (ends.contains(endSnake)) || (ends.contains(startSnake)) || (starts.contains(startSnake)) || (starts.contains(endSnake)));

               Snakes s = new Snakes(c, startSnake, endSnake);
               getsnakes().add(s);
               ends.add(endSnake);
               starts.add(startSnake);
 
           }
           for(int i = 0 ; i < 4 ; i++) {
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
       		}while((startLadder == 1) || (endLadder == size*size) ||(endl.contains(endLadder)) || (ends.contains(endLadder)) || (startlevel+i+1 > size-1) || (startl.contains(startLadder)) || (startl.contains(endLadder)) || (endl.contains(startLadder)) || (endl.contains(startSnake)));
       		
       		System.out.println(startLadder + " " + endLadder);
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
       		Ladders l = new Ladders(lt, startLadder, endLadder);
       		getladders().add(l);
       		startl.add(startLadder);
       		endl.add(endLadder);
       	}

	}

}
