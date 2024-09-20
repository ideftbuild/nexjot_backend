///**
// * DocumentControllerTest.java
// * Unit tests for the DocumentController class.
// */
//package com.nexjot.nexjot.api.test_controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.nexjot.nexjot.api.controller.DocumentController;
//import com.nexjot.nexjot.api.model.Document;
//import com.nexjot.nexjot.api.service.DocumentService;
//import java.util.Arrays;
//import java.util.UUID;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(DocumentController.class)
//public class DocumentControllerTest {
//
//    @MockBean
//    private DocumentService documentService;
//
//    @Autowired
//    private MockMvc mockmvc;
//
//    @Autowired
//    private ObjectMapper mapper;
//
//    /**
//     * Set up the test environment.
//     */
//    @BeforeEach
//    public void setUp() {
//        mapper.registerModule(new JavaTimeModule());
//        mockmvc = MockMvcBuilders.standaloneSetup(new DocumentController(documentService)).build();
//    }
//
//    /**
//     * Test createDocument method with valid request made
//     * @throws Exception if any error occurs
//     */
//    @Test
//    public void testCreateDocument() throws Exception {
//        String jsonPayload = "{\"title\":\"Document 1\"," +
//                "\"content\":\"Hello NexJot\"," +
//                "\"owner_id\":\"" + UUID.randomUUID() + "\"}";
//
//        Document documentDB = mapper.readValue(jsonPayload, Document.class);
//        // return the document that is created when save method is called
//        when(documentService.save(any(Document.class))).thenReturn(documentDB);
//        // perform a post request to create a document
//        MockHttpServletResponse response = mockmvc.perform(post("/api/documents")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(jsonPayload))
//                        .andExpect(status().isCreated())
//                        .andReturn()
//                        .getResponse();
//        // get the document from the response
//        Document document = mapper.readValue(response.getContentAsString(), Document.class);
//        // verify that the document is created
//        assertEquals(documentDB.getTitle(), document.getTitle());
//        assertEquals(documentDB.getContent(), document.getContent());
//    }
//
//    /**
//     * Test createDocument method with missing title
//     * @throws Exception if any error occurs
//     */
//    @Test
//    public void testCreateDocumentMissingTitle() throws Exception {
//        String jsonPayload = "{\"content\":\"Hello NexJot\"," +
//                "\"owner_id\":\"" + UUID.randomUUID() + "\"}";
//        // perform a post request to create a document
//       mockmvc.perform(post("/api/documents")
//                       .contentType(MediaType.APPLICATION_JSON)
//                       .content(jsonPayload))
//               .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * Test createDocument method with missing content
//     * @throws Exception if any error occurs
//     */
//    @Test
//    public void testCreateDocumentNoRequestBody() throws Exception {
//        // perform a post request to create a document with no request body
//        mockmvc.perform(post("/api/documents")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }
//
//    /**
//     * Test getDocument method with valid request made
//     * @throws Exception if any error occurs
//     */
//    @Test
//    public void testGetDocument() throws Exception {
//        UUID id = UUID.randomUUID();
//        String expectedJsonContent ="{\"id\":\"" + UUID.randomUUID() +
//                "\",\"title\":null," +
//                "\"content\":null," +
//                "\"owner_id\": null}";
//
//        // return the document with the id
//        when(documentService.getDocumentById(id)).thenReturn(
//                mapper.readValue(expectedJsonContent, Document.class)
//        );
//        // verify that the document with the id is returned
//        mockmvc.perform(get("/api/documents/" + id))
//                .andExpect(status().isOk())
//                .andExpect(content().json(expectedJsonContent));
//    }
//
//    /**
//     * Test getDocument method with invalid id
//     * @throws Exception if any error occurs
//     */
//    @Test
//    public void testGetDocumentPassingInvalidId() throws Exception {
//        UUID id = UUID.randomUUID();
//        String expectedJsonContent ="{\"id\":\"" + UUID.randomUUID() +
//                "\",\"title\":null," +
//                "\"content\":null," +
//                "\"owner_id\":null}";
//
//        // return the document with the id
//        when(documentService.getDocumentById(id)).thenReturn(
//                mapper.readValue(expectedJsonContent, Document.class)
//        );
//        // verify that an error is returned when an invalid id is passed
//        mockmvc.perform(get("/api/documents/" + UUID.randomUUID()))
//                .andExpect(status().is4xxClientError());
//    }
//
//    /**
//     * Test getDocuments method with valid request made
//     * @throws Exception if any error occurs
//     */
//    @Test
//    public void testGetDocuments() throws Exception {
//        String[] expectedJsonContents = {"{\"id\":\"" + UUID.randomUUID() +
//                "\",\"title\":\"Document 1\"," +
//                "\"content\":\"Hello NexJot\"," +
//                "\"owner_id\":\"" + UUID.randomUUID() + "\"," +
//                "\"users\":null," +
//                "\"createdAt\":null," +
//                "\"updatedAt\":null}",
//                "{\"id\":\"" + UUID.randomUUID() +
//                "\",\"title\":\"Document 2\"," +
//                "\"content\":\"Hello NexJot\"," +
//                "\"owner_id\":\"" + UUID.randomUUID() + "\"," +
//                "\"users\":null," +
//                "\"createdAt\":null," +
//                "\"updatedAt\":null}"};
//        // return all the documents
//        when(documentService.getDocuments()).thenReturn(
//                Arrays.asList(
//                        mapper.readValue(expectedJsonContents[0], Document.class),
//                        mapper.readValue(expectedJsonContents[1], Document.class)
//                )
//        );
//        // verify that all documents are returned
//        mockmvc.perform(get("/api/documents"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(
//                        "[" + expectedJsonContents[0] + ","
//                                + expectedJsonContents[1] + "]"));
//    }
//
//    /**
//     * Test updateDocument method when title is passed in the request
//     * @throws Exception if any error occurs
//     */
//    @Test
//    public void testUpdateDocumentTitle() throws Exception {
//        UUID id = UUID.randomUUID();
//        String jsonPayload = "{\"title\":\"New Document Title\"}";
//        Document document = new Document();
//
//        document.setTitle("Document Title");
//        // return the document with the id
//        when(documentService.getDocumentById(id)).thenReturn(document);
//        // return the updated document
//        when(documentService.save(document)).thenReturn(document);
//
//        mockmvc.perform(put("/api/documents/" + id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonPayload))
//                .andExpect(status().isOk());
//
//        verify(documentService, times(1)).getDocumentById(id);
//        verify(documentService, times(1)).save(document);
//        // verify that the document title is updated
//        assertEquals(document.getTitle(), "New Document Title");
//    }
//
//    /**
//     * Test updateDocument when content is passed in the request
//     * @throws Exception if any error occurs
//     */
//    @Test
//    public void testUpdateDocumentContent() throws Exception {
//        UUID id = UUID.randomUUID();
//        String jsonPayload = "{\"content\":\"New Content\"}";
//        Document document = new Document();
//
//        document.setContent("Old Content");
//        // return the document with the id
//        when(documentService.getDocumentById(id)).thenReturn(document);
//        // return the updated document
//        when(documentService.save(document)).thenReturn(document);
//
//        mockmvc.perform(put("/api/documents/" + id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonPayload))
//                .andExpect(status().isOk());
//
//        verify(documentService, times(1)).getDocumentById(id);
//        verify(documentService, times(1)).save(document);
//        // verify that the document title is updated
//        assertEquals(document.getContent(), "New Content");
//    }
//
//
//    /**
//     * Test updateDocument when title and content is passed in the request
//     * @throws Exception if any error occurs
//     */
//    @Test
//    public void testUpdateDocumentTitleAndContent() throws Exception {
//        UUID id = UUID.randomUUID();
//        String jsonPayload = "{\"title\":\"New Document Title\"," +
//                "\"content\":\"New Content\"}";
//        Document document = new Document();
//
//        document.setTitle("Old Document Title");
//        document.setContent("Old Content");
//        // return the document with the id
//        when(documentService.getDocumentById(id)).thenReturn(document);
//        // return the updated document
//        when(documentService.save(document)).thenReturn(document);
//
//        mockmvc.perform(put("/api/documents/" + id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonPayload))
//                .andExpect(status().isOk());
//
//        verify(documentService, times(1)).getDocumentById(id);
//        verify(documentService, times(1)).save(document);
//        // verify that the document title is updated
//        assertEquals(document.getTitle(), "New Document Title");
//        assertEquals(document.getContent(), "New Content");
//    }
//
//    /**
//     * Test deleteDocument method with valid request made
//     * @throws Exception if any error occurs
//     */
//    @Test
//    public void testDeleteDocument() throws Exception {
//        UUID id = UUID.randomUUID();
//        Document mockDocument = new Document();
//
//        when(documentService.getDocumentById(id)).thenReturn(mockDocument);
//        doNothing().when(documentService).delete(mockDocument);
//
//        mockmvc.perform(delete("/api/documents/" + id))
//                .andExpect(status().isNoContent());
//
//        verify(documentService, times(1)).getDocumentById(id);
//        verify(documentService, times(1)).delete(mockDocument);
//    }
//
//    /**
//     * Test deleteDocument method with invalid id
//     * @throws Exception if any error occurs
//     */
//    @Test
//    public void testDeleteDocumentInvalidId()  throws Exception {
//        UUID id = UUID.randomUUID();
//        Document mockDocument = new Document();
//
//        when(documentService.getDocumentById(id)).thenReturn(mockDocument);
//        doNothing().when(documentService).delete(mockDocument);
//
//        mockmvc.perform(delete("/api/documents/" + UUID.randomUUID()))
//                .andExpect(status().is4xxClientError());
//    }
//}
