/**
 * DocumentDTO.java
 * Data Transfer Object for Document entity
 */
package com.nexjot.nexjot.api.dto.document;

import com.nexjot.nexjot.api.dto.user.UserDTO;
import com.nexjot.nexjot.api.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import lombok.*;

/**
 * This class is used to map the request body of the document creation request.
 * It contains the title, content and owner of the document.
 * The title is a required field and should be between 1 and 255 characters.
 * The content is optional and can be of any length.
 * The owner is the UUID of the user who created the document.
 */
@Data
public class DocumentDTO {
    @NotNull
    private UUID id;
    @NotBlank
    private String title;
    private String content;
    @NotBlank
    private UserDTO owner;
    @NotBlank
    private LocalDateTime createdAt;
    @NotBlank
    private LocalDateTime updatedAt;

    private Set<User> users;

    public DocumentDTO(UUID id) {
       this.id = id;
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", owner=" + owner +
                '}';
    }
}
