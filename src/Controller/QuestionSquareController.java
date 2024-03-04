package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Question;

public class QuestionSquareController {
	public static QuestionSquareController _ins;
	public static synchronized QuestionSquareController getInstance() {
		if(_ins==null) {
			return _ins = new QuestionSquareController();
		}
		return _ins;
	}
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

	private SysData sysData;
	
    private Question question; // Add this field
    
    public Question quesdiff;
    
    public Boolean flag;


	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	@FXML
	void initialize() {
		rbans = new ToggleGroup();
		rbans1.setToggleGroup(rbans);
		rbans2.setToggleGroup(rbans);
		rbans3.setToggleGroup(rbans);
		rbans4.setToggleGroup(rbans);

		sysData = new SysData("src/WolfQuestionsDB.json");
		loadQuestions();
		answerButton.setOnAction(event -> checkAnswer());
	}
	public Question loadQuestions() {
        try {
            question = SysData.getInstance().getRandomQuestion(sysData.loadDataFromJSON(sysData.getFilePath()));
            if (question != null) {
                questionText.setText(question.getText());
                rbans1.setText(question.getAnswers().get(0));
                rbans2.setText(question.getAnswers().get(1));
                rbans3.setText(question.getAnswers().get(2));
                rbans4.setText(question.getAnswers().get(3));
//                
//                for(String s : sysData.getInstance().getDifques().keySet()) {
//                	if(questionText.getText().equals(s)) {
//                		sysData.getInstance().getDifques().get(ques2);
//                	}
//                }
                setQuesdiff(question);
                return getQuesdiff();
            } else {
                showAlert("Error", "No question found.");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to load questions.");
            e.printStackTrace();
        }
        return getQuesdiff();
    }

    public Boolean checkAnswer() {
        if (question != null) {
            int selectedIndex = rbans.getToggles().indexOf(rbans.getSelectedToggle());
            int correctIndex = Integer.parseInt(question.getCorrectAnswer()) - 1;

            String selectedAnswer = question.getAnswers().get(selectedIndex);
            String correctAnswer = question.getAnswers().get(correctIndex);

            // Close the window after showing the message
            Stage stage = (Stage) answerButton.getScene().getWindow();
            stage.close();
            
            if (selectedIndex == correctIndex) {
                showAlert("Correct Answer!", "You have chosen the correct answer: " + selectedAnswer);
                setFlag(true);
                System.out.println(getFlag());
                return getFlag();
            } else {
                showAlert("Incorrect Answer!", "The correct answer is: " + correctAnswer + "\nYou chose: " + selectedAnswer);
                setFlag(false);
                System.out.println(getFlag());
                return getFlag();
            }
            
            
        } else {
            showAlert("Error", "No question found.");
        }
        return false;
    }
    
//    public Integer checkDiff() {
//    	Question ques = ques2;
//    	System.out.println(ques);
//    	if(ques.getDifficulty().equals("1")) {
//    		System.out.println(ques.getDifficulty());
//    		return 1;
//    	}else if(ques.getDifficulty().equals("2")) {
//    		System.out.println(ques.getDifficulty());
//    		return 2;
//    	}
//    	return 3;
//    }

	private void showAlert(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}
	public Question getQuesdiff() {
		return quesdiff;
	}
	public void setQuesdiff(Question quesdiff) {
		this.quesdiff = quesdiff;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
}