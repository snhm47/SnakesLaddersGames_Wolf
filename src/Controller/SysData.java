package Controller;

import model.Games;
import model.Question;

public class SysData {
	 	private Games games;
	    private Question questions;
	    
	    public Games getGames() {
			return games;
		}

		public void setGames(Games games) {
			this.games = games;
		}

		public Question getQuestions() {
			return questions;
		}

		public void setQuestions(Question questions) {
			this.questions = questions;
		}
		
		public SysData(Games games, Question questions) {
			super();
			this.games = games;
			this.questions = questions;
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
