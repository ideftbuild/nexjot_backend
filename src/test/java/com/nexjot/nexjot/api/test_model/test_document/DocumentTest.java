/**
 * DocumentTest.java
 * Tests the Document model
 */
package com.nexjot.nexjot.api.test_model.test_document;

import com.nexjot.nexjot.api.model.Document;
import com.nexjot.nexjot.api.model.User;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Document model
 */
public class DocumentTest {

    private User user;
    private Document document;

    @BeforeEach
    public void setUp() {
        user = new User();
        document = new Document("title", "content", user);
        document.setUsers(new HashSet<>());
        user.setDocuments(new HashSet<>());
    }

    /**
     * Tests the addUsers method
     */
    @Test
    public void testAddUsers() {

        document.addUser(user);

        assertTrue(document.getUsers().contains(user));
        assertTrue(user.getDocuments().contains(document));
    }

    /**
     * Tests the deleteUsers method
     */
    @Test
    public void testDeleteUsers() {
        document.getUsers().add(user);

        document.deleteUser(user);

        assertEquals(document.getUsers().size(), 0);
    }
    
}
