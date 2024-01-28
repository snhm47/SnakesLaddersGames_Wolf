package model;

import java.util.ArrayList;

public class MainForTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Square[][] ss = null;
		ArrayList<Snakes> s = new ArrayList<Snakes>();
		Board b = new Board(DiffLevel.easy, ss, null, s, null, null, null);
 
		b.initializeBoard();
		for(int i = 0 ; i<7 ; i++) {
			for(int j = 0 ; j<7 ; j++) {
				System.out.print("  "+b.getSquares()[i][j].getNumber()+" "+b.getSquares()[i][j].getSquareType());
			}
			System.out.println();
		}
		System.out.println(s);
//		System.out.println(7/5);
	}

}
