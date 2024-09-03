/**
 * DocumentTest.java
 * Tests the Document model
 */
package com.nexjot.nexjot.api.test_model;

import com.nexjot.nexjot.api.model.Document;
import com.nexjot.nexjot.api.model.User;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the Document model
 */
public class DocumentTest {

    /**
     * Tests the addUsers method
     */
    @Test
    public void testAddUsers() {
        Document document = new Document();
        User user = new User();

        user.setDocuments(new HashSet<>());
        document.setUsers(new HashSet<>());

        document.addUser(user);

        assertTrue(document.getUsers().contains(user));
        assertTrue(user.getDocuments().contains(document));
    }

    /**
     * Tests the deleteUsers method
     */
    @Test
    public void testDeleteUsers() {
        Document document = new Document();
        User user = new User();

        user.setDocuments(new HashSet<>());
        document.setUsers(new HashSet<>());
        document.addUser(user);

        document.deleteUser(user);

        assertFalse(document.getUsers().contains(user));
        assertFalse(user.getDocuments().contains(document));
    }
}
