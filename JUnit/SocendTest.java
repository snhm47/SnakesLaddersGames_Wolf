import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;

import model.Question;

public class SocendTest {
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
