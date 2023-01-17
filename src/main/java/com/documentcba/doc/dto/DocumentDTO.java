package com.documentcba.doc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class DocumentDTO {

    private String fileName;
    @JsonProperty("hash-sha-256")
    private String hashSha256;

    @JsonProperty("hash-sha-512")
    private String hashSha512;
    @JsonProperty("lastUpload")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime lastUpload;

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
