package Controller;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

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
	            JSONArray questionsArray = new JSONArray(reader);
	            for (int i = 0; i < questionsArray.length() ; i++) {
	                JSONObject questionObject = questionsArray.getJSONObject(i);
	                DiffLevel df;
	                if(questionObject.getString("difficulty").equals("1")) {
	                	df=DiffLevel.easy;
	                }else if(questionObject.getString("difficulty").equals("2")){
	                	df=DiffLevel.medium;
	                }else {
	                	df=DiffLevel.hard;
	                }
	                ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(questionObject.getString("answer").split(",")));
	                Question question = new Question(
	                        questionObject.getString("question"),
	                        arrayList,
	                        questionObject.getString("correct_ans"),
	                        df);
	                questions.add(question);
	            }
	            reader.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		
		
		public ArrayList<Question> getQuestions() {
			return questions;
		}
		// Methods
	    public void loadData(String filePath) {
	    }

	    public void writeData(String filePath) {
	    }

	    public void loadQuestions(String filePath) {
	    }

	    public static final String DATA_LOADED_SUCCESSFULLY = "Data loaded successfully!";
	    public static final String DATA_SAVED_SUCCESSFULLY = "Data saved successfully!";
	    public static final String PROBLEM_LOADING_DATA = "There is a problem loading the data.";

}