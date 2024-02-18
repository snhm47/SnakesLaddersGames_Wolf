package Controller;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


import Utils.DiffLevel;
import model.Question;
import java.lang.reflect.Type;
import java.util.List;



public class SysData {
	    private ArrayList<Question> questions=new ArrayList<Question>();
	    
		
		public SysData() {
			this.questions = new ArrayList<>();
			loadQuestions();
		}
		
		
		  public void loadQuestions() {
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader("src/WolfQuestionsDB.json"));
	            String line;
	            StringBuilder jsonContent = new StringBuilder();

	            while ((line = reader.readLine()) != null) {
	                jsonContent.append(line);
	            }
	            reader.close();

	            // Parsing JSON manually
	            String jsonString = jsonContent.toString(); ///
	            jsonString = jsonString.trim();
	            jsonString = jsonString.substring(1, jsonString.length() - 1); // Remove outer square brackets
	           
	            jsonString = jsonString.replaceAll("\\\\", ""); // Remove backslashes

	            String[] questionEntries = jsonString.split("\\},\\{");

	            for (String entry : questionEntries) {
	                entry = entry.replaceAll("[{}\"]", ""); // Remove curly braces and quotes
	                String[] keyValuePairs = entry.split(",");
	                String questionText = null;
	                ArrayList<String> answers = new ArrayList<>();
	                String correctAnswer = null;
	                DiffLevel difficulty = null;

	                for (String pair : keyValuePairs) {
	                    String[] parts = pair.split(":", 2); // Limit split to 2 parts
	                    System.out.println(Arrays.toString(parts)); // Add this line for debugging

	                    if (parts.length == 2) {
	                        String key = parts[0].trim();
	                        String value = parts[1].trim();

	                        switch (key) {
	                            case "question":
	                                questionText = value;
	                                break;
	                            case "answers":
	                                String[] answersArray = value.split(",");
	                                for (String answer : answersArray) {
	                                    answers.add(answer);
	                                }
	                                break;
	                            case "correct_ans":
	                                correctAnswer = value;
	                                break;
	                            case "difficulty":
	                                switch (value) {
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
	                                        System.out.println("Invalid difficulty level for question: " + questionText);
	                                        break;
	                                }
	                                break;
	                        }
	                    }
	                }

	                Question question = new Question(questionText, answers, correctAnswer, difficulty);
	                questions.add(question);
	            }

	            System.out.println(DATA_LOADED_SUCCESSFULLY); // Added success message
	        } catch (Exception e) {
	            System.out.println(PROBLEM_LOADING_DATA); // Added error message
	            e.printStackTrace();
	        }
	    }
		 // add Question to WolfQuestionsDB.json file
		 public void addQuestionToJsonFile(Question qu) {
			 try {
		          
		            Gson gson = new Gson();
		            FileReader fileReader = new FileReader("src/WolfQuestionsDB.json");
		            Type questionListType = new TypeToken<ArrayList<Question>>() {}.getType();
		            ArrayList<Question> questionList = gson.fromJson(fileReader, questionListType);
		            fileReader.close();
		            
		            questionList.add(qu);

		            FileWriter fileWriter = new FileWriter("src/WolfQuestionsDB.json");
		            gson.toJson(questionList, fileWriter);
		            fileWriter.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			  
		 }
		 
		 public void deleteQuestionToJsonFile(Question qu) {
			 
		 }
		 
		/*public void loadQuestions() {
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader("src/WolfQuestionsDB.json"));
	            String line;
	            StringBuilder jsonContent = new StringBuilder();

	            while ((line = reader.readLine()) != null) {
	                jsonContent.append(line);
	            }

	            reader.close();

	            // Parsing JSON manually
	            String jsonString = jsonContent.toString();
	            jsonString = jsonString.trim();
	            jsonString = jsonString.substring(1, jsonString.length() - 1); // Remove outer square brackets
	            jsonString = jsonString.replaceAll("\\\\", ""); // Remove backslashes

	            String[] questionEntries = jsonString.split("\\},\\{");

	            for (String entry : questionEntries) {
	                entry = entry.replaceAll("[{}\"]", ""); // Remove curly braces and quotes
	                String[] keyValuePairs = entry.split(",");
	                String questionText = null;
	                ArrayList<String> answers = new ArrayList<>();
	                String correctAnswer = null;
	                DiffLevel difficulty = null;

	                for (String pair : keyValuePairs) {
	                    String[] parts = pair.split(":", 2); // Limit split to 2 parts
	                    System.out.println(Arrays.toString(parts)); // Add this line for debugging

	                    if (parts.length == 2) {
	                        String key = parts[0].trim();
	                        String value = parts[1].trim();

	                        switch (key) {
	                            case "question":
	                                questionText = value;
	                                break;
	                            case "answers":
	                                String[] answersArray = value.split(",");
	                                for (String answer : answersArray) {
	                                    answers.add(answer);
	                                }
	                                break;
	                            case "correct_ans":
	                                correctAnswer = value;
	                                break;
	                            case "difficulty":
	                                switch (value) {
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
	                                        System.out.println("Invalid difficulty level for question: " + questionText);
	                                        break;
	                                }
	                                break;
	                        }
	                    }
	                }

	                Question question = new Question(questionText, answers, correctAnswer, difficulty);
	                questions.add(question);
	            }

	            System.out.println(DATA_LOADED_SUCCESSFULLY); // Added success message
	        } catch (Exception e) {
	            System.out.println(PROBLEM_LOADING_DATA); // Added error message
	            e.printStackTrace();
	        }
	    }*/
		
		// Import Question From JSON
		/*public void loadQuestions(){
			try {
	            
	            FileReader reader = new FileReader("src/WolfQuestionsDB.json");
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
		*/
		// add new question to JSON file
		/*
		public void writeQuestions(Question question) {
	        try {
	            FileReader reader = new FileReader("src/WolfQuestionsDB.json");
	            JSONObject jsonObject = new JSONObject(reader);
	            JSONArray questionsArray = jsonObject.getJSONArray("questions");
	            JSONObject newQuestionObject = new JSONObject();
	            newQuestionObject.put("question", question.getText());
	            newQuestionObject.put("answers", new JSONArray(question.getAnswers()));
	            newQuestionObject.put("correct_ans", question.getCorrectAnswer());
	            String difNum="1";
	            if(question.getDifficulty().equals(DiffLevel.medium))
	            	difNum="2";
	            if(question.getDifficulty().equals(DiffLevel.hard))
	            	difNum="3";
	            newQuestionObject.put("difficulty", difNum);
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
		
		*/
		public ArrayList<Question> getQuestions() {
			return questions;
		}

	    public static final String DATA_LOADED_SUCCESSFULLY = "Data loaded successfully!";
	    public static final String DATA_SAVED_SUCCESSFULLY = "Data saved successfully!";
	    public static final String PROBLEM_LOADING_DATA = "There is a problem loading the data.";

}