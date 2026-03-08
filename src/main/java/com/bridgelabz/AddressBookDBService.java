package com.bridgelabz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {

    private static AddressBookDBService instance;

    private AddressBookDBService(){}

    public static AddressBookDBService getInstance(){

        if(instance == null){
            instance = new AddressBookDBService();
        }
        return instance;
    }

    private Connection getConnection(){

        Connection connection = null;

        try{

            String url = "jdbc:mysql://localhost:3306/addressbook";
            String username = "root";
            String password = "Nty99hd8r@";

            connection = DriverManager.getConnection(url,username,password);

            System.out.println("Database connection successful");

        }catch(Exception e){
            e.printStackTrace();
        }

        return connection;
    }

    // UC16 Read contacts
    public List<Contact> readContacts(){

        List<Contact> contactList = new ArrayList<>();

        String query = "SELECT * FROM contact";

        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ){

            while(resultSet.next()){

                Contact contact = new Contact(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("zip"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );

                contactList.add(contact);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return contactList;
    }

    // UC17 Update contact
    public boolean updateContact(String firstName,String city,String state){

        boolean updated = false;

        String query = "UPDATE contact SET city=?, state=? WHERE first_name=?";

        try(
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ){

            ps.setString(1,city);
            ps.setString(2,state);
            ps.setString(3,firstName);

            int result = ps.executeUpdate();

            if(result>0){
                updated = true;
                System.out.println("Contact updated in DB");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return updated;
    }

    // UC18 Retrieve by date range
    public List<Contact> getContactsByDateRange(String startDate,String endDate){

        List<Contact> contacts = new ArrayList<>();

        String query = "SELECT * FROM contact WHERE date_added BETWEEN ? AND ?";

        try(
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ){

            ps.setString(1,startDate);
            ps.setString(2,endDate);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Contact contact = new Contact(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip"),
                        rs.getString("phone"),
                        rs.getString("email")
                );

                contacts.add(contact);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return contacts;
    }

    // UC19 Count by city
    public void countContactsByCity(){

        String query = "SELECT city, COUNT(*) AS total FROM contact GROUP BY city";

        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query)
        ){

            while(rs.next()){

                String city = rs.getString("city");
                int count = rs.getInt("total");

                System.out.println(city + " -> " + count);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // UC19 Count by state
    public void countContactsByState(){

        String query = "SELECT state, COUNT(*) AS total FROM contact GROUP BY state";

        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query)
        ){

            while(rs.next()){

                String state = rs.getString("state");
                int count = rs.getInt("total");

                System.out.println(state + " -> " + count);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // UC20 Add contact with transaction
    public boolean addContactToDB(Contact contact){

        boolean added = false;

        String query = "INSERT INTO contact " +
                "(first_name,last_name,address,city,state,zip,phone,email,date_added) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";

        Connection connection = null;

        try{

            connection = getConnection();

            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1,contact.getFirstName());
            ps.setString(2,contact.getLastName());
            ps.setString(3,contact.getAddress());
            ps.setString(4,contact.getCity());
            ps.setString(5,contact.getState());
            ps.setString(6,contact.getZip());
            ps.setString(7,contact.getPhoneNumber());
            ps.setString(8,contact.getEmail());
            ps.setDate(9,new java.sql.Date(System.currentTimeMillis()));

            int result = ps.executeUpdate();

            if(result>0){

                connection.commit();
                added = true;

                System.out.println("Contact added successfully in DB");
            }

        }catch(Exception e){

            try{
                if(connection != null)
                    connection.rollback();
            }catch(SQLException ex){
                ex.printStackTrace();
            }

            e.printStackTrace();
        }

        return added;
    }
}