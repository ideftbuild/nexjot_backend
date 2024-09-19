package com.nexjot.nexjot.api.test_model.test_user;

import com.nexjot.nexjot.api.model.Document;
import com.nexjot.nexjot.api.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    private User user;
    private Document document;

    @BeforeEach
    public void setUp() {
        user = new User();
        document = new Document("title", "content", user);
        document.setUsers(new HashSet<>());
        user.setDocuments(new HashSet<>());
    }

    @Test
    public void testAddDocument() {
        user.addDocument(document);

        assertEquals(user.getDocuments().size(), 1);
        assertEquals(document.getUsers().size(), 1);
    }

    @Test
    public void testDeleteDocument() {
        user.getDocuments().add(document);

        user.deleteDocument(document);

        assertEquals(user.getDocuments().size(), 0);
    }
}
