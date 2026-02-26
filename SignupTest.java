import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SignupTest {

    private Signup signup;

    @BeforeEach
    void setUp() {
        signup = new Signup();
        DataStore.users.clear();
        DataStore.userExpenses.clear();
    }

    // -------------------- PRECONDITION TESTS --------------------

    @Test
    void testEmptyUsername() {
        boolean result = signup.signup("", "Password@1");
        assertFalse(result);
    }

    @Test
    void testEmptyPassword() {
        boolean result = signup.signup("user1", "");
        assertFalse(result);
    }

    @Test
    void testUsernameAlreadyExists() {
        signup.signup("user1", "Password@1");
        boolean result = signup.signup("user1", "Password@1");
        assertFalse(result);
    }

    @Test
    void testPasswordLessThan6Characters() {
        boolean result = signup.signup("user1", "Pa@1");
        assertFalse(result);
    }

    @Test
    void testPasswordGreaterThan20Characters() {
        boolean result = signup.signup("user1", "VeryLongPassword@12345");
        assertFalse(result);
    }

    @Test
    void testUsernameContainsSpaces() {
        boolean result = signup.signup("user name", "Password@1");
        assertFalse(result);
    }

    @Test
    void testPasswordContainsSpaces() {
        boolean result = signup.signup("user1", "Password @1");
        assertFalse(result);
    }

    @Test
    void testUsernameInvalidCharacters() {
        boolean result = signup.signup("user$", "Password@1");
        assertFalse(result);
    }

    @Test
    void testPasswordMissingUppercase() {
        boolean result = signup.signup("user1", "password@1");
        assertFalse(result);
    }

    @Test
    void testPasswordMissingLowercase() {
        boolean result = signup.signup("user1", "PASSWORD@1");
        assertFalse(result);
    }

    @Test
    void testPasswordMissingNumber() {
        boolean result = signup.signup("user1", "Password@");
        assertFalse(result);
    }

    @Test
    void testPasswordMissingSpecialCharacter() {
        boolean result = signup.signup("user1", "Password1");
        assertFalse(result);
    }

    // -------------------- BOUNDARY TESTS --------------------

    @Test
    void testPasswordExactly6Characters() {
        boolean result = signup.signup("user1", "Pa@123");
        assertTrue(result);
    }

    @Test
    void testPasswordExactly20Characters() {
        boolean result = signup.signup("user2", "Password@123456789");
        assertTrue(result);
    }

    // -------------------- POSTCONDITION TEST --------------------

    @Test
    void testSuccessfulSignupIncreasesDatastoreSize() {
        int initialSize = DataStore.users.size();
        boolean result = signup.signup("newuser", "Password@1");

        assertTrue(result);
        assertEquals(initialSize + 1, DataStore.users.size());
        assertTrue(DataStore.userExpenses.containsKey("newuser"));
    }
}