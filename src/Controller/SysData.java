package Controller;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import Utils.DiffLevel;
import model.Question;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SysData {
	
	public static SysData _ins;

	private ArrayList<Question> questions = new ArrayList<Question>();
	private static String filePath;
	private HashMap<String, Question>difques = 	new HashMap<String, Question>();
	private ArrayList<Question>easyq= new ArrayList<Question>();
	private ArrayList<Question>medq= new ArrayList<Question>();
	private ArrayList<Question>hardq= new ArrayList<Question>();


	public HashMap<String, Question> getDifques() {
		return difques;
	}

	public void setDifques(HashMap<String, Question> difques) {
		this.difques = difques;
	}

	public static  synchronized SysData getInstance() {
		if(_ins == null) {
			return _ins = new SysData(filePath);
		}
		return _ins;
	}
	
	public SysData(String filePath) {
		SysData.filePath = filePath;
	}


    public ObservableList<Question> loadDataFromJSON(String filePath) {
        ObservableList<Question> data = FXCollections.observableArrayList();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray questionArray = (JSONArray) jsonObject.get("questions");

            for (Object questionObj : questionArray) {
                JSONObject q = (JSONObject) questionObj;
                String questionText = (String) q.get("question");
                String difficulty = (String) q.get("difficulty");
                List<String> answers = (List<String>) q.get("answers");
                String correctAnswer = (String) q.get("correct_ans");
                Question ques = new Question(questionText, answers, correctAnswer, difficulty);
//                data.add(new Question(questionText, answers, correctAnswer, difficulty));
                data.add(ques);
                difques.put(questionText, ques);
                if(difficulty.equals("1")) {
                	easyq.add(ques);
                }else if(difficulty.equals("2")) {
                	medq.add(ques);
                }else {
                	hardq.add(ques);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
	

//    public ObservableList<Question> loadDataFromJSON(String filePath) {
//        ObservableList<Question> data = FXCollections.observableArrayList();
//        try {
//            JSONParser parser = new JSONParser();
//            Object obj = parser.parse(new FileReader(filePath));
//            JSONObject jsonObject = (JSONObject) obj;
//            JSONArray questionArray = (JSONArray) jsonObject.get("questions");
//
//            for (Object questionObj : questionArray) {
//                JSONObject q = (JSONObject) questionObj;
//                String questionText = (String) q.get("question");
//                String difficulty = (String) q.get("difficulty");
//                List<String> answers = (List<String>) q.get("answers");
//                String correctAnswer = (String) q.get("correct_ans");
//                Question ques = new Question(questionText, answers, correctAnswer, difficulty);
////                data.add(new Question(questionText, answers, correctAnswer, difficulty));
//                data.add(ques);
//                if(difficulty.equals("1")) {
//                	easyq.add(ques);
//                }else if(difficulty.equals("2")) {
//                	medq.add(ques);
//                }else {
//                	hardq.add(ques);
//                }
//                difques.put(questionText, ques);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return data;
//    }

    public ArrayList<Question> getEasyq() {
		return easyq;
	}

	public void setEasyq(ArrayList<Question> easyq) {
		this.easyq = easyq;
	}

	public ArrayList<Question> getMedq() {
		return medq;
	}

	public void setMedq(ArrayList<Question> medq) {
		this.medq = medq;
	}

	public ArrayList<Question> getHardq() {
		return hardq;
	}

	public void setHardq(ArrayList<Question> hardq) {
		this.hardq = hardq;
	}

	public ObservableList<Question> filterQuestionsByDifficulty(String difficulty) {
        ObservableList<Question> filteredQuestions = FXCollections.observableArrayList();
        for (Question question : questions) {
            if (question.getDifficulty().equals(difficulty)) {
                filteredQuestions.add(question);
            } else if (difficulty.equals("Medium") && (question.getDifficulty().equals("Easy") || question.getDifficulty().equals("Medium"))) {
                filteredQuestions.add(question);
            } else if (difficulty.equals("Hard")) {
                filteredQuestions.add(question);
            }
        }
        return filteredQuestions;
    }

	// this function for reading the question only in order to edit a question in
	// the EditPage.fxml
	public ObservableList<String> loadQuestionsFromJSON(String filePath) {
		ObservableList<String> questionTexts = FXCollections.observableArrayList();
		try {
			// Parse JSON file
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(filePath));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray questions = (JSONArray) jsonObject.get("questions");

			// Extract question texts from JSON
			for (Object question : questions) {
				JSONObject questionObj = (JSONObject) question;
				String questionText = (String) questionObj.get("question");
				questionTexts.add(questionText);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
		}
		return questionTexts;
	}

	public void deleteQuestion(Question question) {
		try {
			// Load JSON file
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("WolfQuestionsDB.json"));
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
			try (FileWriter file = new FileWriter("WolfQuestionsDB.json")) {
				jsonObject.put("questions", questions);
				file.write(jsonObject.toJSONString());
				file.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public String getFilePath() {
	    return this.filePath;
	}

	
	    public Question getRandomQuestion(ObservableList<Question> questions) {
	        Random random = new Random();
	        int index = random.nextInt(questions.size());
	        return questions.get(index);
	    }

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public static final String DATA_LOADED_SUCCESSFULLY = "Data loaded successfully!";
	public static final String DATA_SAVED_SUCCESSFULLY = "Data saved successfully!";
	public static final String PROBLEM_LOADING_DATA = "There is a problem loading the data.";

}