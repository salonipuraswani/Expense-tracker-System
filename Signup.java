public class Signup {

    public boolean signup(String username, String password) {

        if (DataStore.users.containsKey(username)) {
            return false;
        }

        DataStore.users.put(username, password);
        DataStore.userExpenses.put(username, new java.util.ArrayList<>());
        return true;
    }
}

