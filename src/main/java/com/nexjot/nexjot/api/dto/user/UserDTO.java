package com.nexjot.nexjot.api.dto.user;

import com.nexjot.nexjot.api.model.Document;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserDTO {

    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<Document> documents;
}
