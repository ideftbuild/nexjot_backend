/**
 * Document.java
 * Entity class for Document
 */
package com.nexjot.nexjot.api.model;

import com.nexjot.nexjot.api.listener.DocumentListener;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Document model class
 * This class represents the document entity in the database
 * It contains the document's title, content, owner_id, users, createdAt, and updatedAt
 * It also contains the necessary methods to add and remove users from the document
 * It also contains the necessary annotations to map the document entity to the database
 */
@Entity
@Table(name = "documents")
@EntityListeners(DocumentListener.class)
@NoArgsConstructor
@Getter
@Setter
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private UUID owner_id;

    @ManyToMany(mappedBy = "documents")
    private Set<User> users;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Document (String title, String content, UUID owner_id) {
        this.title = title;
        this.content = content;
        this.owner_id = owner_id;
    }

    /**
     * Add a user to the document
     * @param user the user to be added
     */
    public void addUser(User user) {
        user.getDocuments().add(this);  // add the document to the user's documents
        this.users.add(user);  // add the user to the current document
    }

    /**
     * Remove a user from the document
     * @param user the user to be removed
     */
    public void deleteUser(User user) {
        user.getDocuments().remove(this);  // remove the document from the user's documents
        this.users.remove(user);  // remove the user from the current document
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", owner_id=" + owner_id +
                ", userCount=" + (users == null || users.isEmpty() ? "null" : users.size()) +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
