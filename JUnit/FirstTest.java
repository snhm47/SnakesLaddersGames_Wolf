
import org.junit.Test;
import model.Player;

import static org.junit.Assert.*;


public class FirstTest {

	//Test 1- checks when creating a new user the high score is equal to 0.
	@Test   
	public void initialHighScoreTest() {  		
		Player u=new Player("arwad", Utils.Color.RED, 0);
		int expectedResult = 0 ;
		int actualResult = u.getWinCount();
		assertEquals(expectedResult, actualResult);
	}
}