package com.bridgelabz;

public class AddContactThread extends Thread {

    private Contact contact;

    public AddContactThread(Contact contact){
        this.contact = contact;
    }

    @Override
    public void run(){

        AddressBookDBService dbService = AddressBookDBService.getInstance();

        dbService.addContactToDB(contact);

        System.out.println("Added by Thread : " + contact.getFirstName());
    }
}