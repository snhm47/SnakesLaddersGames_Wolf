package Controller;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Utils.DiffLevel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Player;
import model.Question;
import model.RunningGame;

public class QuestionSquareController {
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private RadioButton rbans1;

	@FXML
	private RadioButton rbans2;

	@FXML
	private RadioButton rbans3;

	@FXML
	private RadioButton rbans4;

	@FXML
	private TextField questionText;

	@FXML
	private Button answerButton;

	private ToggleGroup rbans;
	
	public static boolean corr ;

	private SysData sysData = SysData.getInstance();
	private ObservableList<Question> getques = sysData.loadDataFromJSON("WolfQuestionsDB.json");
	
    private static Question question; // Add this field
    
    public static Question quesdiff ;
    
    
    //singleton
	public static QuestionSquareController _ins;
	public static synchronized QuestionSquareController getInstance() {
		if(_ins==null) {
			return _ins = new QuestionSquareController();
		}
		return _ins;
	}
    
    //getters and setters
    public static Question getQuesdiff() {
		return quesdiff;
	}
	public static void setQuesdiff(Question quesdiff) {
		QuestionSquareController.quesdiff = quesdiff;
	}
	public static Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		QuestionSquareController.question = question;
	}
	public static boolean isCorr() {
		return corr;
	}
	public static void setCorr(boolean corr) {
		QuestionSquareController.corr = corr;
	}
	
	
	// when the fxml file is load
	@FXML
	void initialize() {
		rbans = new ToggleGroup();
		rbans1.setToggleGroup(rbans);
		rbans2.setToggleGroup(rbans);
		rbans3.setToggleGroup(rbans);
		rbans4.setToggleGroup(rbans);
		loadQuestions();
		
		//check the answer and tell the player 
		answerButton.setOnAction(event -> {
			checkAnswer();
		});
	}
	
	//loading the questions
	public Question loadQuestions() {
        try {
        	setQuestion(SysData.getInstance().getRandomQuestion(getques));
            if (question != null) {
                questionText.setText(question.getText());
                rbans1.setText(question.getAnswers().get(0));
                rbans2.setText(question.getAnswers().get(1));
                rbans3.setText(question.getAnswers().get(2));
                rbans4.setText(question.getAnswers().get(3));
                return question;
            } else {
                showAlert("Error", "No question found.");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to load questions.");
            e.printStackTrace(); 
        }
        return question;
    }

	
	//check answer method 
    public Boolean checkAnswer() {
    	Player pl = StartGameController.p;
    	int tern = StartGameController.turn;
    	DiffLevel dl = StartGameController.dl;
    	HashMap<Player , Integer>hm = RunningGame.getInstance().getPlayerPlacement();
    	int from = hm.get(pl);
    	
        if (question != null) {
            int selectedIndex = rbans.getToggles().indexOf(rbans.getSelectedToggle());
            int correctIndex = Integer.parseInt(question.getCorrectAnswer()) - 1;

            String selectedAnswer = question.getAnswers().get(selectedIndex);
            String correctAnswer = question.getAnswers().get(correctIndex);
            
            if (selectedIndex == correctIndex) {
                setCorr(true);
                showAlert("Correct Answer!", "You have chosen the correct answer: " + selectedAnswer);
//                if(dl.equals(DiffLevel.hard)) {
//                	StartGameController.getInstance().move(pl, from, from+1, tern);
//                }
                if(dl.equals(DiffLevel.easy)) {
                	if(StartGameController.diceRes == 7) {
                		StartGameController.getInstance().move(pl, from, from+1, tern);
                	}
                }else if(dl.equals(DiffLevel.medium)) {
                	if(StartGameController.diceRes == 11 || StartGameController.diceRes == 12) {
                		StartGameController.getInstance().move(pl, from, from+1, tern);
                	}
                }else {
                	if(StartGameController.diceRes == 11 || StartGameController.diceRes == 12 || StartGameController.diceRes == 14|| StartGameController.diceRes == 13|| StartGameController.diceRes == 15) {
                		StartGameController.getInstance().move(pl, from, from+1, tern);
                	}
                }
            } else {
                setCorr(false);
                showAlert("Incorrect Answer!", "The correct answer is: " + correctAnswer + "\nYou chose: " + selectedAnswer);
                if(dl.equals(DiffLevel.easy)) {
                	if(StartGameController.diceRes == 5) {
                    	StartGameController.getInstance().move(pl, from, from-1, tern);
                	}else if(StartGameController.diceRes == 6) {
                		StartGameController.getInstance().move(pl, from, from-2, tern);
                	}else if(StartGameController.diceRes == 7) {
                    	StartGameController.getInstance().move(pl, from, from-3, tern);
                	}
                }else if(dl.equals(DiffLevel.medium)) {
                	if(StartGameController.diceRes == 7 || StartGameController.diceRes == 8) {
                    	StartGameController.getInstance().move(pl, from, from-1, tern);
                	}else if(StartGameController.diceRes == 9 ||StartGameController.diceRes == 10) {
                		StartGameController.getInstance().move(pl, from, from-2, tern);
                	}else if(StartGameController.diceRes == 11 || StartGameController.diceRes == 12) {
                    	StartGameController.getInstance().move(pl, from, from-3, tern);
                	}                
                }else {
                	if(StartGameController.diceRes == 7 || StartGameController.diceRes == 8) {
                    	StartGameController.getInstance().move(pl, from, from-1, tern);
                	}else if(StartGameController.diceRes == 9 ||StartGameController.diceRes == 10) {
                		StartGameController.getInstance().move(pl, from, from-2, tern);
                	}else if(StartGameController.diceRes == 11 || StartGameController.diceRes == 12 || StartGameController.diceRes == 14|| StartGameController.diceRes == 13|| StartGameController.diceRes == 15) {
                    	StartGameController.getInstance().move(pl, from, from-3, tern);
                	}                  }
            }
            // Close the window after showing the message
            Stage stage = (Stage) answerButton.getScene().getWindow();
            stage.close();
        } else {
            showAlert("Error", "No question found.");
        }
        return isCorr();
    }
    
    //check the diff of the question
    public static String checkDiff() {
		System.out.println(quesdiff.getDifficulty());

    	if (question != null) {
    		String getdiff =  question.getDifficulty();
    		return getdiff;
    	}
    	return null;
    }

    //the method that show alerts to the player
	private void showAlert(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}

}