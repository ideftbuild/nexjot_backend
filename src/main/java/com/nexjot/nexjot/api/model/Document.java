/**
 * Document.java
 * Entity class for Document
 */
package com.nexjot.nexjot.api.model;

import com.nexjot.nexjot.api.listener.DocumentListener;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import lombok.*;

/**
 * Document model class
 * This class represents the document entity in the database
 * It contains the document's title, content, owner, users, createdAt, and updatedAt
 * It also contains the necessary methods to add and remove users from the document
 * It also contains the necessary annotations to map the document entity to the database
 */
@Entity
@Table(name = "documents")
@EntityListeners(DocumentListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "owner", nullable=false)
    private User owner;

    @ManyToMany(mappedBy = "documents")
    private Set<User> users;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Document (String title, String content, User owner) {
        this.title = title;
        this.content = content;
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content);
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
                ", owner=" + owner.getId() +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
