import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private Login login;
    private Signup signup;

    @BeforeEach
    void setUp() {
        login = new Login();
        signup = new Signup();
        DataStore.users.clear();
        DataStore.userExpenses.clear();
    }

    @Test
    void testLoginUsernameDoesNotExist() {
        boolean result = login.login("unknownUser", "Password@1");
        assertFalse(result);
    }

    @Test
    void testLoginIncorrectPassword() {
        signup.signup("user1", "Password@1");
        boolean result = login.login("user1", "WrongPass@1");
        assertFalse(result);
    }

    @Test
    void testSuccessfulLogin() {
        signup.signup("user1", "Password@1");
        boolean result = login.login("user1", "Password@1");
        assertTrue(result);
    }
}