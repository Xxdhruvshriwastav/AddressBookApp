package com.bridgelabz;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class AddressBookRestTest {

    ArrayList<ContactDTO> addressBookList;

    @BeforeEach
    public void setUp(){

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;

        addressBookList = new ArrayList<>();
    }

    // UC22 : Retrieve Contacts
    @Test
    public void givenAddressBookData_whenRetrieved(){

        Response response = given()
                .when()
                .get("/contacts")
                .then()
                .statusCode(200)
                .extract()
                .response();

        ContactDTO[] contacts = response.as(ContactDTO[].class);

        addressBookList.addAll(Arrays.asList(contacts));

        System.out.println("Total Contacts : " + addressBookList.size());

        assertTrue(addressBookList.size() >= 0);
    }

    // UC23 : Add Multiple Contacts
    @Test
    public void givenMultipleContacts_whenAdded_shouldSyncWithMemory(){

        ContactDTO[] newContacts = {
                new ContactDTO(0,"Amit","Delhi"),
                new ContactDTO(0,"Rohit","Patna"),
                new ContactDTO(0,"Priya","Kolkata")
        };

        for(ContactDTO contact : newContacts){

            Response response = given()
                    .contentType("application/json")
                    .body(contact)
                    .when()
                    .post("/contacts")
                    .then()
                    .statusCode(201)
                    .extract()
                    .response();

            ContactDTO addedContact = response.as(ContactDTO.class);

            addressBookList.add(addedContact);

            System.out.println("Added Contact : " + addedContact.getName());
        }

        System.out.println("Total Contacts in Memory : " + addressBookList.size());

        assertEquals(3,addressBookList.size());
    }

    // UC24 : Update Contact
    @Test
    public void givenNewCity_whenUpdated_shouldSyncWithMemory(){

        // Step 1: Retrieve contacts
        Response response = given()
                .when()
                .get("/contacts")
                .then()
                .statusCode(200)
                .extract()
                .response();

        ContactDTO[] contacts = response.as(ContactDTO[].class);

        addressBookList.addAll(Arrays.asList(contacts));

        // Step 2: Pick first contact
        ContactDTO contact = addressBookList.get(0);
        contact.setCity("Mumbai");

        // Step 3: Update using PUT
        Response updateResponse = given()
                .contentType("application/json")
                .body(contact)
                .when()
                .put("/contacts/" + contact.getId())
                .then()
                .statusCode(200)
                .extract()
                .response();

        ContactDTO updatedContact = updateResponse.as(ContactDTO.class);

        // Step 4: Sync memory
        for(int i=0;i<addressBookList.size();i++){
            if(addressBookList.get(i).getId() == updatedContact.getId()){
                addressBookList.set(i, updatedContact);
            }
        }

        boolean result = addressBookList.stream()
                .anyMatch(c -> c.getCity().equals("Mumbai"));

        System.out.println("Updated Contact City: " + updatedContact.getCity());

        assertTrue(result);
    }
}