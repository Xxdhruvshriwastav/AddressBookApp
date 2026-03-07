package com.bridgelabz;
import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressBookAppApplication {
	
	
	public static Contact addContact() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter details:- ");
		System.out.print("First name: ");
		String firstName = sc.nextLine();
		System.out.print("Last name: ");
		String lastName = sc.nextLine();
		System.out.print("Address: ");
		String address = sc.nextLine();
		System.out.print("City: ");
		String city = sc.nextLine();
		System.out.print("State: ");
		String state = sc.nextLine();
		System.out.print("Zip code: ");
		int zip = sc.nextInt();
		System.out.println("Phone number: ");
		int phoneNumber = sc.nextInt();
		sc.nextLine();
		System.out.println("Email: ");
		String email = sc.nextLine();
		
		Contact c = new Contact(firstName, lastName, address, city, state, city, state, email);
		return c;
		
	}
	
	
	// edit the Address book
	
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
		
		
		ArrayList<Contact> addressBook = new ArrayList<>();
		
		//adding
		 addressBook.add(addContact());
		
		 // editing
		 editContact(addressBook);
		
	}

}
