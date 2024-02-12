
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
@Test	
	// Test 2- checks if the import question from json file 
	public void readQuestionsFromJSON() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("WolfQuestionsDB.json"));
            String line;
            StringBuilder jsonString = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            reader.close();

            // המרת המחרוזת ל־JSONArray
            JSONArray jsonArray = new JSONArray(jsonString.toString());
            
            // עבור על כל איבר ב־JSONArray ויצירת מופע של Question
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String questionText = jsonObject.getString("questionText");
                String answer = jsonObject.getString("answer");
                Question question = new Question(questionText, answer);
                questions.add(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}