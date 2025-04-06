package com.example.common.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.JoinColumn;
import java.util.List;

@Entity
@Table(name = "database_stats")
public class DatabaseStats {
    @Id
    @Column(name = "database_name")
    private String databaseName;
    
    @Column(name = "size_in_bytes")
    private long sizeInBytes;
    
    @ElementCollection
    @CollectionTable(name = "database_collections", joinColumns = @JoinColumn(name = "database_name"))
    @Column(name = "collection_name")
    private List<String> collections;
    
    @Column(name = "document_count")
    private int documentCount;
    
    // Default constructor
    public DatabaseStats() {
    }
    
    // All-args constructor
    public DatabaseStats(String databaseName, long sizeInBytes, List<String> collections, int documentCount) {
        this.databaseName = databaseName;
        this.sizeInBytes = sizeInBytes;
        this.collections = collections;
        this.documentCount = documentCount;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    public List<String> getCollections() {
        return collections;
    }

    public int getDocumentCount() {
        return documentCount;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setSizeInBytes(long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    public void setCollections(List<String> collections) {
        this.collections = collections;
    }

    public void setDocumentCount(int documentCount) {
        this.documentCount = documentCount;
    }
    
    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        DatabaseStats that = (DatabaseStats) o;
        
        if (sizeInBytes != that.sizeInBytes) return false;
        if (documentCount != that.documentCount) return false;
        if (databaseName != null ? !databaseName.equals(that.databaseName) : that.databaseName != null) return false;
        return collections != null ? collections.equals(that.collections) : that.collections == null;
    }
    
    @Override
    public int hashCode() {
        int result = databaseName != null ? databaseName.hashCode() : 0;
        result = 31 * result + (int) (sizeInBytes ^ (sizeInBytes >>> 32));
        result = 31 * result + (collections != null ? collections.hashCode() : 0);
        result = 31 * result + documentCount;
        return result;
    }
    
    @Override
    public String toString() {
        return "DatabaseStats{" +
                "databaseName='" + databaseName + '\'' +
                ", sizeInBytes=" + sizeInBytes +
                ", collections=" + collections +
                ", documentCount=" + documentCount +
                '}';
    }
}