package model;


import Controller.PlayerMovement;
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

		Board hb = new HardBoard();
		Games g = new Games(4, DiffLevel.hard);
		RunningGame rg = new RunningGame(g);
		PlayerMovement pm = new PlayerMovement();
		Player p = new Player("salah", Utils.Color.BLUE, 0);
		pm.Move(rg, 0, 0, p);
 
		hb.initializeBoard();
		for(int i = 0 ; i<13 ; i++) {
			for(int j = 0 ; j<13 ; j++) {
				System.out.print("  "+hb.getSquares()[i][j].getNumber()+" "+hb.getSquares()[i][j].getSquareType());
			}
			System.out.println();
		}
		System.out.println(hb.getsnakes());
		System.out.println(hb.getladders());
//		for(Square sss : b.getSq().values() ) {
//			System.out.println(sss);
//		}
//		System.out.println();
//		System.out.println(7/5);
	}

}

