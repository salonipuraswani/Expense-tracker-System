
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class UpdateExpenseTest {

    UpdateExpense updateExpense;

    @BeforeEach
    void setUp() {

        updateExpense = new UpdateExpense();

        // Clear previous data
        DataStore.users.clear();
        DataStore.userExpenses.clear();

        // Create test user
        DataStore.users.put("saniya", "1234");
        DataStore.userExpenses.put("saniya", new ArrayList<>());

        // Add initial expenses
        DataStore.userExpenses.get("saniya")
                .add(new Expense("Food", 500));

        DataStore.userExpenses.get("saniya")
                .add(new Expense("Travel", 1000));
    }

    // Test 1: Successful update
    @Test
    void testUpdateExpenseSuccessfully() {

        boolean result = updateExpense.updateExpense("saniya", "Food", 800);

        assertTrue(result);
        assertEquals(800,
                DataStore.userExpenses.get("saniya").get(0).getAmount());
    }

    // Test 2: Expense not found
    @Test
    void testUpdateExpenseNotFound() {

        boolean result = updateExpense.updateExpense("saniya", "Shopping", 700);

        assertFalse(result);
    }

    // Test 3: User not found
    @Test
    void testUserNotFound() {

        boolean result = updateExpense.updateExpense("unknown", "Food", 900);

        assertFalse(result);
    }

    // Test 4: Update with zero amount
    @Test
    void testUpdateWithZeroAmount() {

        boolean result = updateExpense.updateExpense("saniya", "Food", 0);

        assertTrue(result);
        assertEquals(0,
                DataStore.userExpenses.get("saniya").get(0).getAmount());
    }

    // Test 5: Update with negative amount
    @Test
    void testUpdateWithNegativeAmount() {

        boolean result = updateExpense.updateExpense("saniya", "Food", -200);

        assertTrue(result);
        assertEquals(-200,
                DataStore.userExpenses.get("saniya").get(0).getAmount());
    }

    // Test 6: Multiple expenses – only correct one updates
    @Test
    void testOnlyMatchingExpenseUpdates() {

        updateExpense.updateExpense("saniya", "Travel", 1500);

        assertEquals(500,
                DataStore.userExpenses.get("saniya").get(0).getAmount());

        assertEquals(1500,
                DataStore.userExpenses.get("saniya").get(1).getAmount());
    }
}
