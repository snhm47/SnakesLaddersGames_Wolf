package model;

import java.util.ArrayList;

import Controller.PlayerMovement;
import Controller.SysData;
import Utils.DiffLevel;

public class MainForTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		
//		SysData sysData = new SysData();
//        ArrayList<Question> questions = sysData.getQuestions();
//        for (Question question : questions) {
//            System.out.println(question.getText());
//        }

//		Board b = new Board(DiffLevel.easy);

		Board b = new Board(DiffLevel.hard);
		Games g = new Games(4, null);
		RunningGame rg = new RunningGame(g);
		PlayerMovement pm = new PlayerMovement();
		pm.Move(rg, 0, 0, null);
 
		b.initializeBoard();
		for(int i = 0 ; i<13 ; i++) {
			for(int j = 0 ; j<13 ; j++) {
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

