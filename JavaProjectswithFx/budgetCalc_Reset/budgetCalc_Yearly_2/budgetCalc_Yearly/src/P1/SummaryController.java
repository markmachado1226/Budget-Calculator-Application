package P1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.BudgetMap;

/**
 * SummaryController is the controller class for the summary.fxml.
 * It manages the display of summary information such as total income, total expenses, and savings.
 * It also displays the user's progress towards their savings goal and their overall budget status.
 * 
 * This controller communicates with the Model class to retrieve and display the necessary data.
 * 
 * 
 * 
 * @author Ayhan Ahsan
 */

public class SummaryController {

    @FXML
    private Label totalIncomeLabel;

    @FXML
    private Label totalExpensesLabel;

    @FXML
    private Label savingsLabel;

    @FXML
    private Label savingsStatusLabel;

    @FXML
    private Label budgetStatusLabel;

    @FXML
    private Label budgetStatusValueLabel;

    @FXML
    private Label savingsGoalLabel;

    @FXML
    private Label remainingBudgetLabel;

    public SummaryController(double totalIncome, double totalExpenses, double savings, double savingsGoal) {
        new BudgetMap(totalIncome, totalExpenses, savings, savingsGoal);
        updateSummary(totalIncome, totalExpenses, savings, savingsGoal);
    }

    public void setBudgetMap(double totalIncome, double totalExpenses, double savings, double savingsGoal) {
        new BudgetMap(totalIncome, totalExpenses, savings, savingsGoal);
        updateSummary(totalIncome, totalExpenses, savings, savingsGoal);
    }

    public void updateSummary(double totalIncome, double totalExpenses, double savings, double savingsGoal) {
        totalIncomeLabel.setText(String.format("$%.2f", totalIncome));
        totalExpensesLabel.setText(String.format("$%.2f", totalExpenses));
        savingsLabel.setText(String.format("$%.2f", savings));
        savingsGoalLabel.setText(String.format("$%.2f", savingsGoal));

        double remainingBudget = totalIncome - totalExpenses;
        remainingBudgetLabel.setText(String.format("$%.2f", remainingBudget));

        double budgetStatus = (remainingBudget / totalIncome) * 100;
        budgetStatusValueLabel.setText(String.format("%.2f%%", budgetStatus));

        if (budgetStatus < 0) {
            budgetStatusLabel.setText("Deficit");
            budgetStatusLabel.setTextFill(javafx.scene.paint.Color.RED);
        } else if (budgetStatus < 50) {
            budgetStatusLabel.setText("Needs Improvement");
            budgetStatusLabel.setTextFill(javafx.scene.paint.Color.ORANGE);
        } else if (budgetStatus < 100) {
            budgetStatusLabel.setText("Good");
            budgetStatusLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        } else {
            budgetStatusLabel.setText("Excellent");
            budgetStatusLabel.setTextFill(javafx.scene.paint.Color.BLUE);
        }

        if (savings < savingsGoal) {
            savingsStatusLabel.setText("Under Budget");
            savingsStatusLabel.setTextFill(javafx.scene.paint.Color.RED);
        } else {
            savingsStatusLabel.setText("On Track");
            savingsStatusLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        }
    }
    
}
