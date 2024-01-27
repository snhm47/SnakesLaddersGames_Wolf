package model;

import java.util.ArrayList;

public class Question {
	 private int number;
	    private String text;
	    private DiffLevel difficulty;
	    private ArrayList<String> answers;
	    private String correctAnswer;
	    
	    public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public DiffLevel getDifficulty() {
			return difficulty;
		}

		public void setDifficulty(DiffLevel difficulty) {
			this.difficulty = difficulty;
		}

		public ArrayList<String> getAnswers() {
			return answers;
		}

		public void setAnswers(ArrayList<String> answers) {
			this.answers = answers;
		}

		public String getCorrectAnswer() {
			return correctAnswer;
		}

		public void setCorrectAnswer(String correctAnswer) {
			this.correctAnswer = correctAnswer;
		}

		public Question(int number, String text, DiffLevel difficulty, ArrayList<String> answers, String correctAnswer) {
			super();
			this.number = number;
			this.text = text;
			this.difficulty = difficulty;
			this.answers = answers;
			this.correctAnswer = correctAnswer;
		}

		// Methods
	    public void addAnswers(String[] possibleAnswers) {}

	    public boolean checkQuestion() {
	    	return false; 
	    }

	    public void addQuestion(Question questionDatabase) {
	    }

	    public void displayAllQuestions() {
	    }

	    public void removeQuestion(Question questionDatabase) {
	    }

	    public void editQuestion(Question questionDatabase) {
	        }

	 
	    public static final String QUESTION_FOUND = "Question found!";
	    public static final String QUESTION_SUCCESSFULLY_ADDED = "The question was successfully added!";
	    public static final String QUESTION_SUCCESSFULLY_UPDATED = "The question has been successfully updated!";
	    public static final String QUESTION_SUCCESSFULLY_DELETED = "The question has been successfully deleted!";
	}

