package Controller;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import Utils.DiffLevel;
import model.Question;
import java.lang.reflect.Type;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;



public class SysData {
	    private ArrayList<Question> questions=new ArrayList<Question>();
	    private String filePath;
	    
		
    public SysData(String filePath) {
        this.filePath = filePath;
    }


		    public ObservableList<Question> loadDataFromJSON(String filePath) {
		        ObservableList<Question> data = FXCollections.observableArrayList();
		        try {
		            // Parse JSON file
		            JSONParser parser = new JSONParser();
		            Object obj = parser.parse(new FileReader(filePath));
		            JSONObject jsonObject = (JSONObject) obj;
		            JSONArray questions = (JSONArray) jsonObject.get("questions");

		            // Convert JSON data to ObservableList<Question>
		            for (Object question : questions) {
		                JSONObject questionObj = (JSONObject) question;
		                String questionText = (String) questionObj.get("question");
		                String difficulty = (String) questionObj.get("difficulty");

		                Question newQuestion = new Question(questionText, difficulty);

		                // Assuming there are also answer and correctAnswer fields in the JSON
		                List<String> answers = (List<String>) questionObj.get("answers");
		                newQuestion.setAnswers((ArrayList<String>) answers);

		                String correctAnswer = (String) questionObj.get("correctAnswer");
		                newQuestion.setCorrectAnswer(correctAnswer);

		                data.add(newQuestion);
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		            System.err.println("Error: " + e.getMessage());
		        }
		        return data;
		    }
		    
		    public void deleteQuestion(Question question) {
		        try {
		            // Load JSON file
		            JSONParser parser = new JSONParser();
		            Object obj = parser.parse(new FileReader("src/WolfQuestionsDB.json"));
		            JSONObject jsonObject = (JSONObject) obj;
		            JSONArray questions = (JSONArray) jsonObject.get("questions");

		            // Find and remove the question from the JSON array
		            Iterator<JSONObject> iterator = questions.iterator();
		            while (iterator.hasNext()) {
		                JSONObject questionObj = iterator.next();
		                if (questionObj.get("question").equals(question.getText())) {
		                    iterator.remove();
		                    break;
		                }
		            }

		            // Update JSON file
		            try (FileWriter file = new FileWriter("src/WolfQuestionsDB.json")) {
		                jsonObject.put("questions", questions);
		                file.write(jsonObject.toJSONString());
		                file.flush();
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		            System.err.println("Error: " + e.getMessage());
		        }
		    }
		    
		  

		

		
		 
		
		public ArrayList<Question> getQuestions() {
			return questions;
		}

	    public static final String DATA_LOADED_SUCCESSFULLY = "Data loaded successfully!";
	    public static final String DATA_SAVED_SUCCESSFULLY = "Data saved successfully!";
	    public static final String PROBLEM_LOADING_DATA = "There is a problem loading the data.";

}