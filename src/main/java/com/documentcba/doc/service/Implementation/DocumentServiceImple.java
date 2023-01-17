package com.documentcba.doc.service.Implementation;

import com.documentcba.doc.dto.DocumentCreatedDTO;
import com.documentcba.doc.dto.DocumentCreatedResponseDTO;
import com.documentcba.doc.dto.DocumentDTO;
import com.documentcba.doc.dto.DocumentOneHashDTO;
import com.documentcba.doc.exceptions.DocumentNotFound;
import com.documentcba.doc.exceptions.FileNotCreated;
import com.documentcba.doc.exceptions.ParamNotFound;
import com.documentcba.doc.model.DocumentModel;
import com.documentcba.doc.repository.DocumentRepository;
import com.documentcba.doc.service.DocumentService;
import com.documentcba.doc.utils.SHAUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentServiceImple implements DocumentService {


    private DocumentRepository documentRepository;

    public DocumentServiceImple(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public DocumentCreatedResponseDTO saveDocuments(List<MultipartFile> files, String hashType) throws NoSuchAlgorithmException, IOException {

        List<DocumentCreatedDTO> dtos = new ArrayList<>();
        DocumentCreatedResponseDTO response = new DocumentCreatedResponseDTO();

        for(MultipartFile file: files){

            if(file.isEmpty() || !SHAUtil.isValidSha(hashType)){
                throw new FileNotCreated();
            }

            String sha256 = SHAUtil.toSHA256String(file);
            String sha512 = SHAUtil.toSHA512String(file);

            DocumentModel documentModel =
                            documentRepository.existsByHashSha256OrHashSha512(sha256,sha512) ?
                            documentRepository.findByHashSha256OrHashSha512(sha256,sha512) :
                            new DocumentModel();

            DocumentCreatedDTO dto = new DocumentCreatedDTO();

            documentModel.setFileName(file.getOriginalFilename());
            documentModel.setHashSha256(sha256);
            documentModel.setHashSha512(sha512);

            if(documentRepository.existsByHashSha256OrHashSha512(sha256, sha512)){
                LocalDateTime date = LocalDateTime.now();
                documentModel.setLastUpload(date);
            }

            DocumentModel docSaved = documentRepository.save(documentModel);
            dto.setLastUpload(docSaved.getLastUpload());
            dto.setFileName(docSaved.getFileName());

            if(hashType.equalsIgnoreCase("SHA-256")){
                dto.setHash(sha256);
            } else if (hashType.equalsIgnoreCase("SHA-512")) {
                dto.setHash(sha512);
            }

            dtos.add(dto);

        }

        response.setAlgorithms(hashType.toUpperCase());
        response.setDocuments(dtos);

        return response;

    }

    @Override
    public List<DocumentDTO> getAllDocument() {
        List<DocumentModel> documentModels = documentRepository.findAll();

        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentModel doc : documentModels ){
            DocumentDTO documentDTO = new DocumentDTO();
            documentDTO.setFileName(doc.getFileName());
            documentDTO.setHashSha256(doc.getHashSha256());
            documentDTO.setHashSha512(doc.getHashSha512());
            documentDTO.setLastUpload(doc.getLastUpload());
            documentDTOS.add(documentDTO);
        }

        return documentDTOS;
    }

    @Override
    public DocumentOneHashDTO getDocumentByHash(String hashType, String hash) {

        DocumentModel documentModel;
        DocumentOneHashDTO dto = new DocumentOneHashDTO();

        if(!SHAUtil.isValidSha(hashType)){
            throw new ParamNotFound("hashType");
        }

        if(hashType.equalsIgnoreCase("SHA-256")){
            documentModel = documentRepository.findDocumentHash256(hash);
            dto.setHash(documentModel.getHashSha256());
        }else if(hashType.equalsIgnoreCase("SHA-512")){
            documentModel = documentRepository.findDocumentHash512(hash);
            dto.setHash(documentModel.getHashSha512());
        }else{
            throw new DocumentNotFound();
        }



        dto.setFileName(documentModel.getFileName());
        dto.setHash(documentModel.getHashSha256());
        dto.setLastUpload(documentModel.getLastUpload());

        return dto;
    }
}
