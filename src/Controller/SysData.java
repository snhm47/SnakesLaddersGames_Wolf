package Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import model.Question;


public class SysData {
	    private ArrayList<Question> questions;
	    

		
		
		public SysData() throws IOException {
			this.questions = new ArrayList<>();
			ImportQuestion();
		}

		public void ImportQuestion() throws IOException {
			String jsonString=new String(Files.readAllBytes(Paths.get("WolfQuestionsDB.json")));
			// Convert the string to a JSONArray
			
			
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
