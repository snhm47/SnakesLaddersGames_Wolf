package JUnit5;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Controller.HistoryController;
import Controller.SysData;
import Utils.Color;
import model.EasyGameDice;
import model.Player;
import model.Question;


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
		
		//Test 3 check if the data loaded successfully from JSON
		@Test
	    public void testLoadData() { 
	        SysData sysData = new SysData("src/WolfQuestionsDB.json");
	        assertNotNull(sysData.getQuestions());
	    }
		
		//Test 4 checks if the inserts questions to JSON is done successfully
		@Test
		public void insertQuesToJsonTest() throws FileNotFoundException{
			    SysData sysData = new SysData("src/WolfQuestionsDB.json");
			    List<String> answers = new ArrayList<String>();
			    answers.add("a");
			    answers.add("b");
			    answers.add("c");
			    answers.add("d");
				Question question = new Question("What manufacturing processes did the engineers need to understand?", answers, "2", "1");
				SysData sysDataa = new SysData("src/WolfQuestionsDB.json");
				ArrayList <Question> arrQues=sysDataa.getQuestions();
				arrQues.add(question);
				Boolean actualResult = arrQues.contains(question);
				Boolean expectedResult = true;
				assertEquals(expectedResult, actualResult);
		}
		
		//Test 5 check if the dice method is done successfully
		@Test 
		public void testCheckDice() { 
			EasyGameDice ed= new EasyGameDice();
			Boolean actualResult;
			if(ed.roll()>0&&ed.roll()<7) {
				actualResult=true;
			}else {
				actualResult=false;
			}			
			Boolean expectedResult = true;
			assertEquals(expectedResult, actualResult); 
	    }
		
		
		
		
}