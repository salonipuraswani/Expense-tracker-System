import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class ViewExpenseTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DataStore.userExpenses.clear();
        DataStore.users.clear();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testViewExpenses_PrintsAllExpenses() {
        String username = "user1";

        ArrayList<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("Food", 200.0));
        expenses.add(new Expense("Travel", 500.0));

        DataStore.userExpenses.put(username, expenses);

        ViewExpense view = new ViewExpense();
        view.viewExpenses(username);

        String output = outContent.toString();

        assertTrue(output.contains("Description: Food"));
        assertTrue(output.contains("Amount: 200.0"));
        assertTrue(output.contains("Description: Travel"));
        assertTrue(output.contains("Amount: 500.0"));
    }

    @Test
    void testViewExpenses_EmptyList() {
        String username = "user2";

        DataStore.userExpenses.put(username, new ArrayList<>());

        ViewExpense view = new ViewExpense();
        view.viewExpenses(username);

        String output = outContent.toString();
        assertEquals("", output);
    }

    @Test
    void testViewExpenses_UserNotFound() {
        ViewExpense view = new ViewExpense();

        assertThrows(NullPointerException.class, () -> {
            view.viewExpenses("unknown");
        });
    }
}
