package com.documentcba.doc.service;

import com.documentcba.doc.dto.DocumentCreatedResponseDTO;
import com.documentcba.doc.dto.DocumentDTO;
import com.documentcba.doc.dto.DocumentOneHashDTO;
import com.documentcba.doc.model.DocumentModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface DocumentService {

    public DocumentCreatedResponseDTO saveDocuments(List<MultipartFile> files, String hashType) throws NoSuchAlgorithmException, IOException;

    public List<DocumentDTO> getAllDocument();

    public DocumentOneHashDTO getDocumentByHash(String hashType, String hash);
}
