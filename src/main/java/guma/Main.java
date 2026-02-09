package guma;

import java.io.IOException;

import guma.Guma;
import guma.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Guma using FXML.
 */
public class Main extends Application {

    private Guma guma = new Guma("src/main/data/guma.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGuma(guma);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
