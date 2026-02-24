public class Signup {

    public boolean signup(String username, String password) {
        if (DataStore.users.containsKey(username)) {
            System.out.println("Username already exists!");
            return false;
        }
        if(username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password cannot be empty!");
            return false;
        }
        if(password.length() < 6) {
            //boundry test for password length
            System.out.println("Password must be at least 6 characters long!");
            return false;
        }
        if(username.contains(" ")) {
            System.out.println("Username cannot contain spaces!");
            return false;
        }
        if(password.contains(" ")) {
            System.out.println("Password cannot contain spaces!");
            return false;
        }
        if(!username.matches("[a-zA-Z0-9_]+")) {
            System.out.println("Username can only contain letters, numbers, and underscores!");
            return false;
        }
        if(password.length() > 20) {
            //boundry test for password length
            System.out.println("Password cannot be longer than 20 characters!");
            return false;
        }
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$")) {
            //validation test for password complexity
            System.out.println("Password must contain uppercase, lowercase, number and special character!");
            return false;
        }
        DataStore.users.put(username, password);
        DataStore.userExpenses.put(username, new java.util.ArrayList<>());
        return true;
    }
}

