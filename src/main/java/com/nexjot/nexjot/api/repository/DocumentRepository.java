/**
 * DocumentRepository.java
 * Repository for Document entity
 */
package com.nexjot.nexjot.api.repository;

import com.nexjot.nexjot.api.model.Document;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.nexjot.nexjot.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Document entity
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {
    boolean existsByTitle(String title);
    boolean existsByIdAndOwner(UUID id, User owner);
    Optional<List<Document>> findByOwner(User owner);
    Optional<Document> findByIdAndOwner(UUID id, User owner);
    void deleteByIdAndOwner(UUID id, User owner);
}
