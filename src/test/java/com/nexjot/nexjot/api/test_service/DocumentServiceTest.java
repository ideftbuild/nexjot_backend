/**
 * DocumentServiceTest.java
 * Tests the DocumentService class
 */
package com.nexjot.nexjot.api.test_service;

import com.nexjot.nexjot.api.dto.document.CreateDocumentDTO;
import com.nexjot.nexjot.api.dto.document.DocumentDTO;
import com.nexjot.nexjot.api.dto.document.UpdateDocumentDTO;
import com.nexjot.nexjot.api.model.Document;
import com.nexjot.nexjot.api.model.User;
import com.nexjot.nexjot.api.repository.DocumentRepository;
import com.nexjot.nexjot.api.service.DocumentService;
import com.nexjot.nexjot.api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
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

    @Mock
    private UserService userService;

    private User owner;

    @InjectMocks
    private DocumentService documentService;

    @SuppressWarnings("EmptyTryBlock")
    @BeforeEach
    public void setUp() {
        try (AutoCloseable ignored = MockitoAnnotations.openMocks(this)) {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        owner = mock(User.class);
        when(userService.getCurrentAuthUser()).thenReturn(owner);
    }

    /**
     * Test the create method in the DocumentService class
     */
    @Test
    public void testCreateDocument() {
        CreateDocumentDTO docDTO = new CreateDocumentDTO("Document Title 1",
                "Document Content 1");
        // return mock when save method in called on documentRepository
        when(documentRepository.save(any())).thenAnswer(invocation -> {
            Document document = invocation.getArgument(0);
            assertEquals(document.getTitle(), docDTO.getTitle());
            assertEquals(document.getContent(), docDTO.getContent());
            return document;
        });

        documentService.create(docDTO);
        verify(documentRepository, times(1)).save(any());
    }


    /**
     * Test getDocument method that it returns a document with the given id
     */
    @Test
    public void testGetDocument() {
        Document document = new Document("My Title", "My Content", null);
        UUID id = UUID.randomUUID();

        document.setId(id);
        when(documentRepository.findByIdAndOwner(id, owner)).thenReturn(Optional.of(document));

        DocumentDTO docDTO = documentService.getDocument(id);

        assertEquals(docDTO.getId(), id);
    }


    /**
     * Test the getDocumentsByOwner method in the DocumentService class
     */
    @Test
    public void testGetDocumentsByOwner() {
        List<Document> mockDocuments = List.of(
                Document.builder().title("Document 1").owner(owner).build(),
                Document.builder().title("Document 2").owner(owner).build()
        );

        when(documentRepository.findByOwner(owner)).thenReturn(Optional.of(mockDocuments));

        List<DocumentDTO> docsDTO = documentService.getDocumentsByOwner();

        assertEquals(docsDTO.get(0).getTitle(), "Document 1");
        assertEquals(docsDTO.get(1).getTitle(), "Document 2");
    }

    /**
     * Test getDocumentsPreviewByOwner that it returns a preview of all
     * documents owner by a user with the length of the title and content
     * limited to 25 and 50 characters respectively
     */
    @Test
    public void testGetDocumentsPreview() {
        List<Document> mockDocuments = List.of(
            Document.builder()
                .title("Testing Testing Testing Very Very Very Long Long Title Title")  // 60 characters
                .content("This is some random words, don't take it too serious, just a little while")  // 73 characters
                .build(),
            Document.builder()
                    .title("Testing title")  // 13 characters
                    .content("This is some random words")  // 25
                    .build()
        );

        when(userService.getCurrentAuthUser()).thenReturn(owner);
        when(documentRepository.findByOwner(owner)).thenReturn(Optional.of(mockDocuments));

        List<DocumentDTO> docDTOs = documentService.getDocumentsPreviewByOwner();

        assertTrue(docDTOs.get(0).getTitle().length() <= 40);
        assertTrue(docDTOs.get(0).getContent().length() <= 60);
        assertEquals(docDTOs.get(1).getTitle(), "Testing title");
        assertEquals(docDTOs.get(1).getContent(), "This is some random words");
    }

    /**
     * Test the update method in the DocumentService class
     */
    @Test
    public void testUpdateDocument() {
        UUID id = UUID.randomUUID();
        UpdateDocumentDTO docDTO = new UpdateDocumentDTO("New Title", "New Content");
        Document doc = new Document("old title", "old content", owner);

        when(documentRepository.findByIdAndOwner(id, owner)).thenReturn(
                Optional.of(doc));
        when(documentRepository.save(any())).thenReturn(doc);

        documentService.updateDocument(docDTO, id);

        assertEquals(doc.getTitle(), docDTO.getTitle());
        assertEquals(doc.getContent(), docDTO.getContent());
    }

    /**
     * Test the delete method in the DocumentService class
     */
    @Test
    public void testDeleteDocument() {
        UUID id = UUID.randomUUID();

        when(documentRepository.existsByIdAndOwner(id, owner)).thenReturn(true);
        doNothing().when(documentRepository).deleteByIdAndOwner(id, owner);

        documentService.delete(id);
        // verify that delete method is called to delete the document
        verify(documentRepository, times(1)).deleteByIdAndOwner(any(), any());
    }
}
