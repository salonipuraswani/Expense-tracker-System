public class UpdateExpense {

    public boolean updateExpense(String username, String desc, double newAmount) {

        for (Expense e : DataStore.userExpenses.get(username)) {

            if (e.getDescription().equals(desc)) {
                e.setAmount(newAmount);
                return true;
            }
        }

        return false;
    }
}