package com.nexjot.nexjot.api.dto.document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateDocumentDTO {
    @NotBlank
    @Size(min=9, max=100)
    private String title;
    private String content;
}
