public class AddExpense {

    public void addExpense(String username, String desc, double amount) {

        Expense expense = new Expense(desc, amount);
        DataStore.userExpenses.get(username).add(expense);
    }
}
