/**
 * DocumentRepository.java
 * Repository for Document entity
 */
package com.nexjot.nexjot.api.repository;

import com.nexjot.nexjot.api.model.Document;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Document entity
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {
    Document findByTitle(String title);
}
