# 📒 Address Book Application

A **Java-based Address Book Application** developed using **Core Java, File I/O, CSV, JSON, MySQL, Multithreading, and REST Assured API Testing**.

The project is implemented using **25 Use Cases (UC1 → UC25)** with a **feature-branch Git workflow**.

---

# 🚀 Technologies Used

* Java
* JUnit 5
* REST Assured
* MySQL
* OpenCSV
* Gson
* JSON Server
* Git & GitHub

---

# 📂 Project Modules

```
AddressBookApp
│
├── Contact.java
├── AddressBookAppApplication.java
├── AddressBookDBService.java
├── ContactDTO.java
├── AddContactThread.java
└── AddressBookRestTest.java
```

---

# 📌 Use Case Implementation

| UC   | Feature                         | Description                                              | Branch                                            | Link          |
| ---- | ------------------------------- | -------------------------------------------------------- | ------------------------------------------------- | ------------- |
| UC1  | Create Contact                  | Ability to create a contact object with required details | feature/UC1-create-contact                        | [View Code]() |
| UC2  | Add Contact                     | Ability to add contact to address book                   | feature/UC2-add-contact                           | [View Code]() |
| UC3  | Edit Contact                    | Ability to edit existing contact details                 | feature/UC3-edit-contact                          | [View Code]() |
| UC4  | Delete Contact                  | Ability to delete contact from address book              | feature/UC4-delete-contact                        | [View Code]() |
| UC5  | Add Multiple Contacts           | Ability to add multiple contacts                         | feature/UC5-add-multiple-contacts                 | [View Code]() |
| UC6  | Multiple Address Books          | Ability to manage multiple address books                 | feature/UC6-multiple-addressbooks                 | [View Code]() |
| UC7  | Prevent Duplicate Contact       | Prevent adding duplicate contacts                        | feature/UC7-prevent-duplicate-contact             | [View Code]() |
| UC8  | Search Person by City/State     | Search contacts using city or state                      | feature/UC8-search-person-by-city-or-state        | [View Code]() |
| UC9  | View Persons by City/State      | Display persons grouped by city/state                    | feature/UC9-view-persons-by-city-or-state         | [View Code]() |
| UC10 | Count Contacts by City/State    | Count number of contacts by city/state                   | feature/UC10-count-contacts-by-city-or-state      | [View Code]() |
| UC11 | Sort Contacts by Name           | Sort contacts alphabetically                             | feature/UC11-sort-contacts-by-name                | [View Code]() |
| UC12 | Sort Contacts by City/State/Zip | Sort contacts by location                                | feature/UC12-sort-contacts-by-city-state-zip      | [View Code]() |
| UC13 | File IO                         | Read and write address book using text file              | feature/UC13-read-write-addressbook-file-io       | [View Code]() |
| UC14 | CSV File                        | Read and write contacts using CSV                        | feature/UC14-read-write-addressbook-csv           | [View Code]() |
| UC15 | JSON File                       | Read and write contacts using JSON                       | feature/UC15-read-write-addressbook-json          | [View Code]() |
| UC16 | Retrieve Contacts from DB       | Fetch contacts from MySQL database                       | feature/UC16-retrieve-contacts-from-database      | [View Code]() |
| UC17 | Update Contact DB               | Update contact and sync with database                    | feature/UC17-update-contact-and-sync-with-db      | [View Code]() |
| UC18 | Retrieve Contacts by Date       | Retrieve contacts by date range                          | feature/UC18-retrieve-contacts-by-date-range      | [View Code]() |
| UC19 | Count Contacts DB               | Count contacts by city/state from database               | feature/UC19-count-contacts-by-city-state-db      | [View Code]() |
| UC20 | Add Contact to DB               | Insert contact into database                             | feature/UC20-add-contact-to-database              | [View Code]() |
| UC21 | Multithreading                  | Add multiple contacts using threads                      | feature/UC21-add-multiple-contacts-multithreading | [View Code]() |
| UC22 | Retrieve from JSON Server       | Fetch contacts using REST API                            | feature/UC22-read-entries-from-jsonserver         | [View Code]() |
| UC23 | Add to JSON Server              | Add multiple contacts using REST API                     | feature/UC23-add-multiple-entries-jsonserver      | [View Code]() |
| UC24 | Update JSON Server              | Update contact using REST API                            | feature/UC24-update-entry-jsonserver              | [View Code]() |
| UC25 | Delete JSON Server              | Delete contact using REST API                            | feature/UC25-delete-entry-jsonserver              | [View Code]() |

---

# 🗄 Database Setup

```
CREATE DATABASE addressbook;
```

```
CREATE TABLE contact(
first_name VARCHAR(50),
last_name VARCHAR(50),
address VARCHAR(100),
city VARCHAR(50),
state VARCHAR(50),
zip VARCHAR(10),
phone VARCHAR(15),
email VARCHAR(100),
date_added DATE
);
```

---

# 🌐 JSON Server Setup

Install JSON server:

```
npm install -g json-server
```

Create `db.json`

```
{
 "contacts": []
}
```

Run server:

```
json-server --watch db.json
```

Server URL:

```
http://localhost:3000/contacts
```

---

# 🧪 Running Tests

Run JUnit tests:

```
mvn test
```

Test class:

```
AddressBookRestTest.java
```

---

# 🌿 Git Branch Strategy

```
main
dev
feature/UC1-create-contact
feature/UC2-add-contact
...
feature/UC25-delete-entry-jsonserver
```

---

# 👨‍💻 Author

Ashish
Java Developer | Azure Fundamentals Certified
