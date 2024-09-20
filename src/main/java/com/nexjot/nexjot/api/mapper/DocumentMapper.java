package com.nexjot.nexjot.api.mapper;

import com.nexjot.nexjot.api.dto.document.CreateDocumentDTO;
import com.nexjot.nexjot.api.dto.document.DeleteDocumentDTO;
import com.nexjot.nexjot.api.dto.document.DocumentDTO;
import com.nexjot.nexjot.api.dto.document.UpdateDocumentDTO;
import com.nexjot.nexjot.api.model.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DocumentMapper {

    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    @Mapping(source = "owner", target = "owner")
    DocumentDTO toDTO(Document document);
//    @Mapping(source = "owner", target = "owner")
//    Document toEntity(DocumentDTO docDTO);
//    CreateDocumentDTO toCreateDTO(Document document);
    Document toEntity(CreateDocumentDTO createDocDTO);
    UpdateDocumentDTO toUpdateDTO(Document document);
//    Document toEntity(UpdateDocumentDTO updateDocDTO);
    DeleteDocumentDTO toDeleteDTO(Document document);
//    Document toEntity(DeleteDocumentDTO deleteDocDTO);
}
