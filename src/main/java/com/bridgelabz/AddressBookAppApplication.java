package com.bridgelabz;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookAppApplication {

    // UC1 Create Contact
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

        return new Contact(firstName,lastName,address,city,state,zip,phoneNumber,email);
    }

    // UC2 Add Contact
    public static void addContact(ArrayList<Contact> addressBook, Scanner sc) {

        Contact contact = createContact(sc);

        boolean exists = addressBook.stream()
                .anyMatch(c -> c.getFirstName().equalsIgnoreCase(contact.getFirstName()));

        if (exists) {
            System.out.println("Contact already exists!");
        } else {
            addressBook.add(contact);
            System.out.println("Contact Added Successfully");
        }
    }

    // UC3 Edit Contact
    public static void editContact(ArrayList<Contact> addressBook, Scanner sc) {

        System.out.print("Enter First Name to Edit: ");
        String name = sc.nextLine();

        for(Contact c : addressBook){

            if(c.getFirstName().equalsIgnoreCase(name)){

                System.out.print("New Address: ");
                c.setAddress(sc.nextLine());

                System.out.print("New City: ");
                c.setCity(sc.nextLine());

                System.out.print("New State: ");
                c.setState(sc.nextLine());

                System.out.print("New Zip: ");
                c.setZip(sc.nextLine());

                System.out.print("New Phone: ");
                c.setPhoneNumber(sc.nextLine());

                System.out.print("New Email: ");
                c.setEmail(sc.nextLine());

                System.out.println("Contact Updated");
                return;
            }
        }

        System.out.println("Contact Not Found");
    }

    // UC4 Delete Contact
    public static void deleteContact(ArrayList<Contact> addressBook, Scanner sc){

        System.out.print("Enter First Name to Delete: ");
        String name = sc.nextLine();

        Iterator<Contact> iterator = addressBook.iterator();

        while(iterator.hasNext()){

            Contact c = iterator.next();

            if(c.getFirstName().equalsIgnoreCase(name)){
                iterator.remove();
                System.out.println("Contact Deleted");
                return;
            }
        }

        System.out.println("Contact Not Found");
    }

    // UC5 Add Multiple Contacts
    public static void addMultipleContacts(ArrayList<Contact> addressBook, Scanner sc){

        char choice;

        do{
            addContact(addressBook,sc);

            System.out.print("Add another contact? (y/n): ");
            choice = sc.next().charAt(0);
            sc.nextLine();

        }while(choice=='y'||choice=='Y');
    }

    // UC8 Search by City
    public static void searchByCity(ArrayList<Contact> addressBook,Scanner sc){

        System.out.print("Enter City: ");
        String city = sc.nextLine();

        addressBook.stream()
                .filter(c -> c.getCity().equalsIgnoreCase(city))
                .forEach(System.out::println);
    }

    // UC8 Search by State
    public static void searchByState(ArrayList<Contact> addressBook,Scanner sc){

        System.out.print("Enter State: ");
        String state = sc.nextLine();

        addressBook.stream()
                .filter(c -> c.getState().equalsIgnoreCase(state))
                .forEach(System.out::println);
    }

    // UC9 View Persons By City
    public static void viewPersonsByCity(List<Contact> contacts){

        Map<String,List<Contact>> cityMap =
                contacts.stream().collect(Collectors.groupingBy(Contact::getCity));

        cityMap.forEach((city,persons)->{
            System.out.println("\nCity: "+city);
            persons.forEach(System.out::println);
        });
    }

    // UC9 View Persons By State
    public static void viewPersonsByState(List<Contact> contacts){

        Map<String,List<Contact>> stateMap =
                contacts.stream().collect(Collectors.groupingBy(Contact::getState));

        stateMap.forEach((state,persons)->{
            System.out.println("\nState: "+state);
            persons.forEach(System.out::println);
        });
    }

    // UC10 Count By City
    public static void countByCity(List<Contact> contacts){

        Map<String,Long> cityCount =
                contacts.stream().collect(Collectors.groupingBy(Contact::getCity,Collectors.counting()));

        cityCount.forEach((city,count)-> System.out.println(city+" -> "+count));
    }

    // UC10 Count By State
    public static void countByState(List<Contact> contacts){

        Map<String,Long> stateCount =
                contacts.stream().collect(Collectors.groupingBy(Contact::getState,Collectors.counting()));

        stateCount.forEach((state,count)-> System.out.println(state+" -> "+count));
    }

    // UC11 Sort By Name
    public static void sortByName(List<Contact> contacts){

        contacts.stream()
                .sorted(Comparator.comparing(Contact::getFirstName))
                .forEach(System.out::println);
    }

    // UC12 Sort By City
    public static void sortByCity(List<Contact> contacts){

        contacts.stream()
                .sorted(Comparator.comparing(Contact::getCity))
                .forEach(System.out::println);
    }

    // UC12 Sort By State
    public static void sortByState(List<Contact> contacts){

        contacts.stream()
                .sorted(Comparator.comparing(Contact::getState))
                .forEach(System.out::println);
    }

    // UC12 Sort By Zip
    public static void sortByZip(List<Contact> contacts){

        contacts.stream()
                .sorted(Comparator.comparing(Contact::getZip))
                .forEach(System.out::println);
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Contact> addressBook = new ArrayList<>();

        int choice;

        do{

            System.out.println("\n===== Address Book Menu =====");

            System.out.println("1 Add Contact");
            System.out.println("2 Edit Contact");
            System.out.println("3 Delete Contact");
            System.out.println("4 Add Multiple Contacts");
            System.out.println("5 Search by City");
            System.out.println("6 Search by State");
            System.out.println("7 View Persons by City");
            System.out.println("8 View Persons by State");
            System.out.println("9 Count by City");
            System.out.println("10 Count by State");
            System.out.println("11 Sort by Name");
            System.out.println("12 Sort by City");
            System.out.println("13 Sort by State");
            System.out.println("14 Sort by Zip");
            System.out.println("15 Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1 -> addContact(addressBook,sc);
                case 2 -> editContact(addressBook,sc);
                case 3 -> deleteContact(addressBook,sc);
                case 4 -> addMultipleContacts(addressBook,sc);
                case 5 -> searchByCity(addressBook,sc);
                case 6 -> searchByState(addressBook,sc);
                case 7 -> viewPersonsByCity(addressBook);
                case 8 -> viewPersonsByState(addressBook);
                case 9 -> countByCity(addressBook);
                case 10 -> countByState(addressBook);
                case 11 -> sortByName(addressBook);
                case 12 -> sortByCity(addressBook);
                case 13 -> sortByState(addressBook);
                case 14 -> sortByZip(addressBook);

                case 15 -> System.out.println("Exiting Program");

                default -> System.out.println("Invalid Choice");
            }

        }while(choice!=15);

        sc.close();
    }
}