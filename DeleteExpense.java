public class DeleteExpense {

    public boolean deleteExpense(String username, String desc) {

        return DataStore.userExpenses.get(username)
                .removeIf(e -> e.getDescription().equals(desc));
    }
}