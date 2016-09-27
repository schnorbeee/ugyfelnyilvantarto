package com.codingmentorteam3.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class UtilBean {

    public UtilBean() {
        //Default constructor
    }
    
    public String sha256coding(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(str.getBytes());
        byte byteData[] = md.digest();
        StringBuilder newPassword = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            newPassword.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return newPassword.toString();
    }
    
}
