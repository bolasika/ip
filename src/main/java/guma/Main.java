package guma;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Guma using FXML.
 */
public class Main extends Application {
    private Guma guma = new Guma();

    @Override
    public void start(Stage stage) {
        try {
            stage = setStage(stage);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGuma(guma, stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage setStage(Stage stage) {
        stage.setWidth(600.0);
        stage.setHeight(700.0);
        stage.setMinWidth(500.0);
        stage.setMinHeight(600.0);
        stage.setTitle("Singaporean - Guma");
        return stage;
    }

}
