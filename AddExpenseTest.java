
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class AddExpenseTest {

    AddExpense addExpense;

    @BeforeEach
    void setUp() {
        addExpense = new AddExpense();

        // Clear previous data
        DataStore.users.clear();
        DataStore.userExpenses.clear();

        // Add test user
        DataStore.users.put("saniya", "1234");
        DataStore.userExpenses.put("saniya", new ArrayList<>());
    }

    @Test
    void testAddExpenseSuccessfully() {

        addExpense.addExpense("saniya", "Food", 500);

        assertEquals(1, DataStore.userExpenses.get("saniya").size());
        //assertEquals("Food", DataStore.userExpenses.get("saniya").get(0).getDesc());
        assertEquals(500, DataStore.userExpenses.get("saniya").get(0).getAmount());
    }

    @Test
    void testAddMultipleExpenses() {

        addExpense.addExpense("saniya", "Food", 500);
        addExpense.addExpense("saniya", "Travel", 1000);

        assertEquals(2, DataStore.userExpenses.get("saniya").size());
    }

    @Test
    void testUserNotFound() {

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> addExpense.addExpense("unknown", "Food", 500)
        );

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testAddExpenseWithZeroAmount() {

        addExpense.addExpense("saniya", "Test", 0);

        assertEquals(1, DataStore.userExpenses.get("saniya").size());
        assertEquals(0, DataStore.userExpenses.get("saniya").get(0).getAmount());
    }

    @Test
    void testAddExpenseWithNegativeAmount() {

        addExpense.addExpense("saniya", "Refund", -200);

        assertEquals(-200, DataStore.userExpenses.get("saniya").get(0).getAmount());
    }
}