package com.foundit.repository;

import com.foundit.model.LostItem;
import com.foundit.model.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LostItemRepository extends JpaRepository<LostItem, Long> {

    /**
     * Fetch all lost items with a specific status (e.g., LOST, MATCHED).
     */
    List<LostItem> findByStatus(ItemStatus status);

    /**
     * Find lost items by item name (case-insensitive).
     */
    List<LostItem> findByItemNameIgnoreCase(String itemName);

    /**
     * Find lost items by location (case-insensitive).
     */
    List<LostItem> findByLocationIgnoreCase(String location);
}
