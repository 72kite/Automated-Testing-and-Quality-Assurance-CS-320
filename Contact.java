/*
 * Author: Zion D. Kinniebrew
 * Date: November 14, 2025
 * Course: CS-320
 * Description:
 * This file defines the Contact object. It enforces all business
 * rules (not null, length, and format) by throwing an
 * IllegalArgumentException if any validation fails.
 */

package contact;

public class Contact {

    private final String contactID; // Final, not updatable
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    /**
     * Constructor for the Contact object.
     * All parameters are validated upon creation.
     *
     * @param contactID A unique ID, 10 chars or less, not null.
     * @param firstName First name, 10 chars or less, not null.
     * @param lastName  Last name, 10 chars or less, not null.
     * @param phone     Phone number, exactly 10 digits, not null.
     * @param address   Address, 30 chars or less, not null.
     * @throws IllegalArgumentException if any validation fails.
     */
    public Contact(String contactID, String firstName, String lastName, String phone, String address) {
        // Validate and assign contactID
        if (contactID == null || contactID.length() > 10) {
            throw new IllegalArgumentException("Invalid Contact ID");
        }
        this.contactID = contactID;

        // Validate and assign other fields using setters
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
    }

    // --- Getters ---

    public String getContactID() {
        return contactID;
    }

    public String getFirstName() {
        return firstName;
    }

    /**
     * Accessing the contact's last name.
     */
    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // --- Setters with Validation ---

    /**
     * Sets the first name.
     * @param firstName 10 characters or less, not null.
     * @throws IllegalArgumentException if validation fails.
     */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("Invalid First Name");
        }
        this.firstName = firstName;
    }

    /**
     * Sets the last name.
     * @param lastName 10 characters or less, not null.
     * @throws IllegalArgumentException if validation fails.
     */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Invalid Last Name");
        }
        this.lastName = lastName;
    }

    /**
     * Sets the phone number.
     * @param phone Exactly 10 digits, not null.
     * @throws IllegalArgumentException if validation fails.
     */
    public void setPhone(String phone) {
        // Uses regex to ensure exactly 10 digits
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid Phone Number");
        }
        this.phone = phone;
    }

    /**
     * Sets the address.
     * @param address 30 characters or less, not null.
     * @throws IllegalArgumentException if validation fails.
     */
    public void setAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Invalid Address");
        }
        this.address = address;
    }
}