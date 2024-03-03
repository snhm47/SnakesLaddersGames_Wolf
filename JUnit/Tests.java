package JUnit;
import model.Board;
import model.Player;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import Utils.Color;
import Utils.DiffLevel;

class Tests {
	
	
	//Test 1 checks when creating a new user if the high score is  0
	@Test   
	public void initialHighScoreTest() {  		
		Player u=new Player("arwad", Utils.Color.RED, 0);
		int expectedResult = 0 ;
		int actualResult = u.getWinCount();
		assertEquals(expectedResult, actualResult);
	}
	
	// Test 2 test creating new player
	@Test
	public void testPlayerInitialization() {
        Player player = new Player("John",Color.GREEN, 0);
        String expectedResult = "John" ;
		String actualResult = player.getNickName() ;
        Color expectedResult2=Color.GREEN;
        Color actualResult2=player.getColorPlayer();
		assertEquals(expectedResult, actualResult);
		assertEquals(expectedResult2, actualResult2);
	}
	
	// Test 3 test if the board build and not null
	@Test
	public void testBoardNotNull() {
		Board b = new Board(DiffLevel.easy)		
		int expectedResult = 49;
		int actualResult =b.getSquares().length;
		assertEquals(expectedResult, actualResult);
	}
	
	//Test 4 checks if the inserts questions to JSON is done successfully
	// we will add this test in iteratin 3 
	/*	@Test
		public void insertQuesToJsonTest(){
			String[] answers = new String [] {"a","b","c","d"};
			Question question = new Question("What manufacturing processes did the engineers need to understand?", 
					answers, "2", "1");
			try {
				Sysdata.importQuestionsFromJSON();
				Sysdata.getImportedQuestions().add(ques);
				Sysdata.exportQuestionsToJSON();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Boolean actualResult = Sysdata.getImportedQuestions().contains(ques);
			Boolean expectedResult = true;
			assertEquals(expectedResult, actualResult);
		}
	*/
	
	//Test 5 hecks if the data loaded successfully from JSON
	// we will add this test in iteratin 3 
	/*@Test
    public void testLoadData() { 
        SysData sysData = new SysData();
        sysData.loadData();
        assertNotNull(sysData.getQuestions(), "Questions should not be null after loading data");
    }*/
	
	
}

}
