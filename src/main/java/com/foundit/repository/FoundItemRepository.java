package com.foundit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foundit.model.FoundItem;

public interface FoundItemRepository extends JpaRepository<FoundItem, Long> {
}
