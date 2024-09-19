package com.nexjot.nexjot.api.dto.document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DeleteDocumentDTO {
    @NotNull
    private UUID id;
}
