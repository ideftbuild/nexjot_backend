/**
 * DocumentDTO.java
 * Data Transfer Object for Document entity
 */
package com.nexjot.nexjot.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is used to map the request body of the document creation request.
 * It contains the title, content and owner_id of the document.
 * The title is a required field and should be between 1 and 255 characters.
 * The content is optional and can be of any length.
 * The owner_id is the UUID of the user who created the document.
 */
@Getter
@Setter
public class DocumentDTO {
    @NotNull
    @Size(min=1, max=255)
    private String title;
    private String content;
    private UUID owner_id;
}
