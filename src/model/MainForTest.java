package model;

import Utils.DiffLevel;

public class MainForTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board b = new Board(DiffLevel.easy);
 
		b.initializeBoard();
		for(int i = 0 ; i<7 ; i++) {
			for(int j = 0 ; j<7 ; j++) {
				System.out.print("  "+b.getSquares()[i][j].getNumber()+" "+b.getSquares()[i][j].getSquareType());
			}
			System.out.println();
		}
		System.out.println(b.getsnakes());
		System.out.println(b.getladders());
//		for(Square sss : b.getSq().values() ) {
//			System.out.println(sss);
//		}
//		System.out.println();
//		System.out.println(7/5);
	}

}
