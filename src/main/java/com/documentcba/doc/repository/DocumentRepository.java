package com.documentcba.doc.repository;

import com.documentcba.doc.model.DocumentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentModel, Long> {

    @Query("SELECT d FROM DocumentModel d WHERE :hash = d.hashSha256")
    public DocumentModel findDocumentHash256(@Param("hash") String hash);

    @Query("SELECT d FROM DocumentModel d WHERE :hash = d.hashSha512")
    public DocumentModel findDocumentHash512(@Param("hash") String hash);

    public DocumentModel findByHashSha256OrHashSha512(String hashSha256, String hashSha512);
    public Boolean existsByHashSha256OrHashSha512(String hashSha256, String hashSha512);


}
