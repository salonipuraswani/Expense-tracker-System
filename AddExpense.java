public class AddExpense {

   public void addExpense(String username, String desc, double amount) {

    if (!DataStore.userExpenses.containsKey(username)) {
        throw new IllegalArgumentException("User not found");
    }

    Expense expense = new Expense(desc, amount);
    DataStore.userExpenses.get(username).add(expense);
}

}

