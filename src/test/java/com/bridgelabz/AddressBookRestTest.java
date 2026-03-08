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

    ArrayList<Contact> addressBookList;

    @BeforeEach
    public void setUp(){

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;

        addressBookList = new ArrayList<>();
    }

    @Test
    public void givenAddressBookData_whenRetrieved(){

        Response response = given()
                .when()
                .get("/contacts")
                .then()
                .statusCode(200)
                .extract()
                .response();

        Contact[] contacts = response.as(Contact[].class);

        addressBookList.addAll(Arrays.asList(contacts));

        for(Contact contact : addressBookList){
            System.out.println(contact.getFirstName() + " " + contact.getCity());
        }

        System.out.println("Total Contacts : " + addressBookList.size());

        assertTrue(addressBookList.size() > 0);
    }
}