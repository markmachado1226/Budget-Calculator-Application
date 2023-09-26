package model;

/**
*
*The Model class acts as the main data model for the budgeting application.
*It stores the user's budget information in a HashMap
*@author Mark Machado
*
*/

public class Model {
    private BudgetMap budgetMap = new BudgetMap(0.0, 0.0, 0.0, 0.0);
    private double totalIncome;

    public void setBudget(String category, double amount) {
        budgetMap.set(category, amount);
    }

    public double getBudget(String category) {
        return budgetMap.get(category);
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getPercent(String category) {
        double budget = getBudget(category);
        if (budget == 0.0) {
            return 0.0;
        }
        return getTotalIncome() / budget * 100.0;
    }
}
