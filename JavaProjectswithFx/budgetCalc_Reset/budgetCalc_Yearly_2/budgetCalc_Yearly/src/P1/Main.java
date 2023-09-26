package P1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for the BudgetTracker application.
 * 
 *This class sets up the JavaFX application and launches the user interface
 * 
 * @Author Mark Machado
 * 2023-04-10
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = loader.load();

            // Set the stage
            Scene scene = new Scene(root, 400, 300);
            primaryStage.setTitle("Budget Planner");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}