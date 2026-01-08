package com.foundit.service;

import com.foundit.model.FoundItem;
import com.foundit.model.ItemStatus;
import com.foundit.model.LostItem;
import com.foundit.repository.FoundItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    private final FoundItemRepository foundItemRepository;

    public MatchService(FoundItemRepository foundItemRepository) {
        this.foundItemRepository = foundItemRepository;
    }

    /**
     * Find matching found items for a given lost item
     */
    public List<FoundItem> findMatches(LostItem lostItem) {

        List<FoundItem> allFoundItems = foundItemRepository.findAll();
        List<FoundItem> matches = new ArrayList<>();

        for (FoundItem found : allFoundItems) {

            // Match by item name
            boolean nameMatch =
                    found.getItemName() != null &&
                    lostItem.getItemName() != null &&
                    found.getItemName().equalsIgnoreCase(lostItem.getItemName());

            // Match by location
            boolean locationMatch =
                    found.getFoundLocation() != null &&
                    lostItem.getLocation() != null &&
                    found.getFoundLocation().equalsIgnoreCase(lostItem.getLocation());

            // Match by description keywords
            boolean descriptionMatch =
                    found.getDescription() != null &&
                    lostItem.getDescription() != null &&
                    found.getDescription().toLowerCase()
                            .contains(lostItem.getDescription().toLowerCase());

            if (nameMatch && locationMatch && descriptionMatch) {
                found.setStatus(ItemStatus.MATCHED);
                matches.add(found);
            }
        }

        return matches;
    }
}
