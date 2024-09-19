/**
 * DocumentController.java
 * Controller for managing documents.
 */
package com.nexjot.nexjot.api.controller;

import com.nexjot.nexjot.api.dto.document.CreateDocumentDTO;
import com.nexjot.nexjot.api.dto.document.DocumentDTO;
import com.nexjot.nexjot.api.dto.document.UpdateDocumentDTO;
import com.nexjot.nexjot.api.service.DocumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    /**?
     * Creates a new document.
     *
     * @param docDTO the data transfer object containing document details
     * @return the created document
     */
    @PostMapping("/documents")
    public ResponseEntity<DocumentDTO> createDocument(
            @Valid @RequestBody CreateDocumentDTO docDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(documentService.create(docDTO));
    }

    /**
     * Retrieve User's document by its ID.
     *
     * @param id the ID of the document to retrieve
     * @return the document
     */
    @GetMapping("/documents/{id}")
    public ResponseEntity<DocumentDTO> getDocument(@PathVariable UUID id) {
        return ResponseEntity.ok(documentService.getDocument(id));
    }

    /**
     * Retrieve User's documents
     * @return the list of documents
     */
    @GetMapping("/documents")
    public ResponseEntity<List<DocumentDTO>> getDocuments() {
        return ResponseEntity.ok(documentService.getDocumentsByOwner());
    }

    /**
     * Retrieve User's documents as a preview
     * @return the list of documents
     */
    @GetMapping("/documents/preview")
    public ResponseEntity<List<DocumentDTO>> getDocumentsPreview()  {
        return ResponseEntity.ok(documentService.getDocumentsPreviewByOwner());
    }

    /**
     * Update User's document by its ID.
     * @param docDTO the data transfer object containing document details
     * @param id the ID of the document to update
     * @return the updated document
     */
    @PutMapping("/documents/{id}")
    public ResponseEntity<DocumentDTO> updateDocument(
            @Valid @RequestBody UpdateDocumentDTO docDTO, @PathVariable UUID id) {
        return ResponseEntity.ok(documentService.updateDocument(docDTO, id));
    }

    /**
     * Delete User's document by its ID.
     *
     * @param id the ID of the document to delete
     * @return No content
     */
    @DeleteMapping("/documents/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable UUID id) {
        documentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
