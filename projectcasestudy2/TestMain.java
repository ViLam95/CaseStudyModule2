package projectcasestudy2;

import java.util.Scanner;

public class TestMain {
    private static final Scanner scanner = new Scanner(System.in);
    private static FlightManage flightManage= new FlightManage();
    public static void adminMenu(AccountManage accountManage){
        int choice = -1;
        do{
            System.out.println("====================Menu====================");
            System.out.println("1. Create a new account");
            System.out.println("2. Update account information");
            System.out.println("3. Delete account");
            System.out.println("4. Show all account information");
            System.out.println("5. Action with Flight Manage");
            System.out.println("0. Log out");

            System.out.println("Enter your selection:");
            try{
                choice =Integer.parseInt(scanner.nextLine());
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            switch (choice){
                case 1:
                    accountManage.creat();
                    break;
                case 2:
                    accountManage.update();
                    break;
                case 3:
                    accountManage.delete();
                    break;
                case 4:
                    accountManage.displayAll();
                    break;
                case 5:
                    flightMenu(flightManage);
                case 0:
                    System.out.println("Logout successful!");
                    break;
                default:
                    System.out.println("Account does not exist. Please choose again!");
            }
            if (choice==0){
                break;
            }
        }while (true);
    }

    public static void flightMenu(FlightManage flightManage){
        int choice = -1;
        while (true) {
            System.out.println("==========Menu==========");
            System.out.println("1. Creat new flight.");
            System.out.println("2. Update flight information.");
            System.out.println("3. Delete flight.");
            System.out.println("4. Get flight information.");
            System.out.println("5. Show all ticket information");
            System.out.println("6. Check the number of tickets left.");
            System.out.println("0. Exit!");

            System.out.println("Enter your selection:");
            try{
                choice =Integer.parseInt(scanner.nextLine());
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            switch (choice) {
                case 1:
                    flightManage.creat();
                    break;
                case 2:
                    flightManage.update();
                    break;
                case 3:
                    flightManage.delete();
                    break;
                case 4:
                    flightManage.getById();
                    break;
                case 5:
                    flightManage.displayAll();
                    break;
                case 6:
                    flightManage.checkQuantityTickets();
                    break;
                case 0:
                    System.out.println("Exit!");
                    break;
            }
            if (choice==0){
                break;
            }
        }
    }

    public static void bookingMenu(FlightManage flightManage){
        int choice = -1;
        while (true) {
            System.out.println("==========Menu==========");
            System.out.println("1. Booking ");
            System.out.println("2. Cancel Booking ");
            System.out.println("3. Payment ");
            System.out.println("0. Exit!");

            System.out.println("Enter your selection:");
            try{
                choice =Integer.parseInt(scanner.nextLine());
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            switch (choice){
                case 1:
                    flightManage.bookTicket();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    System.out.println("Exit!");
                    break;
            }
            if (choice==0){
                break;
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManage accountManage = new AccountManage();
        new FlightManage();
        int choice = -1;
        do {
            System.out.println("==========Menu==========");
            System.out.println("1. Register.");
            System.out.println("2. Login.");
            System.out.println("0. Exit!");

            System.out.println("Enter your selection:");
            try{
                choice =Integer.parseInt(scanner.nextLine());
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            switch (choice){
                case 1:
                    accountManage.creat();
                    break;
                case 2:
                    int res = accountManage.login();
                    if(res == 1){
                        adminMenu(accountManage);
                    }
                    else if(res == 0){
                        System.out.println("Logged in successfully.");
                        bookingMenu(flightManage);
                    }
                    else {
                        System.out.println("Login failed.");
                    }
                    break;
                case 0:
                    System.exit(0);
                    System.out.println("Exit!");
                    break;
                default:
                    System.out.println("Account does not exist! Please re-enter.");
            }
        }while (true);
    }
}
