import java.util.ArrayList;

public class DeleteExpense {

public boolean deleteExpense(String username, String desc) {

    ArrayList<Expense> list = DataStore.userExpenses.get(username);

    if (list == null || list.isEmpty()) {
        return false;
    }

    return list.removeIf(e -> e.getDescription().equals(desc));
}
}