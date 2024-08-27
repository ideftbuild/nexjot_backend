/**
 * DocumentServiceTest.java
 * Tests the DocumentService class
 */
package com.nexjot.nexjot.api.test_service;

import com.nexjot.nexjot.api.model.Document;
import com.nexjot.nexjot.api.repository.DocumentRepository;
import com.nexjot.nexjot.api.service.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

/**
 * This is a test class for the DocumentService class.
 * It tests the save, delete, getDocumentByTitle, getDocuments, and getDocumentById methods.
 * The tests are done using mockito to mock the Document class and the DocumentRepository class.
 * The tests are done to verify that the methods in the DocumentService class are working
 */
public class DocumentServiceTest {
    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private DocumentService documentService;

    @SuppressWarnings("EmptyTryBlock")
    @BeforeEach
    public void setUp() {
        try (AutoCloseable ignored = MockitoAnnotations.openMocks(this)) {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Add tests here
    @Test
    public void testSave() {
        Document mockDocument = mock(Document.class);  // mock document
        // return mock when save method in called on documentRepository
        when(documentRepository.save(mockDocument)).thenReturn(mockDocument);
        Document result = documentService.save(mockDocument);

        assert result == mockDocument;  // compare the result with the mock document
    }

    @Test
    public void testDelete() {
        Document mockDocument = mock(Document.class);

        documentService.delete(mockDocument);
        // verify that delete method is called to delete the document
        verify(documentRepository, times(1)).delete(mockDocument);
    }

    @Test
    public void testGetDocumentByTitle() {
        Document mockDocument = mock(Document.class);
        String title = "Document Title 1";

        when(documentRepository.findByTitle(title)).thenReturn(mockDocument);
        // verify that the document with the title is returned
        Document result = documentService.getDocumentByTitle(title);

        assert result == mockDocument;
    }

    @Test
    public void testGetDocuments() {
        List<Document> documents = new ArrayList<>();
        documents.add(mock(Document.class));
        documents.add(mock(Document.class));

        when(documentRepository.findAll()).thenReturn(documents);
        // verify that all documents are returned
        List<Document> result = documentService.getDocuments();

        assert result == documents;
    }

    @Test
    public void testGetDocumentById() {
        Document mockDocument = mock(Document.class);
        UUID id = UUID.randomUUID();

        when(documentRepository.findById(id)).thenReturn(
                Optional.ofNullable(mockDocument));
        // verify that the document with the id is returned
        Document result = documentService.getDocumentById(id);

        assert result == mockDocument;
    }
}