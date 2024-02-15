package Controller;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import Utils.DiffLevel;
import model.Question;



public class SysData {
	    private ArrayList<Question> questions;
	    
		
		public SysData() {
			this.questions = new ArrayList<>();
			loadQuestions();
		}

		// Import Question From JSON
		public void loadQuestions(){
			try {
	            
	            FileReader reader = new FileReader("WolfQuestionsDB.json");
	            JSONObject jsonObject = new JSONObject(reader);
	            JSONArray questionsArray = jsonObject.getJSONArray("questions");

	            for (int i = 0; i < questionsArray.length(); i++) {
	                JSONObject questionObject = questionsArray.getJSONObject(i);
	                String questionText = questionObject.getString("question");
	                JSONArray answersArray = questionObject.getJSONArray("answers");
	                ArrayList<String> answers = new ArrayList<>();
	                for (int j = 0; j < answersArray.length(); j++) {
	                    answers.add(answersArray.getString(j));
	                }
	                String correctAnswer = questionObject.getString("correct_ans");
	                String difficultyString = questionObject.getString("difficulty");
	                DiffLevel difficulty = DiffLevel.valueOf("easy".toUpperCase()); // Assuming "easy" is default

	                switch (difficultyString) {
	                    case "1":
	                        difficulty = DiffLevel.easy;
	                        break;
	                    case "2":
	                        difficulty = DiffLevel.medium;
	                        break;
	                    case "3":
	                        difficulty = DiffLevel.hard;
	                        break;
	                    default:
	                        break;
	                }
	                Question question = new Question(questionText, answers, correctAnswer, difficulty);
	                questions.add(question);
	            }

	            reader.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		
		// add new question to JSON file
		public void writeQuestions(Question question) {
	        try {
	            FileReader reader = new FileReader("WolfQuestionsDB.json");
	            JSONObject jsonObject = new JSONObject(reader);
	            JSONArray questionsArray = jsonObject.getJSONArray("questions");
	            JSONObject newQuestionObject = new JSONObject();
	            newQuestionObject.put("question", question.getText());
	            newQuestionObject.put("answers", new JSONArray(question.getAnswers()));
	            newQuestionObject.put("correct_ans", question.getCorrectAnswer());
	            int difNum=1;
	            if(question.getDifficulty().equals(DiffLevel.medium))
	            	difNum=2;
	            if(question.getDifficulty().equals(DiffLevel.hard))
	            	difNum=3;
	            newQuestionObject.put("difficulty", String.valueOf(difNum));
	            questionsArray.put(newQuestionObject);
	            jsonObject.put("questions", questionsArray);            
	            reader.close();
	            // update JSON file with the new question
	            FileWriter writer = new FileWriter("WolfQuestionsDB.json");
	            // to save the file order
	            writer.write(jsonObject.toString(4)); 
	            writer.close();    
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		
		public ArrayList<Question> getQuestions() {
			return questions;
		}

	    public static final String DATA_LOADED_SUCCESSFULLY = "Data loaded successfully!";
	    public static final String DATA_SAVED_SUCCESSFULLY = "Data saved successfully!";
	    public static final String PROBLEM_LOADING_DATA = "There is a problem loading the data.";

}