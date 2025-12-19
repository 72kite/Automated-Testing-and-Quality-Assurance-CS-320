/*
 * Author: Zion D. Kinniebrew
 * Date: November 14, 2025
 * Course:  CS-320
 * Description:
 * This file contains the JUnit 5 test class for the Contact
 * object. It verifies all business rules (null checks, length,
 * and format) and ensures that IllegalArgumentExceptions
 * are thrown correctly.
 */

package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import contact.Contact;

/**
 * Unit tests for the Contact class.
 * Tests validation rules for all fields and setter methods.
 */
class ContactTest {

    @Test
    void testContactCreationSuccess() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertAll("Contact creation",
            () -> assertEquals("12345", contact.getContactID()),
            () -> assertEquals("John", contact.getFirstName()),
            () -> assertEquals("Doe", contact.getLastName()),
            () -> assertEquals("1234567890", contact.getPhone()),
            () -> assertEquals("123 Main St", contact.getAddress())
        );
    }

    // --- Contact ID Tests ---
    @Test
    void testContactIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    void testContactIdNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "John", "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    void testContactIdMaxLength() {
        // Test that exactly 10 characters is valid
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("1234567890", contact.getContactID());
    }

    // --- First Name Tests ---
    @Test
    void testFirstNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "JohnathanDo", "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    void testFirstNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", null, "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    void testFirstNameMaxLength() {
        // Test that exactly 10 characters is valid
        Contact contact = new Contact("12345", "Johnathan1", "Doe", "1234567890", "123 Main St");
        assertEquals("Johnathan1", contact.getFirstName());
    }

    // --- Last Name Tests ---
    @Test
    void testLastNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "DoeSmithson", "1234567890", "123 Main St");
        });
    }

    @Test
    void testLastNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", null, "1234567890", "123 Main St");
        });
    }

    @Test
    void testLastNameMaxLength() {
        // Test that exactly 10 characters is valid
        Contact contact = new Contact("12345", "John", "DoeSmithso", "1234567890", "123 Main St");
        assertEquals("DoeSmithso", contact.getLastName());
    }

    // --- Phone Tests ---
    @Test
    void testPhoneNotTenDigits() {
        // Test too short
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "123456789", "123 Main St");
        });
        // Test too long
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "12345678901", "123 Main St");
        });
        // Test with letters
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "123456789A", "123 Main St");
        });
    }

    @Test
    void testPhoneNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", null, "123 Main St");
        });
    }

    @Test
    void testPhoneExactlyTenDigits() {
        // Test that exactly 10 digits is valid
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("1234567890", contact.getPhone());
    }

    @Test
    void testPhoneWithNonDigits() {
        // Test with special characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "123-456-789", "123 Main St");
        });
    }

    // --- Address Tests ---
    @Test
    void testAddressTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "1234567890", "123 Main St, Anytown, USA 123456");
        });
    }

    @Test
    void testAddressNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "1234567890", null);
        });
    }

    @Test
    void testAddressMaxLength() {
        // Test that exactly 30 characters is valid
        String address = "123456789012345678901234567890"; // Exactly 30 characters
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", address);
        assertEquals(address, contact.getAddress());
    }

    // --- Setter Tests ---
    @Test
    void testSettersSuccess() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        
        contact.setFirstName("Jane");
        contact.setLastName("Smith");
        contact.setPhone("0987654321");
        contact.setAddress("456 Other Ave");

        assertAll("Setter verification",
            () -> assertEquals("Jane", contact.getFirstName()),
            () -> assertEquals("Smith", contact.getLastName()),
            () -> assertEquals("0987654321", contact.getPhone()),
            () -> assertEquals("456 Other Ave", contact.getAddress())
        );
    }

    @Test
    void testSettersValidation() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");

        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("ThisNameIsTooLong"));
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
        
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("ThisNameIsTooLong"));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName(null));
        
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("123"));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("12345678901"));
        
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress("This Address Is Way Too Long For The Field"));
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(null));
    }
}