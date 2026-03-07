package com.bridgelabz;

import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressBookAppApplication {

    // UC1 : Add Contact
    public static Contact addContact() {

        Scanner sc = new Scanner(System.in);

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

        Contact contact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);

        return contact;
    }

    // UC2 : Edit Contact
    public static void editContact(ArrayList<Contact> addressBook) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first name of contact to edit: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Contact contact : addressBook) {

            if (contact.getFirstName().equalsIgnoreCase(name)) {

                found = true;

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
                System.out.println(contact);

                break;
            }
        }

        if (!found) {
            System.out.println("Contact not found!");
        }
    }

    
    public static void main(String[] args) {

        SpringApplication.run(AddressBookAppApplication.class, args);

        System.out.println("Welcome to Address Book Program");

        ArrayList<Contact> addressBook = new ArrayList<>();

        // UC1
        addressBook.add(addContact());

        // UC2
        editContact(addressBook);

        
    }
}