/*
 * Author: Zion D. Kinniebrew
 * Date: November 14, 2025
 * Course:  CS-320
 * Description:
 * This file contains the ContactService class. It provides
 * methods to add, delete, and update Contact objects using
 * an in-memory HashMap to manage the collection.
 */

package contact;

import java.util.HashMap;
import java.util.Map;

/**
 * ContactService provides CRUD operations for managing contacts.
 * Uses in-memory storage with HashMap.
 */
public class ContactService {

    // In-memory storage using a HashMap
    private final Map<String, Contact> contacts;

    /**
     * Constructor initializes the contact service.
     */
    public ContactService() {
        this.contacts = new HashMap<>();
    }

    /**
     * Adds a new contact to the service.
     * The contact ID must be unique.
     *
     * @param contact The contact object to add.
     * @throws IllegalArgumentException if the contact ID already exists.
     */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }
        if (contacts.containsKey(contact.getContactID())) {
            throw new IllegalArgumentException("Contact ID already exists");
        }
        contacts.put(contact.getContactID(), contact);
    }

    /**
     * Deletes a contact from the service per contact ID.
     *
     * @param contactID The ID of the contact to delete.
     * @throws IllegalArgumentException if the contact ID is not found.
     */
    public void deleteContact(String contactID) {
        if (!contacts.containsKey(contactID)) {
            throw new IllegalArgumentException("Contact not found");
        }
        contacts.remove(contactID);
    }

    /**
     * Updates an existing contact's fields.
     * Implements transactional validation to ensure no partial updates occur.
     *
     * @param contactID The ID of the contact to update.
     * @param firstName The new first name.
     * @param lastName  The new last name.
     * @param phone     The new phone number.
     * @param address   The new address.
     * @throws IllegalArgumentException if the contact ID is not found or validation fails.
     */
    public void updateContact(String contactID, String firstName, String lastName, String phone, String address) {
        Contact contact = contacts.get(contactID);

        if (contact == null) {
            throw new IllegalArgumentException("Contact not found");
        }

        /*
         * FIX: Transactional Validation.
         * We create a DUMMY object using the existing contact's guaranteed valid data.
         * We then test the new, potentially invalid, parameters on the DUMMY.
         * If any validation fails, an exception is thrown, and the real contact
         * object remains completely untouched, preventing partial updates.
         */

        // 1. Create a dummy object using the current (valid) data of the existing contact.
        // This relies on the Contact constructor to properly validate the existing (good) data.
        Contact dummy = new Contact(contactID, contact.getFirstName(), contact.getLastName(), contact.getPhone(), contact.getAddress());

        // 2. Test the new parameters on the DUMMY object. If any setter fails,
        // the method exits via exception before step 3 is reached.
        dummy.setFirstName(firstName);
        dummy.setLastName(lastName);
        dummy.setPhone(phone);
        dummy.setAddress(address);

        // 3. If validation passes for all parameters, apply the new values to the real contact.
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhone(phone);
        contact.setAddress(address);
    }

    /**
     * Retrieves a contact by ID.
     *
     * @param contactID The ID of the contact to retrieve.
     * @return The Contact object, or null if not found.
     */
    public Contact getContact(String contactID) {
        return contacts.get(contactID);
    }
}