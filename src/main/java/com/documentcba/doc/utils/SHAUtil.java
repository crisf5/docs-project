package com.documentcba.doc.utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil {

    public static boolean isValidSha(String hashType) {
        return (hashType.equalsIgnoreCase("SHA-256") || hashType.equalsIgnoreCase("SHA-512"));
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String toSHA256String(MultipartFile file) throws NoSuchAlgorithmException, IOException  {

        MessageDigest SHA256Digest = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = SHA256Digest.digest(file.getBytes());

        String toHex = bytesToHex(messageDigest);

        return toHex;
    }

    public static String toSHA512String(MultipartFile file) throws  NoSuchAlgorithmException, IOException{

        MessageDigest SHA256Digest = MessageDigest.getInstance("SHA-512");
        byte[] messageDigest = SHA256Digest.digest(file.getBytes());

        String toHex = bytesToHex(messageDigest);

        return toHex;
    }

}
