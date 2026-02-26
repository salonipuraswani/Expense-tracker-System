import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class DeleteExpenseTest {

    private DeleteExpense deleteExpense;

    @BeforeEach
    void setUp() {
        DataStore.userExpenses.clear();
        deleteExpense = new DeleteExpense();
    }

    @Test
    void testDeleteExpense_Success() {
        String username = "user1";

        ArrayList<Expense> list = new ArrayList<>();
        list.add(new Expense("Food", 200.0));
        list.add(new Expense("Travel", 500.0));

        DataStore.userExpenses.put(username, list);

        boolean result = deleteExpense.deleteExpense(username, "Food");

        assertTrue(result);
        assertEquals(1, DataStore.userExpenses.get(username).size());
        assertEquals("Travel",
                DataStore.userExpenses.get(username).get(0).getDescription());
    }

    @Test
    void testDeleteExpense_DescriptionNotFound() {
        String username = "user2";

        ArrayList<Expense> list = new ArrayList<>();
        list.add(new Expense("Food", 200.0));

        DataStore.userExpenses.put(username, list);

        boolean result = deleteExpense.deleteExpense(username, "Travel");

        assertFalse(result);
        assertEquals(1, DataStore.userExpenses.get(username).size());
    }

    @Test
    void testDeleteExpense_UserNotFound() {
        boolean result = deleteExpense.deleteExpense("unknownUser", "Food");

        assertFalse(result);  // Should return false, not throw exception
    }

    @Test
    void testDeleteExpense_MultipleSameDescriptions() {
        String username = "user3";

        ArrayList<Expense> list = new ArrayList<>();
        list.add(new Expense("Food", 100.0));
        list.add(new Expense("Food", 200.0));
        list.add(new Expense("Travel", 300.0));

        DataStore.userExpenses.put(username, list);

        boolean result = deleteExpense.deleteExpense(username, "Food");

        assertTrue(result);
        assertEquals(1, DataStore.userExpenses.get(username).size());
        assertEquals("Travel",
                DataStore.userExpenses.get(username).get(0).getDescription());
    }
}