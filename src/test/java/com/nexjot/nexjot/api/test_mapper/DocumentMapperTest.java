package com.nexjot.nexjot.api.test_mapper;

import com.nexjot.nexjot.api.dto.document.CreateDocumentDTO;
import com.nexjot.nexjot.api.dto.document.DeleteDocumentDTO;
import com.nexjot.nexjot.api.dto.document.DocumentDTO;
import com.nexjot.nexjot.api.dto.document.UpdateDocumentDTO;
import com.nexjot.nexjot.api.mapper.DocumentMapper;
import com.nexjot.nexjot.api.model.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentMapperTest {

    private static Document document;


    @BeforeAll
    public static void setUpOnce() {
        document = Document.builder()
            .title("My Title")
            .content("My Content")
            .build();
    }
    /**
     * Tests the toDTO method
     */
   @Test
    public void testToDTO() {
       DocumentDTO docDTO =  DocumentMapper.INSTANCE.toDTO(document);

       assertEquals(document.getTitle(), docDTO.getTitle());
       assertEquals(document.getContent(), docDTO.getContent());
   }

   @Test
    public void testToEntity() {
       CreateDocumentDTO docDTO = new CreateDocumentDTO("My Title", "My Content");

       Document document  = DocumentMapper.INSTANCE.toEntity(docDTO);

       assertEquals(document.getTitle(), docDTO.getTitle());
       assertEquals(document.getContent(), docDTO.getContent());
   }

   @Test
    public void testUpdateDocumentDTO() {
       UpdateDocumentDTO docDTO =  DocumentMapper.INSTANCE.toUpdateDTO(document);

       assertEquals(document.getTitle(), docDTO.getTitle());
       assertEquals(document.getContent(), docDTO.getContent());

   }

   @Test
    public void testDeleteDocumentDTO() {
       DeleteDocumentDTO docDTO = DocumentMapper.INSTANCE.toDeleteDTO(document);

       assertEquals(docDTO.getId(), document.getId());
   }
}

