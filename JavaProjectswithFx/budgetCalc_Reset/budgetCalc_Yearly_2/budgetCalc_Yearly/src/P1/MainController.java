package P1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The MainController class is the controller for the main view of the budget
 * application. It handles user input and updates the display with the results.
 *
 * @author Ayhan Ahsan & Mark Machado
 */

public class MainController {

    @FXML
    private Button saveButton;

    @FXML
    private TextField monthlyIncomeField;

    @FXML
    private TextField monthlyExpensesField;

    @FXML
    private TextField savingsGoalField;

    @FXML
    private Label monthlyIncomeLabel;

    @FXML
    private Label monthlyExpensesLabel;

    @FXML
    private Label savingsGoalLabel;

    @FXML
    private Label remainingBudgetLabel;

    private Map<String, Double> budgetMap = new HashMap<>();

    private File budgetFile = new File("budget.txt");

    @FXML
    private void initialize() {
        // Load budget data from file if it exists
        if (budgetFile.exists()) {
            try {
                Scanner scanner = new Scanner(budgetFile);
                while (scanner.hasNextLine()) {
                    String[] line = scanner.nextLine().split("=");
                    String key = line[0];
                    Double value = Double.parseDouble(line[1]);
                    budgetMap.put(key, value);
                }
                scanner.close();
                updateLabels();
            } catch (FileNotFoundException e) {
                showErrorDialog("Error loading budget data from file");
            } catch (NumberFormatException e) {
                showErrorDialog("Error loading budget data from file: invalid format");
            }
        }
    }

    @FXML
    private void handleSaveButtonAction() {
        try {
            // Get user input
            Double monthlyIncome = Double.parseDouble(monthlyIncomeField.getText());
            Double monthlyExpenses = Double.parseDouble(monthlyExpensesField.getText());
            Double savingsGoal = Double.parseDouble(savingsGoalField.getText());
            // Calculate remaining budget
            Double remainingBudget = monthlyIncome - monthlyExpenses;
            // Store budget data in map
            budgetMap.put("Monthly Income", monthlyIncome);
            budgetMap.put("Monthly Expenses", monthlyExpenses);
            budgetMap.put("Savings Goal", savingsGoal);
            budgetMap.put("Remaining Budget", remainingBudget);
            // Update labels
            updateLabels();
            // Save budget data to file
            FileWriter writer = new FileWriter(budgetFile);
            for (Map.Entry<String, Double> entry : budgetMap.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
            }
            writer.close();
            // Show success dialog
            showInfoDialog("Budget data saved successfully");
        } catch (NumberFormatException e) {
            showErrorDialog("Invalid input: please enter numbers only");
        } catch (IOException e) {
            showErrorDialog("Error saving budget data to file");
        }
    }

    private void showInfoDialog(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateLabels() {
        monthlyIncomeLabel.setText(" $" + budgetMap.get("Monthly Income"));
        monthlyExpensesLabel.setText(" $" + budgetMap.get("Monthly Expenses"));
        savingsGoalLabel.setText(" $" + budgetMap.get("Savings Goal"));
        remainingBudgetLabel.setText(" $" + budgetMap.get("Remaining Budget"));
    }
    
    @FXML
    private void handleLoadButtonAction(ActionEvent event) {
        try {
            // Load budget data from file
            Scanner scanner = new Scanner(budgetFile);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("=");
                String key = line[0];
                Double value = Double.parseDouble(line[1]);
                budgetMap.put(key, value);
            }
            scanner.close();
            // Update labels
            updateLabels();
            // Show success dialog
            showInfoDialog("Budget data loaded successfully");
        } catch (FileNotFoundException e) {
            showErrorDialog("Error loading budget data from file");
        } catch (NumberFormatException e) {
            showErrorDialog("Error loading budget data from file: invalid format");
        }
    }
    
    @FXML
    private void handleResetButtonAction(ActionEvent event) {
        // Clear all fields and labels
        monthlyIncomeField.setText("");
        monthlyExpensesField.setText("");
        savingsGoalField.setText("");
        monthlyIncomeLabel.setText("$0.00");
        monthlyExpensesLabel.setText("$0.00");
        savingsGoalLabel.setText("$0.00");
        remainingBudgetLabel.setText("$0.00");
        // Clear budget map
        budgetMap.clear();
        // Delete budget file
        budgetFile.delete();
        // Show success dialog
        showInfoDialog("Budget data reset successfully");
    }
    
    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        // Close the application
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void viewSummaryButtonClicked(ActionEvent event) {
    try {
        // Load the FXML file using the FXMLLoader
        Stage stage = new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(); 
        Pane root = fxmlLoader.load(getClass().getResource("summary.fxml").openStream());

        // Create a new stage to display the summary window
   
        stage.setTitle("Summary");
        stage.setScene(new Scene(root,600,600));    
        stage.showAndWait();
    } catch (IOException e) {
        System.err.println("Error loading Summary Window: " + e.getMessage());
    }
}

@FXML
private void handleAboutButtonAction(ActionEvent event) {
    AboutDialog dialog = new AboutDialog();
    dialog.show();
    }
}  