package com.nexjot.nexjot.api.dto.document;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateDocumentDTO {
    @Size(min=9, max=50)
    private String title;
    private String content;
}
