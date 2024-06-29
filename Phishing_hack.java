package projext01;
import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Phishing_hack {
	
	
	    private static String username = null;
	    private static String password = "";
	    private static String emergencyContact1 = "";
	    private static String emergencyContact2 = "";
	    private static final String DATA_FILE_PATH = "D:\\Java Data\\logindata.txt";

	    public static void main(String[] args) {
	        displayMenu();
	    }

	    public static void displayMenu() {
	        Scanner sc = new Scanner(System.in);
	        try {

	        for(int i=0;;){
	            System.out.println("Select an option from the menu:");
	            System.out.println("1. Register");
	            System.out.println("2. Login");
	            System.out.println("3. Exit");

	            int choice = sc.nextInt();

	            switch (choice) {
	                case 1:
	                    register();
	                    break;
	                case 2:
	                    if (login()) {
	                        menu();
	                    } else {
	                        System.out.println("Login failed.");
	                    }
	                    break;
	                case 3:
	                    System.out.println("Exiting...");
	                    System.exit(0);
	                default:
	                    System.out.println("Invalid option. Please try again.");
	            }
	        }
	        }
	        catch(Exception exp) {
	        	System.out.println("only numbers you enter");
	        }
	    }

	    public static void register() {
	        Scanner sc = new Scanner(System.in);
	        System.out.print("Enter a username: ");
	        username = sc.nextLine();
	        System.out.print("Enter a password: ");
	        password = hashPassword(sc.nextLine());
	        saveUserDetails();
	        System.out.println("Registration successful!");
	    }

	    public static boolean login() {
	        Scanner sc = new Scanner(System.in);
	        System.out.print("Enter a username: ");
	        username = sc.nextLine();
	        System.out.print("Enter a password: ");
	        String enteredPassword = hashPassword(sc.nextLine());
	        return isLoginSuccessful(enteredPassword);
	    }

	    public static String hashPassword(String input) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            byte[] hashBytes = md.digest(input.getBytes());
	            StringBuilder hexString = new StringBuilder(2 * hashBytes.length);

	            for (byte b : hashBytes) {
	                hexString.append(String.format("%02x", b));
	            }

	            return hexString.toString();
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException("Password hashing failed.");
	        }
	    }

	    public static void saveUserDetails() {
	        try (BufferedWriter output = new BufferedWriter(new FileWriter(DATA_FILE_PATH, true))) {
	            output.write(username + "," + password);
	            output.newLine();
	        } catch (IOException e) {
	            System.out.println("Failed to save user details.");
	        }
	    }

	    public static boolean isLoginSuccessful(String enteredPassword) {
	        try (BufferedReader buffReader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
	            String line;
	            while ((line = buffReader.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(enteredPassword)) {
	                    return true;
	                }
	            }
	        } catch (IOException e) {
	            System.out.println("Failed to read user details.");
	        }
	        return false;
	    }

	    public static void menu() {
	        Scanner sc = new Scanner(System.in);

	        while (true) {
	            System.out.println("Select an option from the menu:");
	            System.out.println("1. Add emergency contacts");
	            System.out.println("2. Profile");
	            System.out.println("3. Danger");
	            System.out.println("4. Back");

	            int choice = sc.nextInt();

	            switch (choice) {
	                case 1:
	                    addEmergencyContacts();
	                    System.out.println("Emergency contacts added successfully.");
	                    break;
	                case 2:
	                    displayProfile();
	                    break;
	                case 3:
	                    alertDanger();
	                    break;
	                case 4:
	                    displayMenu();
	                    break;
	                default:
	                    System.out.println("Invalid option. Please try again.");
	            }
	        }
	    }

	    public static void addEmergencyContacts() {
	        Scanner sc = new Scanner(System.in);
	        System.out.print("Enter your first emergency contact (10-digit phone number): ");
	        emergencyContact1 = sc.next();
	        System.out.print("Enter your second emergency contact (10-digit phone number): ");
	        emergencyContact2 = sc.next();
	    }

	    public static void displayProfile() {
	        System.out.println("Username: " + username);
	        System.out.println("Password: " + "********"); // Password is not displayed
	        System.out.println("Emergency Contact 1: " + emergencyContact1);
	        System.out.println("Emergency Contact 2: " + emergencyContact2);
	    }

	    public static void alertDanger() {
	        if (emergencyContact1.isEmpty() && emergencyContact2.isEmpty()) {
	            System.out.println("Please add your emergency contacts.");
	        } else {
	            System.out.println("I am in danger!");
	            System.out.println("Sending message and location...");
	            // Implement sending messages and location to emergency contacts here.
	        }
	    }
	}


