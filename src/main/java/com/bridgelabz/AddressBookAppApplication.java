package com.bridgelabz;
import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressBookAppApplication {
	
	
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

	    System.out.println("Contact Added Successfully!");

	    return contact;
	}
	
	

	public static void main(String[] args) {
		
		
		ArrayList<Contact> addressBook = new ArrayList<>();
		
		 addressBook.add(addContact());
		
		
		
		
	}

}
