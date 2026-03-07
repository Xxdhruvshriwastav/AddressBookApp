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

    // UC3 : Delete Contact
    public static void deleteContact(ArrayList<Contact> addressBook) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first name of contact to delete: ");
        String name = sc.nextLine();

        boolean found = false;

        Iterator<Contact> iterator = addressBook.iterator();

        while (iterator.hasNext()) {

            Contact contact = iterator.next();

            if (contact.getFirstName().equalsIgnoreCase(name)) {

                iterator.remove();
                found = true;

                System.out.println("Contact deleted successfully!");

                break;
            }
        }

        if (!found) {
            System.out.println("User not found");
        }
    }
    
    
    
    
    
    

    public static void main(String[] args) {
    	
    	

       Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Address Book Program");

        ArrayList<Contact> addressBook = new ArrayList<>();

        // UC2
        addressBook.add(addContact());

        // UC3
        editContact(addressBook);

        // UC4
       deleteContact(addressBook);
        
        
        // UC 5 
        
        
        char choice;
        
        do {

            Contact contact = addContact();   // UC1 method
            addressBook.add(contact);

            System.out.println("Contact Added Successfully!");

            System.out.print("Do you want to add another contact (y/n): ");
            choice = sc.next().charAt(0);
            sc.nextLine();

        } while (choice == 'y' || choice == 'Y');
        
        
        
        // UC-6
        
        char choise;
        
        do {
        	
        	boolean exist = false;
        	
        	for(Contact c :addressBook) {
        		
        		if(c.getFirstName().equalsIgnoreCase(c.getFirstName())) {
        			
        			exist = false;
        			break;
        			
        		}
        	}
        	
        	
        	if (exist) {
                System.out.println("Contact with this name already exists!");
            } else {
            	
            	Contact contact = addContact();   // UC1 method
                addressBook.add(contact);
                
                System.out.println("Contact Added Successfully!");
            }
        	
        	choise = sc.next().charAt(0);
        	
        	
        } while(choise == 'y' || choise == 'Y');
        
        
        
        
        
        
        // UC-7
        
        public void addPerson(ArrayList<Contact> addressBook, Contact person) {

            boolean duplicate = addressBook.stream()
                    .anyMatch(p -> p.getFirstName().equalsIgnoreCase(person.getFirstName())
                            && p.getLastName().equalsIgnoreCase(person.getLastName()));

            if (duplicate) {
                System.out.println("Duplicate entry! Person already exists.");
            } else {
                addressBook.add(person);
                System.out.println("Person added successfully.");
            }
       

            
    }
}