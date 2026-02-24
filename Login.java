public class Login {

    public boolean login(String username, String password) {

        if(!DataStore.users.containsKey(username)){
            System.out.println("Username does not exist!Please sign up first");
            return false;
        }
        if(!DataStore.users.get(username).equals(password)) {
            System.out.println("Incorrect password!Please try again.");
            return false;
        }
        return true;
    }
}