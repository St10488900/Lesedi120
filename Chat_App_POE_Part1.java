package lesedi.project;

import java.util.Scanner;

public class Chat_App_POE_Part1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // --- REGISTRATION SECTION ---
        Registration user = null;
        String results = "";

        while (!results.equals("User successfully registered.")) {
            System.out.println("\n--- REGISTRATION ---");
            System.out.print("Enter your First Name: ");
            String fName = scanner.nextLine();

            System.out.print("Enter your Last Name: ");
            String lName = scanner.nextLine();

            System.out.print("Enter your username: ");
            String uName = scanner.nextLine();

            System.out.print("Enter your password: ");
            String pWord = scanner.nextLine();

            System.out.print("Enter CellphoneNumber (+27...): ");
            String cell = scanner.nextLine();
            

            user = new Registration(fName, lName, uName, pWord, cell);
            results = user.registerUser();
            System.out.println(results);
        }

        // --- LOGIN SECTION ---
        boolean isLogged = false;
        System.out.println("\nNow, please log in to verify your account.");

        while (!isLogged) {
            System.out.println("\n--- LOGIN ---");
            System.out.print("Enter Username: ");
            String loginUser = scanner.nextLine();

            System.out.print("Enter Password: ");
            String loginPass = scanner.nextLine();

            isLogged = user.loginUser(loginUser, loginPass);
            System.out.println(user.returnLoginStatus(isLogged));
        }

        // --- QUICKCHAT SECTION ---
        System.out.println("\nWelcome to QuickChat.");

        System.out.print("\nHow many messages do you want to send? ");
        int numMessages = Integer.parseInt(scanner.nextLine());

        int messagesSentCount = 0;
        boolean running = true;

        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    if (messagesSentCount >= numMessages) {
                        System.out.println("You have reached your message limit of " + numMessages + ".");
                        break;
                    }

                    messagesSentCount++;

                    // Recipient validation
                    String recipient;
                    while (true) {
                        System.out.print("Enter recipient cell number (with international code, max 10 chars): ");
                        recipient = scanner.nextLine();
                        Message tempMsg = new Message(recipient, "test", messagesSentCount);
                        String cellCheck = tempMsg.checkRecipientCell();
                        System.out.println(cellCheck);
                        if (cellCheck.equals("Cell phone number successfully captured.")) {
                            break;
                        }
                    }

                    // Message validation
                    String messageText;
                    while (true) {
                        System.out.print("Enter your message (max 250 characters): ");
                        messageText = scanner.nextLine();
                        if (messageText.length() > 250) {
                            System.out.println("Please enter a message of less than 250 characters.");
                        } else {
                            break;
                        }
                    }

                    Message msg = new Message(recipient, messageText, messagesSentCount);

                    System.out.println("\n--- Message Details ---");
                    System.out.println("Message ID: " + msg.getMessageID());
                    System.out.println("Message Hash: " + msg.getMessageHash());
                    System.out.println("Recipient: " + msg.getRecipient());
                    System.out.println("Message: " + msg.getMessage());

                    System.out.println("\nWhat would you like to do?");
                    System.out.println("1) Send Message");
                    System.out.println("2) Disregard Message");
                    System.out.println("3) Store Message to send later");
                    System.out.print("Choose: ");
                    int sendChoice = Integer.parseInt(scanner.nextLine());
                    System.out.println(msg.SentMessage(sendChoice));
                    break;

                case "2":
                    System.out.println("Coming Soon.");
                    break;

                case "3":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

        // Display all messages and total
        System.out.println("\n--- All Sent Messages ---");
        System.out.println(Message.printMessages());
        System.out.println("Total messages sent: " + Message.returnTotalMessagess());
    }
}
