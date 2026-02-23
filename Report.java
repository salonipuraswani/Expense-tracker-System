public class Report {

    public double calculateTotal(String username) {

        double total = 0;

        for (Expense e : DataStore.userExpenses.get(username)) {
            total += e.getAmount();
        }

        return total;
    }
}