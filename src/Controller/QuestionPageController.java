package Controller;

import model.Question;

import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class QuestionPageController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private AnchorPane rootPane;

	@FXML
	private TextField eqTf;

	@FXML
	private ResourceBundle resources;

	@FXML
	private TextField searchTextField;

	@FXML
	private URL location;

	@FXML
	private Button addButton;

	@FXML
	private Button deleteButton;

	@FXML
	private TableView<Question> questionTableView;

	@FXML
	private TableColumn<Question, String> QuestionsColumn;

	@FXML
	private TableColumn<Question, String> DeleteColumn;

	@FXML
	private TableColumn<Question, String> DifficultyColumn;

	@FXML
	private ImageView homeImage;

	@FXML
	private Label QuestionsPageLabel;

	@FXML
	private Pane QuestionsPagePane;

	@FXML
	private ChoiceBox<String> sortChoiceBox;

	Stage gameStage;
	private ObservableList<Question> allQuestions;

	SysData sysData = new SysData("src/WolfQuestionsDB.json");
	ObservableList<Question> data = sysData.loadDataFromJSON("src/WolfQuestionsDB.json");

	private void setFullscreen() {
		stage.setResizable(false);
		stage.setFullScreenExitHint("");
		stage.setFullScreen(true);
	}

	@SuppressWarnings("unchecked")
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

		TableColumn<Question, Void> DeleteColumn = new TableColumn<>("Delete");
		DeleteColumn.setPrefWidth(200);
		DeleteColumn.setCellFactory(param -> new EditDeleteCell());

		// Add columns to table view
		questionTableView.getColumns().addAll(questionColumn, difficultyColumn, DeleteColumn);

		// Load data from JSON and set it to the table view
		allQuestions = sysData.loadDataFromJSON("src/WolfQuestionsDB.json"); // Store all questions
		questionTableView.setItems(allQuestions);
	}

	public class EditDeleteCell extends TableCell<Question, Void> {
		private final Button deleteButton;

		public EditDeleteCell() {
			deleteButton = new Button("Delete");

			deleteButton.setOnAction(event -> {
				Question question = (Question) getTableRow().getItem();
				if (question != null) {
					sysData.deleteQuestion(question);
					allQuestions.remove(question);
				}
			});
		}

		@Override
		protected void updateItem(Void item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setGraphic(null);
			} else {
				HBox buttons = new HBox(deleteButton);
				setGraphic(buttons);
			}
		}
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
	public void switchToEditPage(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/EditPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		setFullscreen();
		stage.show();
	}

	@FXML
	private void sortTableByDifficulty(String selectedOption) {
		ObservableList<Question> items = questionTableView.getItems();

		if (selectedOption.equals("diffLvL low to high")) {
			SortedList<Question> sortedList = new SortedList<>(items, Comparator.comparing(Question::getDifficulty));
			questionTableView.setItems(sortedList);
		} else if (selectedOption.equals("diffLvL high to low")) {
			SortedList<Question> sortedList = new SortedList<>(items,
					Comparator.comparing(Question::getDifficulty).reversed());
			questionTableView.setItems(sortedList);
		}
	}

////not needed
//	public void AddButton(ActionEvent event) throws IOException {
//		try {
//
//			FXMLLoader fxmlLoader = new FXMLLoader();
//			fxmlLoader.setLocation(getClass().getResource("/application/AddQuestion.fxml"));
//			Scene addQuestionScene = new Scene(fxmlLoader.load());
//
//			gameStage = new Stage();
//			gameStage.setTitle("Add Question");
//			gameStage.setScene(addQuestionScene);
//			gameStage.setResizable(false);
//			stage.setMaximized(true);
//			gameStage.show();
//			Stage prevStage = (Stage) addButton.getScene().getWindow();
//			prevStage.hide();
//
//		} catch (IOException e) {
//			// Handle IOException more robustly with specific messages
//			e.printStackTrace();
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setTitle("Error");
//			alert.setContentText(
//					"An error occurred while loading the FXML file. Please check the file path and content.");
//			alert.showAndWait();
//		}
//	}

	@FXML
	public void returnToMainPage(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/StartMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		setFullscreen();
		stage.show();
	}

	@FXML
	public void addQ(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/AddQuestion.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setMaximized(true);
		setFullscreen();
		stage.show();
	}

}
