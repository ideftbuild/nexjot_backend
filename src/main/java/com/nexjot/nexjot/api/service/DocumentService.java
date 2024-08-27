/**
 * DocumentService.java
 * Service for Document entity
 */
package com.nexjot.nexjot.api.service;

import com.nexjot.nexjot.api.model.Document;
import com.nexjot.nexjot.api.repository.DocumentRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing documents.
 */
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    /**
     * Constructor for DocumentService.
     *
     * @param documentRepository the repository for managing documents
     */
    @Autowired
    DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    /**
     * Saves a document.
     *
     * @param document the document to save
     * @return the saved document
     */
    public Document save(Document document) {
        return documentRepository.save(document);
    }

    /**
     * Deletes a document.
     *
     * @param document the document to delete
     */
    public void delete(Document document) {
        documentRepository.delete(document);
    }

    /**
     * Retrieves a document by its title.
     *
     * @param title the title of the document
     * @return the document with the given title, or null if not found
     */
    public Document getDocumentByTitle(String title) {
        return documentRepository.findByTitle(title);
    }

    /**
     * Retrieves all documents.
     *
     * @return a list of all documents
     */
    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }

    /**
     * Retrieves a document by its ID.
     *
     * @param id the ID of the document
     * @return the document with the given ID, or null if not found
     */
    public Document getDocumentById(UUID id) {
        return documentRepository.findById(id).orElse(null);
    }
}
