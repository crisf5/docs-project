package com.documentcba.doc.utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil {

    public static boolean isValidSha(String hashType) {
        return (hashType.equalsIgnoreCase("sha-256") || hashType.equalsIgnoreCase("sha-512"));
    }

    public static String toSHA256String(MultipartFile file) throws NoSuchAlgorithmException, IOException  {

        MessageDigest SHA256Digest = MessageDigest.getInstance("sha-256");

        byte[] messageDigest = SHA256Digest.digest(file.getBytes());

        BigInteger bigInteger = new BigInteger(messageDigest);

        return bigInteger.toString();
    }

    public static String toSHA512String(MultipartFile file) throws  NoSuchAlgorithmException, IOException{
        MessageDigest SHA256Digest = MessageDigest.getInstance("sha-512");

        byte[] messageDigest = SHA256Digest.digest(file.getBytes());

        BigInteger bigInteger = new BigInteger(messageDigest);

        return bigInteger.toString();
    }


}
