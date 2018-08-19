package application.ui;

import application.AddressBook;
import application.Address;
import application.Contact;
import java.util.Scanner;
import java.util.Set;
import java.util.List;

public class UserInterface {

    private final AddressBook book = new AddressBook();
    private final Scanner reader = new Scanner(System.in);

    public void start() {
        System.out.println("phone search");
        printAvailableOperations();
        while (true) {
            String command = getUserInput("\ncommand: ");
            if (command.equals("x")) {
                break;
            }
            handleCommands(command);
        }
    }

    private String getUserInput(String prompt) {
        System.out.print(prompt);
        return reader.nextLine();
    }

    private void printAvailableOperations() {
        System.out.println("available operations:\n"
                + " 1 add a number\n"
                + " 2 search for a number\n"
                + " 3 search for a person by phone number\n"
                + " 4 add an address\n"
                + " 5 search for personal information\n"
                + " 6 delete personal information\n"
                + " 7 filtered listing\n"
                + " x quit");
    }

    private void printPersonalInformation(Contact contact) {
        Address address = contact.getAddress();
        if (address == null) {
            System.out.println("  address unknown");
        } else {
            System.out.println("  address: " + address);
        }
        if (contact.getNumbers().isEmpty()) {
            System.out.println("  phone number not found");
        } else {
            System.out.println("  phone numbers:");
            for (String number : contact.getNumbers()) {
                System.out.println("   " + number);
            }
        }
    }

    private void handleCommands(String command) {
        // could use switch statement, used if-else to be backward compatible
        if (command.equals("1")) {
            addNumber();
        } else if (command.equals("2")) {
            searchForNumber();
        } else if (command.equals("3")) {
            searchForPersonByNumber();
        } else if (command.equals("4")) {
            addAddress();
        } else if (command.equals("5")) {
            searchForPersonalInformation();
        } else if (command.equals("6")) {
            deletePersonalInformation();
        } else if (command.equals("7")) {
            filteredListing();
        } else {
            invalidCommand();
        }
    }

    private void addNumber() {
        String name = getUserInput("whose number: ");
        String number = getUserInput("number: ");
        book.addNumber(name, number);
    }

    private void searchForNumber() {
        String name = getUserInput("whose number: ");
        Set<String> numbers = book.searchForNumber(name);
        if (numbers == null || numbers.isEmpty()) {
            System.out.println(" not found");
        } else {
            for (String number : numbers) {
                System.out.println(" " + number);
            }
        }
    }

    private void searchForPersonByNumber() {
        String number = getUserInput("number: ");
        Set<String> names = book.searchForPersonByNumber(number);
        if (names.isEmpty()) {
            System.out.println(" not found");
        } else {
            for (String name : names) {
                System.out.println(" " + name);
            }
        }
    }

    private void addAddress() {
        String name = getUserInput("whose address: ");
        String street = getUserInput("street: ");
        String city = getUserInput("city: ");
        Address address = new Address(street, city);
        book.addAddress(name, address);
    }

    private void searchForPersonalInformation() {
        String name = getUserInput("whose information: ");
        Contact contact = book.searchForPersonalInformation(name);
        if (contact == null) {
            System.out.println(" not found");
        } else {
            printPersonalInformation(contact);
        }
    }

    private void deletePersonalInformation() {
        String name = getUserInput("whose information: ");
        book.deletePersonalInformation(name);
    }

    private void filteredListing() {
        String keyword = getUserInput("keyword (if empty, all listed): ");
        List<Contact> contacts = book.filteredListing(keyword);
        if (contacts.isEmpty()) {
            System.out.println(" keyword not found");
        } else {
            for (Contact contact : contacts) {
                System.out.println("\n " + contact.getName());
                printPersonalInformation(contact);
            }
        }
    }

    private void invalidCommand() {
        printAvailableOperations();
    }

}
