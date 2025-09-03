package Siri.JavaFx;

import Siri.Util.ConsoleLogger;
import Siri.Util.Parser;
import Siri.Util.TaskManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import Siri.Siri;
import Siri.Command;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Siri siri;
    private Parser parser;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Siri instance */
    public void setSiri(Siri s) {
        siri = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Soro's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        parser = new Parser(input);
        Command command = parser.getCommand();
        String response = siri.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSiriDialog(response, dukeImage)
        );
        userInput.clear();
    }
}

