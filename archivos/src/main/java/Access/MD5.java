/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase para encriptar la contraseña de los usuarios. 
 * @author edanP
 */
public class MD5 {

    /**
     * Encriptar la contraseña usando MD5
     *
     * @param input Cadena que representa la contraseña.
     * @return Cadena con la contraseña cifrada.
     */
    public static String encryptPassword(String input) {

        try {
            // Instanciar md5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest password
            md.update(input.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();

            // to hex
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            //Get complete hashed password in hex format            
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return e.getMessage();
        }
    }

}
