package com.nexjot.nexjot.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;  // This is the primary key of the table

    @ManyToMany
    @JoinTable(
            name = "user_documents",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    private Set<Document> documents;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", documentCount=" + (documents == null || documents.isEmpty() ? "null" : documents.size()) +
                '}';
    }
}
