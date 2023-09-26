package model;

import java.util.HashMap;
import java.util.Map;

/**
*
*This class represents a budget map that contains the user's income, expenses, and savings.
*It also includes the user's savings goal. The class is implemented using a HashMap.
*@author Ayhan Ahsan & Mark Machado
*
*/

public class BudgetMap {
    private Map<String, Double> map = new HashMap<>();
    private double savingsGoal;

    public BudgetMap(double initialIncome, double initialExpenses, double initialSavings, double initialSavingsGoal) {
        set("Income", initialIncome);
        set("Expense", initialExpenses);
        setSavings(initialSavings);
        setSavingsGoal(initialSavingsGoal);
    }

    public void set(String category, double amount) {
        map.put(category, amount);
    }

    public double get(String category) {
        if (map.containsKey(category)) {
            return map.get(category);
        }
        return 0.0;
    }

    public void setSavings(double savings) {
        set("Savings", savings);
    }

    public double getSavings() {
        return get("Savings");
    }

    public void setSavingsGoal(double savingsGoal) {
        this.savingsGoal = savingsGoal;
    }

    public double getSavingsGoal() {
        return savingsGoal;
    }

    public double getTotalIncome() {
        double totalIncome = 0.0;
        for (String key : map.keySet()) {
            if (key.startsWith("Income")) {
                totalIncome += map.get(key);
            }
        }
        return totalIncome;
    }

    public double getTotalExpenses() {
        double totalExpenses = 0.0;
        for (String key : map.keySet()) {
            if (key.startsWith("Expense")) {
                totalExpenses += map.get(key);
            }
        }
        return totalExpenses;
    }
}
