/*
 * Author: Zion D. Kinnierbrew
 * Date: November 14, 2025
 * Course:  CS-320
 * Description:
 * This file contains the JUnit 5 test class for the
 * ContactService. It verifies that contacts can be added
 * (with unique IDs), deleted, and updated successfully, and
 * that the service correctly handles errors.
 */

package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import contact.Contact;
import contact.ContactService;

/**
 * Unit tests for the ContactService class.
 * Tests CRUD operations: add, delete, and update contacts.
 */
class ContactServiceTest {

    private ContactService service;
    private Contact contact1;
    private Contact contact2;

    @BeforeEach
    void setUp() {
        service = new ContactService();
        contact1 = new Contact("ID1", "Alice", "Smith", "1112223333", "1 Apple Rd");
        contact2 = new Contact("ID2", "Bob", "Johnson", "4445556666", "2 Banana Ct");
    }

    // --- Add Contact Tests ---
    @Test
    void testAddContactSuccess() {
        service.addContact(contact1);
        assertNotNull(service.getContact("ID1"));
        assertEquals("Alice", service.getContact("ID1").getFirstName());
    }

    @Test
    void testAddMultipleContacts() {
        service.addContact(contact1);
        service.addContact(contact2);

        assertAll("Multiple contacts added",
            () -> assertNotNull(service.getContact("ID1")),
            () -> assertNotNull(service.getContact("ID2")),
            () -> assertEquals("Alice", service.getContact("ID1").getFirstName()),
            () -> assertEquals("Bob", service.getContact("ID2").getFirstName())
        );
    }

    @Test
    void testAddDuplicateContact() {
        service.addContact(contact1);
        // Create a new contact object with the same ID
        Contact duplicateContact = new Contact("ID1", "Charlie", "Brown", "7778889999", "3 Cherry Ln");

        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(duplicateContact);
        });

        // Verify original contact is still there
        assertEquals("Alice", service.getContact("ID1").getFirstName());
    }

    @Test
    void testAddNullContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(null);
        });
    }

    // --- Delete Contact Tests ---
    @Test
    void testDeleteContactSuccess() {
        service.addContact(contact1);
        assertNotNull(service.getContact("ID1"));

        service.deleteContact("ID1");
        assertNull(service.getContact("ID1"));
    }

    @Test
    void testDeleteContactNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact("NONEXISTENT_ID");
        });
    }

    @Test
    void testDeleteOneOfMultipleContacts() {
        service.addContact(contact1);
        service.addContact(contact2);

        service.deleteContact("ID1");

        assertAll("Delete verification",
            () -> assertNull(service.getContact("ID1")),
            () -> assertNotNull(service.getContact("ID2"))
        );
    }

    // --- Update Contact Tests ---
    @Test
    void testUpdateContactSuccess() {
        service.addContact(contact1);

        service.updateContact("ID1", "Alicia", "Jones", "1231231234", "1 NewAddress Way");

        Contact updatedContact = service.getContact("ID1");
        assertAll("Update verification",
            () -> assertEquals("Alicia", updatedContact.getFirstName()),
            () -> assertEquals("Jones", updatedContact.getLastName()),
            () -> assertEquals("1231231234", updatedContact.getPhone()),
            () -> assertEquals("1 NewAddress Way", updatedContact.getAddress()),
            () -> assertEquals("ID1", updatedContact.getContactID()) // ID should remain unchanged
        );
    }

    @Test
    void testUpdateContactNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("NONEXISTENT_ID", "New", "Name", "1234567890", "New Address");
        });
    }

    @Test
    void testUpdateContactInvalidData() {
        service.addContact(contact1);

        // Test that validation (from Contact class) is triggered during an update
        assertThrows(IllegalArgumentException.class, () -> {
            // First name is too long
            service.updateContact("ID1", "AliciaVeronica", "Jones", "1231231234", "1 NewAddress Way");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            // Phone is invalid (too short)
            service.updateContact("ID1", "Alicia", "Jones", "123", "1 NewAddress Way");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            // Last name is null
            service.updateContact("ID1", "Alicia", null, "1231231234", "1 NewAddress Way");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            // Address is too long
            service.updateContact("ID1", "Alicia", "Jones", "1231231234", "This is a very long address that exceeds thirty characters");
        });

        // Verify original contact data remains unchanged after failed updates
        Contact original = service.getContact("ID1");
        assertAll("Original data preserved",
            () -> assertEquals("Alice", original.getFirstName()),
            () -> assertEquals("Smith", original.getLastName()),
            () -> assertEquals("1112223333", original.getPhone()),
            () -> assertEquals("1 Apple Rd", original.getAddress())
        );
    }

    @Test
    void testUpdateContactPartialFields() {
        service.addContact(contact1);

        // Update only some fields
        service.updateContact("ID1", "Alicia", "Smith", "1112223333", "1 Apple Rd");

        Contact updatedContact = service.getContact("ID1");
        assertAll("Partial update verification",
            () -> assertEquals("Alicia", updatedContact.getFirstName()),
            () -> assertEquals("Smith", updatedContact.getLastName()),
            () -> assertEquals("1112223333", updatedContact.getPhone()),
            () -> assertEquals("1 Apple Rd", updatedContact.getAddress())
        );
    }

    // --- Get Contact Tests ---
    @Test
    void testGetContactNotFound() {
        assertNull(service.getContact("NONEXISTENT_ID"));
    }

    @Test
    void testGetContactAfterAdd() {
        service.addContact(contact1);
        Contact retrieved = service.getContact("ID1");

        assertAll("Retrieved contact verification",
            () -> assertNotNull(retrieved),
            () -> assertEquals(contact1.getContactID(), retrieved.getContactID()),
            () -> assertEquals(contact1.getFirstName(), retrieved.getFirstName()),
            () -> assertEquals(contact1.getLastName(), retrieved.getLastName()),
            () -> assertEquals(contact1.getPhone(), retrieved.getPhone()),
            () -> assertEquals(contact1.getAddress(), retrieved.getAddress())
        );
    }
}