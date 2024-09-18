package com.nexjot.nexjot.api.listener;

import com.nexjot.nexjot.api.model.User;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;

public class UserListener {

    @PrePersist
    public  void prePersist(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(User user) {
        user.setUpdatedAt(LocalDateTime.now());
    }
}
