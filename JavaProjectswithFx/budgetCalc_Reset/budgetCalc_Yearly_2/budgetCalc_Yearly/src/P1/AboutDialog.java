package P1;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class is a dialog window that displays information about the application.
 * The dialog contains a text area thats displays information about the purpose of the application
 * and a close button to dismiss the dialog.
 * 
 * @author Ayhan Ahsan
 */

public class AboutDialog {

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("About");
        window.setMinWidth(250);

        Label aboutLabel = new Label("About Budget Calculator");
        aboutLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextArea textArea = new TextArea();
        textArea.setText("This application was created by Ayhan Ahsan & Mark Machado.\n\n"
                + "The purpose of this application is to help you manage your budget by calculating your monthly income, monthly expenses, and savings goal. "
                + "It will then calculate and display your remaining budget based on your input.\n\n"
                + "Version: 1.0.0\n");

        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(textArea, javafx.scene.layout.Priority.ALWAYS);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(aboutLabel, textArea, closeButton);

        BorderPane root = new BorderPane();
        root.setCenter(layout);

        Scene scene = new Scene(root);
        window.setScene(scene);
        window.showAndWait();
    }

    public void show() {
        AboutDialog.display();
    }
}

