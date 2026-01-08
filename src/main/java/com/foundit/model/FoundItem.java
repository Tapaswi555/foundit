package com.foundit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class FoundItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* =======================
       BASIC ITEM DETAILS
       ======================= */
    private String itemName;

    private String foundLocation;

    @Column(length = 1000)
    private String description;

    /* =======================
       CUSTODY INFORMATION
       ======================= */
    private String keptAt;

    /* =======================
       IMAGE STORAGE
       ======================= */
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    /* =======================
       WHO REPORTED THIS ITEM
       ======================= */
    @ManyToOne
    @JoinColumn(name = "reported_by_id")
    private User reportedBy;

    /* =======================
       ITEM STATUS
       ======================= */
    @Enumerated(EnumType.STRING)
    private ItemStatus status = ItemStatus.AVAILABLE;

    /* =======================
       GETTERS & SETTERS
       ======================= */

    public Long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getFoundLocation() {
        return foundLocation;
    }

    public void setFoundLocation(String foundLocation) {
        this.foundLocation = foundLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeptAt() {
        return keptAt;
    }

    public void setKeptAt(String keptAt) {
        this.keptAt = keptAt;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public User getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(User reportedBy) {
        this.reportedBy = reportedBy;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }
}
