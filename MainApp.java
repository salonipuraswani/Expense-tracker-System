import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            Signup signup = new Signup();
            Login login = new Login();
            AddExpense add = new AddExpense();
            UpdateExpense update = new UpdateExpense();
            DeleteExpense delete = new DeleteExpense();
            ViewExpense view = new ViewExpense();
            Report report = new Report();

            String currentUser = null;

            while (true) {

                System.out.println("\n===== EXPENSE TRACKER =====");
                System.out.println("1. Signup");
                System.out.println("2. Login");
                System.out.println("3. Exit");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        System.out.print("Enter username: ");
                        String u = sc.nextLine();
                        System.out.print("Enter password: ");
                        String p = sc.nextLine();

                        if (signup.signup(u, p))
                            System.out.println("Signup Successful!");
                        else
                            System.out.println("User already exists!");
                        break;

                    case 2:
                        System.out.print("Enter username: ");
                        u = sc.nextLine();
                        System.out.print("Enter password: ");
                        p = sc.nextLine();

                        if (login.login(u, p)) {

                            currentUser = u;
                            System.out.println("Login Successful!");

                            // USER MENU
                            while (true) {

                                System.out.println("\n1. Add Expense");
                                System.out.println("2. Update Expense");
                                System.out.println("3. Delete Expense");
                                System.out.println("4. View Expenses");
                                System.out.println("5. View Total Report");
                                System.out.println("6. Logout");

                                int ch = sc.nextInt();
                                sc.nextLine();

                                if (ch == 6) break;

                                switch (ch) {

                                    case 1:
                                        System.out.print("Description: ");
                                        String d = sc.nextLine();
                                        System.out.print("Amount: ");
                                        double a = sc.nextDouble();
                                        sc.nextLine();
                                        add.addExpense(currentUser, d, a);
                                        break;

                                    case 2:
                                        System.out.print("Description: ");
                                        d = sc.nextLine();
                                        System.out.print("New Amount: ");
                                        a = sc.nextDouble();
                                        sc.nextLine();
                                        if (update.updateExpense(currentUser, d, a))
                                            System.out.println("Updated!");
                                        else
                                            System.out.println("Not found!");
                                        break;

                                    case 3:
                                        System.out.print("Description: ");
                                        d = sc.nextLine();
                                        if (delete.deleteExpense(currentUser, d))
                                            System.out.println("Deleted!");
                                        else
                                            System.out.println("Not found!");
                                        break;

                                    case 4:
                                        view.viewExpenses(currentUser);
                                        break;

                                    case 5:
                                        System.out.println("Total: "
                                                + report.calculateTotal(currentUser));
                                        break;
                                }
                            }

                        } else {
                            System.out.println("Invalid credentials!");
                        }
                        break;

                    case 3:
                        System.exit(0);
                }
            }
        }
    }
}