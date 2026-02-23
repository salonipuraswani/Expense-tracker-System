public class ViewExpense {

    public void viewExpenses(String username) {

        for (Expense e : DataStore.userExpenses.get(username)) {

            System.out.println("Description: " + e.getDescription());
            System.out.println("Amount: " + e.getAmount());
            System.out.println("----------------------");
        }
    }
}
