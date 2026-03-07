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
	
	

	public static void main(String[] args) {
		
		
		ArrayList<Contact> addressBook = new ArrayList<>();
		
		 addressBook.add(addContact());
		
		
		
		
	}

}
