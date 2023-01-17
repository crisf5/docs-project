package com.documentcba.doc.controller;

import com.documentcba.doc.dto.DocumentCreatedResponseDTO;
import com.documentcba.doc.dto.DocumentDTO;
import com.documentcba.doc.dto.DocumentOneHashDTO;
import com.documentcba.doc.exceptions.FileNotCreated;
import com.documentcba.doc.model.DocumentModel;
import com.documentcba.doc.service.DocumentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("hash")
    public ResponseEntity<DocumentCreatedResponseDTO> createDocuments(
            @RequestParam List<MultipartFile> files,
            @RequestParam String hashType) throws NoSuchAlgorithmException, IOException {

        DocumentCreatedResponseDTO dtos = documentService.saveDocuments(files, hashType);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("200", "Se guardó el registro de archivos");

        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(dtos);
    }

    @GetMapping()
    public ResponseEntity<List<DocumentDTO>> getDocumentList(){

        List<DocumentDTO> documentModel = documentService.getAllDocument();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("200", "Se devolvieron correctamente todos los documetos");

        return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body(documentModel);
    }

    @GetMapping(params = {"hashType", "hash"})
    public ResponseEntity<DocumentOneHashDTO> getDocumentByHash(
            @RequestParam(defaultValue = "SHA-256") String hashType,
            @RequestParam String hash){

        DocumentOneHashDTO documentOneHashDTO = documentService.getDocumentByHash(hashType, hash);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("200", "Se devolvió el documento");

        return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body(documentOneHashDTO);
    }


}
