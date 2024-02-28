package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.ArrayList;
import java.util.List;

public class Question {

	private static int count = 0;
	private int number;
	private final StringProperty text;
	private ArrayList<String> answers = new ArrayList<>();
	private final StringProperty correctAnswer;
	private final StringProperty difficulty;
    private String textQ;

	public Question(String question, List<String> answers, String correct_ans, String difficulty) {
		count++;
		this.number = count;
		this.text = new SimpleStringProperty(question);
		for (String s : answers) {
			this.answers.add(s);
		}
		this.correctAnswer = new SimpleStringProperty(correct_ans);
		this.difficulty = new SimpleStringProperty(difficulty);
	}

	public Question(String questionText, String difficulty) {
		this.text = new SimpleStringProperty(questionText);
		this.difficulty = new SimpleStringProperty(difficulty);
		this.answers = new ArrayList<>();
		this.correctAnswer = new SimpleStringProperty("");
	}

	public int getNumber() {
		return number;
	}

	public String getText() {
		return text.get();
	}
	
	public String getTextQ() {
		return text.get();
	}
	
	public void setTextQ(String text) {
		this.textQ= text;
	}



	public StringProperty questionProperty() {
		return text;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public String getCorrectAnswer() {
		return correctAnswer.get();
	}

	public StringProperty correctAnswerProperty() {
		return correctAnswer;
	}

	public String getDifficulty() {
		return difficulty.get();
	}

	public StringProperty difficultyProperty() {
		return difficulty;
	}

	public void setAnswers(ArrayList<String> answers) {
		if (!answers.isEmpty()) {
			this.answers = answers;
		}
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer.set(correctAnswer);
	}

	public void setDifficulty(String difficulty) {
		this.difficulty.set(difficulty);
	}

	public void editQuestion(String newText, List<String> newAnswers, String newCorrectAnswer, String newDifficulty) {
		this.text.set(newText);
		this.setAnswers(new ArrayList<>(newAnswers));
		this.setCorrectAnswer(newCorrectAnswer);
		this.setDifficulty(newDifficulty);
	}
}
