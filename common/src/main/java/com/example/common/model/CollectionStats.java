package com.example.common.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "collection_stats")
public class CollectionStats {
    @Id
    @Column(name = "collection_name")
    private String collectionName;
    
    @Column(name = "document_count")
    private long documentCount;
    
    @Column(name = "size_in_bytes")
    private long sizeInBytes;
    
    @Column(name = "avg_object_size")
    private String avgObjectSize;
    
    @Column(name = "storage_size")
    private String storageSize;
    
    @Column(name = "capped")
    private boolean capped;
    
    // Default constructor
    public CollectionStats() {
    }
    
    // All-args constructor
    public CollectionStats(String collectionName, long documentCount, long sizeInBytes, 
                          String avgObjectSize, String storageSize, boolean capped) {
        this.collectionName = collectionName;
        this.documentCount = documentCount;
        this.sizeInBytes = sizeInBytes;
        this.avgObjectSize = avgObjectSize;
        this.storageSize = storageSize;
        this.capped = capped;
    }
    
    // Getters
    public String getCollectionName() {
        return collectionName;
    }
    
    public long getDocumentCount() {
        return documentCount;
    }
    
    public long getSizeInBytes() {
        return sizeInBytes;
    }
    
    public String getAvgObjectSize() {
        return avgObjectSize;
    }
    
    public String getStorageSize() {
        return storageSize;
    }
    
    public boolean isCapped() {
        return capped;
    }
    
    // Setters
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
    
    public void setDocumentCount(long documentCount) {
        this.documentCount = documentCount;
    }
    
    public void setSizeInBytes(long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }
    
    public void setAvgObjectSize(String avgObjectSize) {
        this.avgObjectSize = avgObjectSize;
    }
    
    public void setStorageSize(String storageSize) {
        this.storageSize = storageSize;
    }
    
    public void setCapped(boolean capped) {
        this.capped = capped;
    }
    
    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        CollectionStats that = (CollectionStats) o;
        
        if (documentCount != that.documentCount) return false;
        if (sizeInBytes != that.sizeInBytes) return false;
        if (capped != that.capped) return false;
        if (collectionName != null ? !collectionName.equals(that.collectionName) : that.collectionName != null) return false;
        if (avgObjectSize != null ? !avgObjectSize.equals(that.avgObjectSize) : that.avgObjectSize != null) return false;
        return storageSize != null ? storageSize.equals(that.storageSize) : that.storageSize == null;
    }
    
    @Override
    public int hashCode() {
        int result = collectionName != null ? collectionName.hashCode() : 0;
        result = 31 * result + (int) (documentCount ^ (documentCount >>> 32));
        result = 31 * result + (int) (sizeInBytes ^ (sizeInBytes >>> 32));
        result = 31 * result + (avgObjectSize != null ? avgObjectSize.hashCode() : 0);
        result = 31 * result + (storageSize != null ? storageSize.hashCode() : 0);
        result = 31 * result + (capped ? 1 : 0);
        return result;
    }
    
    @Override
    public String toString() {
        return "CollectionStats{" +
                "collectionName='" + collectionName + '\'' +
                ", documentCount=" + documentCount +
                ", sizeInBytes=" + sizeInBytes +
                ", avgObjectSize='" + avgObjectSize + '\'' +
                ", storageSize='" + storageSize + '\'' +
                ", capped=" + capped +
                '}';
    }
}