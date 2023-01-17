package com.documentcba.doc.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public class DocumentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Column(name = "hash-sha-256")
    private String hashSha256;

    @Column(name = "hash-sha-512")
    private String hashSha512;

    private LocalDateTime lastUpload;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getHashSha256() {
        return hashSha256;
    }

    public void setHashSha256(String hashSha256) {
        this.hashSha256 = hashSha256;
    }

    public String getHashSha512() {
        return hashSha512;
    }

    public void setHashSha512(String hashSha512) {
        this.hashSha512 = hashSha512;
    }

    public LocalDateTime getLastUpload() {
        return lastUpload;
    }

    public void setLastUpload(LocalDateTime lastUpload) {
        this.lastUpload = lastUpload;
    }
}
