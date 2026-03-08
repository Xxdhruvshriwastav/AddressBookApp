package com.bridgelabz;

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import com.google.gson.Gson;

public class AddressBookAppApplication {

    public static Contact createContact(Scanner sc){

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
        String phone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        return new Contact(firstName,lastName,address,city,state,zip,phone,email);
    }

    public static void addContact(ArrayList<Contact> addressBook,Scanner sc){

        Contact contact = createContact(sc);

        boolean exists = addressBook.stream()
                .anyMatch(c->c.getFirstName().equalsIgnoreCase(contact.getFirstName()));

        if(exists)
            System.out.println("Contact already exists");
        else{
            addressBook.add(contact);
            System.out.println("Contact Added Successfully");
        }
    }

    public static void editContact(ArrayList<Contact> addressBook,Scanner sc){

        System.out.print("Enter First Name to Edit: ");
        String name = sc.nextLine();

        AddressBookDBService dbService = AddressBookDBService.getInstance();

        for(Contact c : addressBook){

            if(c.getFirstName().equalsIgnoreCase(name)){

                System.out.print("New City: ");
                String city = sc.nextLine();

                System.out.print("New State: ");
                String state = sc.nextLine();

                c.setCity(city);
                c.setState(state);

                dbService.updateContact(name,city,state);

                System.out.println("Contact Updated");
                return;
            }
        }

        System.out.println("Contact Not Found");
    }

    public static void deleteContact(ArrayList<Contact> addressBook,Scanner sc){

        System.out.print("Enter First Name to Delete: ");
        String name = sc.nextLine();

        addressBook.removeIf(c->c.getFirstName().equalsIgnoreCase(name));

        System.out.println("Contact Deleted");
    }

    public static void addMultipleContacts(ArrayList<Contact> addressBook,Scanner sc){

        char choice;

        do{

            addContact(addressBook,sc);

            System.out.print("Add another contact? (y/n): ");
            choice = sc.next().charAt(0);
            sc.nextLine();

        }while(choice=='y'||choice=='Y');
    }

    public static void searchByCity(ArrayList<Contact> addressBook,Scanner sc){

        System.out.print("Enter City: ");
        String city = sc.nextLine();

        addressBook.stream()
                .filter(c->c.getCity().equalsIgnoreCase(city))
                .forEach(System.out::println);
    }

    public static void searchByState(ArrayList<Contact> addressBook,Scanner sc){

        System.out.print("Enter State: ");
        String state = sc.nextLine();

        addressBook.stream()
                .filter(c->c.getState().equalsIgnoreCase(state))
                .forEach(System.out::println);
    }

    public static void viewPersonsByCity(List<Contact> contacts){

        Map<String,List<Contact>> cityMap =
                contacts.stream().collect(Collectors.groupingBy(Contact::getCity));

        cityMap.forEach((city,persons)->{

            System.out.println("\nCity: "+city);
            persons.forEach(System.out::println);

        });
    }

    public static void countByCity(List<Contact> contacts){

        Map<String,Long> cityCount =
                contacts.stream().collect(Collectors.groupingBy(Contact::getCity,Collectors.counting()));

        cityCount.forEach((city,count)->System.out.println(city+" -> "+count));
    }

    public static void sortByName(List<Contact> contacts){

        contacts.stream()
                .sorted(Comparator.comparing(Contact::getFirstName))
                .forEach(System.out::println);
    }

    // UC13 TXT
    public static void writeToFile(List<Contact> contacts){

        try{

            FileWriter writer = new FileWriter("addressbook.txt");

            for(Contact c:contacts){
                writer.write(c.toString());
                writer.write("\n");
            }

            writer.close();

            System.out.println("Contacts written to TXT");

        }catch(Exception e){
            System.out.println("Error writing TXT");
        }
    }

    // UC14 CSV
    public static void writeToCSV(List<Contact> contacts){

        try{

            CSVWriter writer = new CSVWriter(new FileWriter("addressbook.csv"));

            String[] header = {"FirstName","LastName","Address","City","State","Zip","Phone","Email"};

            writer.writeNext(header);

            for(Contact c:contacts){

                String[] data={
                        c.getFirstName(),
                        c.getLastName(),
                        c.getAddress(),
                        c.getCity(),
                        c.getState(),
                        c.getZip(),
                        c.getPhoneNumber(),
                        c.getEmail()
                };

                writer.writeNext(data);
            }

            writer.close();

            System.out.println("Contacts written to CSV");

        }catch(Exception e){
            System.out.println("Error writing CSV");
        }
    }

    public static void readFromCSV(){

        try{

            CSVReader reader = new CSVReader(new FileReader("addressbook.csv"));

            String[] line;

            while((line=reader.readNext())!=null){
                System.out.println(Arrays.toString(line));
            }

            reader.close();

        }catch(Exception e){
            System.out.println("Error reading CSV");
        }
    }

    // UC15 JSON
    public static void writeToJSON(List<Contact> contacts){

        try{

            Gson gson = new Gson();

            FileWriter writer = new FileWriter("addressbook.json");

            gson.toJson(contacts,writer);

            writer.close();

            System.out.println("Contacts written to JSON");

        }catch(Exception e){
            System.out.println("Error writing JSON");
        }
    }

    public static void readFromJSON(){

        try{

            Gson gson = new Gson();

            FileReader reader = new FileReader("addressbook.json");

            Contact[] contacts = gson.fromJson(reader,Contact[].class);

            for(Contact c:contacts)
                System.out.println(c);

            reader.close();

        }catch(Exception e){
            System.out.println("Error reading JSON");
        }
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        AddressBookDBService dbService = AddressBookDBService.getInstance();

        ArrayList<Contact> addressBook = new ArrayList<>();

        addressBook.addAll(dbService.readContacts());

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
            System.out.println("8 Count by City");
            System.out.println("9 Sort by Name");
            System.out.println("10 Write TXT File");
            System.out.println("11 Write CSV File");
            System.out.println("12 Read CSV File");
            System.out.println("13 Write JSON File");
            System.out.println("14 Read JSON File");
            System.out.println("15 Exit");

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
                case 8 -> countByCity(addressBook);
                case 9 -> sortByName(addressBook);
                case 10 -> writeToFile(addressBook);
                case 11 -> writeToCSV(addressBook);
                case 12 -> readFromCSV();
                case 13 -> writeToJSON(addressBook);
                case 14 -> readFromJSON();
                case 15 -> System.out.println("Exiting");

                default -> System.out.println("Invalid Choice");
            }

        }while(choice!=15);

        sc.close();
    }
}