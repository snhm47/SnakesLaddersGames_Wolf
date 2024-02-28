package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Utils.DiffLevel;

public class Question {

	private static int count =0; // to defines the number of the question and runs automatically
	private int number;
	private String text;
	private ArrayList<String> answers=new ArrayList<String>();
	private DiffLevel difficulty;
	private String correctAnswer;
	
	public Question(String question,List<String> answers,String correct_ans,DiffLevel difficulty) {
		count++;
		this.number=count;
		text=question;
		for(String s:answers) {
			this.answers.add(s);
		}
		this.correctAnswer=correct_ans;
		this.difficulty = DiffLevel.easy ;
		if(difficulty.equals("2")) {
			this.difficulty = DiffLevel.medium ;
		}
		if(difficulty.equals("3")) {
			this.difficulty = DiffLevel.hard ;
		}
	}

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
		if(!answers.equals(null)) {
		for(String s:answers) {
			if(!s.equals(null))
			this.answers.add(s);
		}
		}
	}



	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Question(String text,  ArrayList<String> answers, String correctAnswer, DiffLevel difficulty) {
		super();
		count++;
		this.number=count;
		this.text = text;
		this.difficulty = difficulty;
		if(answers.equals(null)==false)
		setAnswers(answers);
		this.correctAnswer = correctAnswer;
	}

	// Methods
	// Method to add the 4 answers option for a question
//	public void addAnswers(String[] possibleAnswers) {
//		if (possibleAnswers.length == 4) 
//			{answers = new ArrayList<>();
//			for (String answer : possibleAnswers) 
//				answers.add(answer);}
//		else 
//			System.out.println("Error: You must add 4 possible answers to each question.");
//		
//	}

	// Method to check if the question is in the data warehouse
	public boolean checkQuestion(ArrayList<Question> questionDatabase) {
		for (Question question : questionDatabase) {
			if (this.text.equalsIgnoreCase(question.getText())) {
				System.out.println("Qustion found!");
				return true;
			}
		}
		return false;
	}
	
	// Method to add a question to the database
    // Check if the number of possible answers is exactly 4
    // Check if the question already exists in the database

	public void addQuestionAndAnswers(ArrayList<Question> questionDatabase, String[] possibleAnswers) {
	    if (possibleAnswers.length == 4) {
	        if (!checkQuestion(questionDatabase)) {
	            this.answers = new ArrayList<>(Arrays.asList(possibleAnswers));
	            questionDatabase.add(this);
	            System.out.println("Question successfuly added");
	        } else {
	            System.out.println("Question already exists in the database.");
	        }
	    } else {
	        System.out.println("Error: You must provide exactly 4 possible answers.");
	    }
	}
	
	// Method to display all questions in the database
	public void displayAllQuestions(ArrayList<Question> questionDatabase) {
		for (Question question : questionDatabase) {
			System.out.println("Number: " + question.getNumber());
			System.out.println("Text: " + question.getText());
			System.out.println("Difficulty: " + question.getDifficulty());
			System.out.println("Answers: " + question.getAnswers());
			System.out.println("Correct Answer: " + question.getCorrectAnswer());
		}
	}

	// Method to remove a question and there answers from the database
    // Check if the current question is the one to be removed
    // Remove the question and its associated answers
	// Stop iterating once the associated question is found and processed
	public void removeQuestionAndAnswers(ArrayList<Question> questionDatabase) {
	    for (Question question : questionDatabase) {
	        if (question.equals(this)) {
	            questionDatabase.remove(this);
	            this.answers = null;
	            System.out.println("Question successfuly removed");
	            return;
	        }
	    }
	    System.out.println("Question not found in the database.");
	}

	// Method to edit an existing question in the database
	public void editQuestion(ArrayList<Question> questionDatabase, String newText, String[] newAnswers,
			String newCorrectAnswer) {
		if (checkQuestion(questionDatabase)) {
			this.setText(newText);
			ArrayList<String>answers = new ArrayList<String>();
			for(String an : newAnswers) {
				answers.add(an);
			}
			this.setAnswers(answers);
			this.setCorrectAnswer(newCorrectAnswer);
			System.out.println("Question updated successfully");
		}

	}

}