/**
 * DocumentService.java
 * Service for Document entity
 */
package com.nexjot.nexjot.api.service;

import com.nexjot.nexjot.api.dto.document.CreateDocumentDTO;
import com.nexjot.nexjot.api.dto.document.DocumentDTO;
import com.nexjot.nexjot.api.dto.document.UpdateDocumentDTO;
import com.nexjot.nexjot.api.exception.document.DocumentAlreadyExistsException;
import com.nexjot.nexjot.api.exception.document.DocumentCreationException;
import com.nexjot.nexjot.api.exception.document.DocumentNotFoundException;
import com.nexjot.nexjot.api.mapper.DocumentMapper;
import com.nexjot.nexjot.api.model.Document;
import com.nexjot.nexjot.api.model.User;
import com.nexjot.nexjot.api.repository.DocumentRepository;
import java.util.List;
import java.util.UUID;

import com.nexjot.nexjot.api.utility.StringSlicer;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing documents.
 */
@Service
@Slf4j
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final UserService userService;

    /**
     * Constructor for DocumentService.
     *
     * @param documentRepository the repository for managing documents
     */
    @Autowired
    DocumentService(DocumentRepository documentRepository, UserService userService) {
        this.documentRepository = documentRepository;
        this.userService = userService;
    }

    /**
     * Creates a new document.
     * @param docDTO the document to create
     * @return the created document
     */
    @Transactional
    public DocumentDTO create(CreateDocumentDTO docDTO) {
        Document doc;
        if (documentRepository.existsByTitle(docDTO.getTitle())) {
            throw new DocumentAlreadyExistsException("Document already exists");
        }
        try {
            doc = DocumentMapper.INSTANCE.toEntity(docDTO);
            doc.setOwner(userService.getCurrentAuthUser());
            doc = documentRepository.save(doc);
        } catch (Exception e) {
            throw new DocumentCreationException("Document not created");
        }
        return DocumentMapper.INSTANCE.toDTO(doc);
    }

    /**
     * Retrieves a document by its owner and id.
     * @param id id of the document
     * @return the document with the given owner and id
     */
    public DocumentDTO getDocument(UUID id) {
        Document document = documentRepository.findByIdAndOwner(
                        id, userService.getCurrentAuthUser())
                .orElseThrow(() -> new DocumentNotFoundException("Documents not found"));
        return DocumentMapper.INSTANCE.toDTO(document);
    }

    /**
     * Retrieves all documents by their owner.
     * @return list of documents
     */
    public List<DocumentDTO> getDocumentsByOwner() {
        return documentRepository.findByOwner(userService.getCurrentAuthUser())
                .orElseThrow(() -> new DocumentNotFoundException("Documents not found"))
                .stream().map(DocumentMapper.INSTANCE::toDTO)
                .toList();
    }

    /**
     * Retrieves all documents preview by their owner.
     * @return list of documents preview
     */
    public List<DocumentDTO> getDocumentsPreviewByOwner() {
        return documentRepository.findByOwner(userService.getCurrentAuthUser())
                .orElseThrow(() -> new DocumentNotFoundException("Document not found"))
                .stream()
                .map((doc) -> {
                    doc.setTitle(StringSlicer.sliceString(doc.getTitle(), 40).strip());
                    doc.setContent(StringSlicer.sliceString(doc.getContent(), 60).strip());
                    return DocumentMapper.INSTANCE.toDTO(doc);
                })
                .toList();
    }

    /**
     * Updates an existing document.
     * @param docDTO the document DTO containing the updated details
     * @param id the id of the document to update
     * @return the updated document DTO
     */
    public DocumentDTO updateDocument(UpdateDocumentDTO docDTO, UUID id) {
        Document document = documentRepository.findByIdAndOwner(id, userService.getCurrentAuthUser())
                .orElseThrow(() -> new DocumentNotFoundException("Document not found"));

        document.setTitle(docDTO.getTitle());
        document.setContent(docDTO.getContent());

        document = documentRepository.save(document);
        return DocumentMapper.INSTANCE.toDTO(document);
    }

    /**
     * Deletes a document.
     *
     * @param id the id of the document to delete
     */
    @Transactional
    public void delete(UUID id) {
        User owner = userService.getCurrentAuthUser();
        if (!documentRepository.existsByIdAndOwner(id, owner)) {
            throw new DocumentNotFoundException("Document not found");
        }
        documentRepository.deleteByIdAndOwner(id, owner);
    }

}
