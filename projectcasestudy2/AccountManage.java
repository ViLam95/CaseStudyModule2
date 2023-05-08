package projectcasestudy2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AccountManage implements Manage<Account> , IOFile<Account> {
    private ArrayList<Account> accounts;
    Scanner scanner;
    String PATH_FILE = "/Users/viquoclam/Documents/CaseStudyM2/projectcasestudy2/accountlist.txt";

    public AccountManage() {
        scanner = new Scanner(System.in);
        accounts = new ArrayList<>();
        accounts = readBinary(PATH_FILE);
    }


    @Override
    public Account creat() {
        int id = accounts.size() + 1;

        String username = null;
        boolean isDuplicateUsername = true;
        while (isDuplicateUsername){
            System.out.println("Enter username: ");
            username = scanner.nextLine();
            isDuplicateUsername = false;
            for (Account account:accounts) {
                if (account.getUsername().equals(username)) {
                    isDuplicateUsername = true;
                    System.out.println("Username already exists. Please enter again.");
                    }
                }
            }

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        System.out.println("Enter full name: ");
        String fullname = scanner.nextLine();

        String email = null;
        boolean isDuplicateEmail = true;
        while (isDuplicateEmail){
            System.out.println("Enter email: ");
            email = scanner.nextLine();
            isDuplicateEmail = false;
            for (Account account:accounts) {
                if (account.getEmail().equals(email)){
                    System.out.println("Email already in use. Please re-enter another email.");
                    break;
                }
            }
            if(!isDuplicateEmail && Pattern.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)){

                break;
            }else {
                System.out.println("Improperly formatted email. Please try again!");
                isDuplicateEmail = true;
            }
        }


        String phoneNumber = null;
        boolean isDuplicatePhoneNumber = true;
        while (isDuplicatePhoneNumber){
            System.out.println("Enter number phone: ");
            phoneNumber = scanner.nextLine();
            isDuplicatePhoneNumber = false;
            for (Account account:accounts) {
                if (account.getPhoneNumber().equals(phoneNumber)){
                    isDuplicatePhoneNumber = true;
                    System.out.println("This phone number already exists. Please re-enter another number.");
                    break;
                }
            }
            if(!isDuplicatePhoneNumber && Pattern.matches("^\\d{3}\\d{3}\\d{4}$", phoneNumber)){
                break;
            }else {
                System.out.println("Invalid phone number format. Please re-enter! ");
                isDuplicatePhoneNumber = true;
            }
        }

        System.out.println("Enter address: ");
        String address = scanner.nextLine();

        Account account = new Account(id,username,password,fullname,email,phoneNumber,address);
        accounts.add(account);
        writeBinary(accounts, PATH_FILE);
        System.out.println("Sign Up Success.");
        return account;
    }

    @Override
    public Account update() {
        Account account = getById();
        if(account != null) {
            System.out.println("Enter new username: ");
            String username = scanner.nextLine();
            account.setUsername(username);

            System.out.println("Enter new password : ");
            String password = scanner.nextLine();
            account.setPassword(password);

            System.out.println("Enter new full name: ");
            String fullName = scanner.nextLine();
            account.setFullname(fullName);

            while (true) {
                System.out.println("Enter new email: ");
                String email = scanner.nextLine();
                if (Pattern.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {

                    break;
                } else {
                    System.out.println("Improperly formatted email. Please try again! ");
                }
                account.setEmail(email);
            }

            while (true) {
                System.out.println("Enter new number phone: ");
                String phoneNumber = scanner.nextLine();
            if (Pattern.matches("^\\d{3}\\d{3}\\d{4}$", phoneNumber)) {
                break;
            } else {
                System.out.println("Invalid phone number format. Please re-enter! ");
            }
            account.setPhoneNumber(phoneNumber);
        }

            System.out.println("Enter new Address: ");
            String address = scanner.nextLine();
            account.setAddress(address);

            System.out.println("Account has been updated successfully.");
        }else {
            System.out.println("Account not find!");
        }
        writeBinary(accounts, PATH_FILE);
        return account;
    }

    @Override
    public Account delete() {
        Account account = getById();
        if(account != null){
            displayAll();
            System.out.println("Enter Id Account you want to delete: ");
            accounts.remove(account);
            System.out.println("Account deleted successfully.");
        }else{
            System.out.println("Account not find!");
        }
        writeBinary(accounts, PATH_FILE);
        return account;
    }

    @Override
    public Account getById() {
        displayAll();
        System.out.println("Enter ID you want to find: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (Account account:accounts) {
            if (account.getId() == id){
                return account;
            }
        }
        return null;
    }
    @Override
    public void displayAll() {
        for (Account account: accounts) {
            System.out.println(account);
        }
    }
    public int login(){
            System.out.println("Login");
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();
        if (username.equals("lam123") && password.equals("lam123")) {
            return 1;
        }
        for (Account acc : accounts) {
                if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
                    return 0;
                }
            }
        return -1;
    }

    @Override
    public void writeBinary(List<Account> e, String path) {
        File file = new File(path);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))){
            objectOutputStream.writeObject(e);
        }catch (IOException ioException){
            System.out.println(ioException.getMessage());
        }
    }

    @Override
    public ArrayList<Account> readBinary(String path) {
        File file = new File(path);
        ArrayList<Account> accounts = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
            accounts = (ArrayList<Account>) objectInputStream.readObject();
        }catch (IOException | ClassNotFoundException ioException){
            System.out.println(ioException.getMessage());
        }
        return accounts;
    }
}
