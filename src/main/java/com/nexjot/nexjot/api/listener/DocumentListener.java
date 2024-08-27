/**
 * DocumentListener.java
 * Listener class for Document entity
 */
package com.nexjot.nexjot.api.listener;

import com.nexjot.nexjot.api.model.Document;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * This class is used to listen to the Document entity lifecycle events.
 * It listens to the prePersist and preUpdate events and sets the
 * createdAt and updatedAt fields respectively.
 */
public class DocumentListener {

    @PrePersist
    public void prePersist(Document document) {
        document.setCreatedAt(LocalDateTime.now());
        document.setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(Document document) {
        document.setUpdatedAt(LocalDateTime.now());
    }
}
