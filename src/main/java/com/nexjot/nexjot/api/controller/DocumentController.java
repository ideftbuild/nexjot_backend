/**
 * DocumentController.java
 * Controller for managing documents.
 */
package com.nexjot.nexjot.api.controller;

import com.nexjot.nexjot.api.dto.DocumentDTO;
import com.nexjot.nexjot.api.model.Document;
import com.nexjot.nexjot.api.service.DocumentService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing documents.
 */
@Controller
@RequestMapping("/api")
public class DocumentController {
    private final DocumentService documentService;

    /**
     * Constructor for DocumentController.
     *
     * @param documentService the service for managing documents
     */
    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * Creates a new document.
     *
     * @param documentDTO the data transfer object containing document details
     * @return the created document
     */
    @PostMapping("/documents")
    public ResponseEntity<Document> createDocument(
            @Valid @RequestBody DocumentDTO documentDTO) {
        Document document = new Document(
                documentDTO.getTitle(),
                documentDTO.getContent(),
                documentDTO.getOwner_id());
        Document savedDocument = documentService.save(document);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDocument);
    }

    /**
     * Retrieves a document by its ID.
     *
     * @param id the ID of the document
     * @return the document if found, otherwise a 404 status
     */
    @GetMapping("/documents/{id}")
    public ResponseEntity<?> getDocument(@PathVariable UUID id) {
        Document document = documentService.getDocumentById(id);
        if (document == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Document Not Found");
        }
        return ResponseEntity.ok(document);
    }

    /**
     * Retrieves all documents.
     *
     * @return a list of all documents
     */
    @GetMapping("/documents")
    public ResponseEntity<List<Document>> getDocuments() {
        List<Document> documents = documentService.getDocuments();
        return ResponseEntity.ok(documents);
    }

    /**
     * Updates an existing document.
     *
     * @param id the ID of the document to update
     * @param documentDTO the data transfer object containing updated document details
     * @return the updated document if found, otherwise a 404 status
     */
    @PutMapping("/documents/{id}")
    public ResponseEntity<?> updateDocument(
            @PathVariable UUID id, @RequestBody DocumentDTO documentDTO) {
        Document document = documentService.getDocumentById(id);
        // Check if the document exists
        if (document == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Document Not Found");
        }
        // Update the document
        if (documentDTO.getTitle() != null && !documentDTO.getTitle().isEmpty()) {
            document.setTitle(documentDTO.getTitle());
        }
        if (documentDTO.getContent() != null && !documentDTO.getContent().isEmpty()) {
            document.setContent(documentDTO.getContent());
        }
        documentService.save(document); // save the updated document
        return ResponseEntity.ok(document);
    }

    /**
     * Deletes a document by its ID.
     *
     * @param id the ID of the document to delete
     * @return a 204 status if the document is deleted, otherwise a 404 status
     */
    @DeleteMapping("/documents/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable UUID id) {
        Document document = documentService.getDocumentById(id); // Check if the document exists

        if (document == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Document Not Found");
        }

        documentService.delete(document); // Delete the document
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
