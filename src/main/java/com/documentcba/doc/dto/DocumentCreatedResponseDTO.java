package com.documentcba.doc.dto;

import java.util.List;

public class DocumentCreatedResponseDTO {

    private String algorithm;
    private List<DocumentCreatedDTO> documents;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithms(String algorithm) {
        this.algorithm = algorithm;
    }

    public List<DocumentCreatedDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentCreatedDTO> documents) {
        this.documents = documents;
    }
}
