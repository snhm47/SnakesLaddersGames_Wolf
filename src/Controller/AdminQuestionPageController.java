package Controller;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Question;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;


import Controller.QuestionPageController.EditDeleteCell;

public class AdminQuestionPageController {

    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<Question> questionTableView;

    @FXML
    private ChoiceBox<String> sortChoiceBox;

    private ObservableList<Question> allQuestions;
	SysData sysData = new SysData("src/WolfQuestionsDB.json");

    @FXML
	private void initialize() {
	    sysData = new SysData("src/WolfQuestionsDB.json");

	    // Add items to the choice box
	    sortChoiceBox.getItems().addAll("diffLvL low to high", "diffLvL high to low");
	    // Set default selection
	    sortChoiceBox.getSelectionModel().selectFirst();

	    // Add listener to the choice box selection
	    sortChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	        if (newValue != null) {
	            sortTableByDifficulty(newValue);
	        }
	    });

	    // Define columns
	    TableColumn<Question, String> questionColumn = new TableColumn<>("Question");
	    questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
	    questionColumn.setPrefWidth(300); // Set preferred width for the Question column

	    TableColumn<Question, String> difficultyColumn = new TableColumn<>("Difficulty Level");
	    difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
	    difficultyColumn.setPrefWidth(200); // Set preferred width for the Difficulty Level column

	    TableColumn<Question, Void> EditDeleteColumn = new TableColumn<>("Edit/Delete");
	    EditDeleteColumn.setPrefWidth(200);
	    EditDeleteColumn.setCellFactory(param -> new EditDeleteCell());
	    
	    for (int i = 0; i < 4; i++) {
	        TableColumn<Question, String> answerColumn = new TableColumn<>("Answer " + (i + 1));
	        int answerIndex = i; // Store the current index for lambda expression
	        answerColumn.setCellValueFactory(cellData -> {
	            List<String> answers = cellData.getValue().getAnswers();
	            return new SimpleStringProperty(answers.size() > answerIndex ? answers.get(answerIndex) : "");
	        });
	        answerColumn.setPrefWidth(100); // Set preferred width for the answer column
	        questionTableView.getColumns().add(answerColumn);
	    }
	    
	    // Add columns to table view
	    questionTableView.getColumns().addAll( difficultyColumn, EditDeleteColumn);

	    // Load data from JSON and set it to the table view
	    allQuestions = sysData.loadDataFromJSON("src/WolfQuestionsDB.json"); // Store all questions
	    questionTableView.setItems(allQuestions);
	}

    @FXML
	private void searchQuestions() {
	    String searchText = searchTextField.getText().toLowerCase().trim();

	    // Split the search text into individual keywords
	    String[] keywords = searchText.split("\\s+");

	    // Create a filtered list from allQuestions
	    FilteredList<Question> filteredList = new FilteredList<>(allQuestions);

	    // Set a predicate to filter items based on the keywords
	    filteredList.setPredicate(question -> {
	        String text = question.getText().toLowerCase(); // Use getQuestion() instead of getText()
	        for (String keyword : keywords) {
	            if (!text.contains(keyword)) {
	                return false; // If any keyword is not found, exclude the question
	            }
	        }
	        return true; // Include the question if all keywords are found
	    });

	    // Set the filtered list as the new items for the TableView
	    questionTableView.setItems(filteredList);
	    searchTextField.setText("");
	}

   

    @FXML
    public void returnBackButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Home2.fxml"));
        Parent mainPageParent = loader.load();
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        mainPageParent.setTranslateY(window.getHeight());
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), mainPageParent);
        transition.setToY(0); 
        transition.play();
        
        System.out.println("Returning back"); 
    }
    @FXML
    private void sortTableByDifficulty(String selectedOption) {
        ObservableList<Question> items = questionTableView.getItems();

        if (selectedOption.equals("diffLvL low to high")) {
            SortedList<Question> sortedList = new SortedList<>(items, Comparator.comparing(Question::getDifficulty));
            questionTableView.setItems(sortedList);
        } else if (selectedOption.equals("diffLvL high to low")) {
            SortedList<Question> sortedList = new SortedList<>(items, Comparator.comparing(Question::getDifficulty).reversed());
            questionTableView.setItems(sortedList);
        }
    }

}

