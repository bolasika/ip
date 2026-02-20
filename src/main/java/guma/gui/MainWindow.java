package guma.gui;

import guma.Guma;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private Guma guma;
    private Stage stage;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image gumaImage = new Image(this.getClass().getResourceAsStream("/images/guma.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Guma instance */
    public void setGuma(Guma g, Stage stage) {
        this.stage = stage;
        this.guma = g;
        String greeting = "Eh Hello! I am Guma from Singapore. \nWhat you want sia?"
                +
                "\nhelp command if you dont know anything hor";

        dialogContainer.getChildren().addAll(
                DialogBox.getGumaDialog(greeting, gumaImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Guma's reply and then appends them to
     * the dialog container. Clears the user input after processing. (in GUI mode)
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = guma.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGumaDialog(response, gumaImage)
        );
        if (input.equalsIgnoreCase("bye")) {
            this.stage.close();
        }
        userInput.clear();
    }
}
