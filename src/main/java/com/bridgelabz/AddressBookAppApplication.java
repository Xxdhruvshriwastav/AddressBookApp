package com.bridgelabz;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressBookAppApplication {

    // UC1: Create Contact (read details)
    public static Contact createContact(Scanner sc) {
        System.out.println("Enter Contact Details");

        System.out.print("First Name: ");
        String firstName = sc.nextLine();

        System.out.print("Last Name: ");
        String lastName = sc.nextLine();

        System.out.print("Address: ");
        String address = sc.nextLine();

        System.out.print("City: ");
        String city = sc.nextLine();

        System.out.print("State: ");
        String state = sc.nextLine();

        System.out.print("Zip: ");
        String zip = sc.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        return new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
    }

    // UC2 + UC6 + UC7: Add Contact with unique/duplicate prevention by firstName
    public static void addContact(ArrayList<Contact> addressBook, Scanner sc) {
        Contact contact = createContact(sc);

        boolean exists = addressBook.stream()
                .anyMatch(c -> c.getFirstName().equalsIgnoreCase(contact.getFirstName()));

        if (exists) {
            System.out.println("Contact with this name already exists! Not added.");
        } else {
            addressBook.add(contact);
            System.out.println("Contact Added Successfully!");
        }
    }

    // UC3: Edit Contact
    public static void editContact(ArrayList<Contact> addressBook, Scanner sc) {
        System.out.print("Enter first name of contact to edit: ");
        String name = sc.nextLine();

        for (Contact contact : addressBook) {
            if (contact.getFirstName().equalsIgnoreCase(name)) {

                System.out.println("Enter new Address:");
                contact.setAddress(sc.nextLine());

                System.out.println("Enter new City:");
                contact.setCity(sc.nextLine());

                System.out.println("Enter new State:");
                contact.setState(sc.nextLine());

                System.out.println("Enter new Zip:");
                contact.setZip(sc.nextLine());

                System.out.println("Enter new Phone:");
                contact.setPhoneNumber(sc.nextLine());

                System.out.println("Enter new Email:");
                contact.setEmail(sc.nextLine());

                System.out.println("Contact Updated Successfully!");
                return;
            }
        }

        System.out.println("Contact not found!");
    }

    // UC4: Delete Contact
    public static void deleteContact(ArrayList<Contact> addressBook, Scanner sc) {
        System.out.print("Enter first name of contact to delete: ");
        String name = sc.nextLine();

        Iterator<Contact> iterator = addressBook.iterator();

        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getFirstName().equalsIgnoreCase(name)) {
                iterator.remove();
                System.out.println("Contact deleted successfully!");
                return;
            }
        }

        System.out.println("User not found");
    }

    // UC5: Add Multiple Contacts
    public static void addMultipleContacts(ArrayList<Contact> addressBook, Scanner sc) {
        char choice;
        do {
            addContact(addressBook, sc);
            System.out.print("Add another contact? (y/n): ");
            choice = sc.next().charAt(0);
            sc.nextLine();
        } while (choice == 'y' || choice == 'Y');
    }

    // UC8: Search by City
    public static void searchByCity(ArrayList<Contact> addressBook, Scanner sc) {
        System.out.print("Enter city to search: ");
        String city = sc.nextLine();

        addressBook.stream()
                .filter(c -> c.getCity().equalsIgnoreCase(city))
                .forEach(System.out::println);
    }

    // UC8: Search by State
    public static void searchByState(ArrayList<Contact> addressBook, Scanner sc) {
        System.out.print("Enter state to search: ");
        String state = sc.nextLine();

        addressBook.stream()
                .filter(c -> c.getState().equalsIgnoreCase(state))
                .forEach(System.out::println);
    }

    // UC9: View persons grouped by city
    public static void viewPersonsByCity(List<Contact> contacts) {
        Map<String, List<Contact>> cityMap =
                contacts.stream().collect(Collectors.groupingBy(Contact::getCity));

        cityMap.forEach((city, persons) -> {
            System.out.println("\nCity: " + city);
            persons.forEach(System.out::println);
        });
    }

    // UC9: View persons grouped by state
    public static void viewPersonsByState(List<Contact> contacts) {
        Map<String, List<Contact>> stateMap =
                contacts.stream().collect(Collectors.groupingBy(Contact::getState));

        stateMap.forEach((state, persons) -> {
            System.out.println("\nState: " + state);
            persons.forEach(System.out::println);
        });
    }

    // UC10: Count by City
    public static void countByCity(List<Contact> contacts) {
        Map<String, Long> cityCount =
                contacts.stream().collect(Collectors.groupingBy(Contact::getCity, Collectors.counting()));

        cityCount.forEach((city, count) ->
                System.out.println(city + " -> " + count));
    }

    // UC10: Count by State
    public static void countByState(List<Contact> contacts) {
        Map<String, Long> stateCount =
                contacts.stream().collect(Collectors.groupingBy(Contact::getState, Collectors.counting()));

        stateCount.forEach((state, count) ->
                System.out.println(state + " -> " + count));
    }

    
    public static void sortByName(List<Contact> contacts) {

        List<Contact> sortedList = contacts.stream()
                .sorted(Comparator.comparing(Contact::getFirstName))
                .toList();

        System.out.println("\nContacts sorted alphabetically:");

        sortedList.forEach(System.out::println);
    }
    
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Contact> addressBook = new ArrayList<>();

        int choice;

        do {
            System.out.println("\n===== Address Book Menu =====");
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Add Multiple Contacts");
            System.out.println("5. Search by City");
            System.out.println("6. Search by State");
            System.out.println("7. View Persons by City");
            System.out.println("8. View Persons by State");
            System.out.println("9. Count Contacts by City");
            System.out.println("10. Count Contacts by State");
            System.out.println("11. Sort Contacts by Name");
            System.out.println("12. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addContact(addressBook, sc);
                case 2 -> editContact(addressBook, sc);
                case 3 -> deleteContact(addressBook, sc);
                case 4 -> addMultipleContacts(addressBook, sc);
                case 5 -> searchByCity(addressBook, sc);
                case 6 -> searchByState(addressBook, sc);
                case 7 -> viewPersonsByCity(addressBook);
                case 8 -> viewPersonsByState(addressBook);
                case 9 -> countByCity(addressBook);
                case 10 -> countByState(addressBook);
                case 11 -> sortByName(addressBook); 
                case 12 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice");
            }

        } while (choice != 11);

        sc.close();
    }
}