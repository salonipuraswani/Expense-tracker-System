public class Login {

    public boolean login(String username, String password) {

        return DataStore.users.containsKey(username)
                && DataStore.users.get(username).equals(password);
    }
}