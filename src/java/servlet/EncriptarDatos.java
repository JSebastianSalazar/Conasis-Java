/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * @author SEBASTIAN SALAZAR
 * @fecha 08/04/2016
 */
public class EncriptarDatos {

    private MessageDigest md;
    private byte[] bytesdeDatos;
    private StringBuffer sb;

    public String cifrarMD5(String clave) {
        // BigInteger numero;
        // String hashtext;

        try {
            // Primera forma:                        
            md = MessageDigest.getInstance("MD5");
            md.update(clave.getBytes());

            bytesdeDatos = md.digest();

            //convert the byte to hex format
            //convierte el byte a formato hexadecimal
            sb = new StringBuffer();
            for (int i = 0; i < bytesdeDatos.length; i++) {
                sb.append(Integer.toString((bytesdeDatos[i] & 0xff) + 0x100, 16).substring(1));
            }

            /*
             // Segunda forma
             md.reset();
             md.update(clave.getBytes());
             bytesdeDatos = md.digest();
             numero = new BigInteger(1, bytesdeDatos);
             hashtext = numero.toString(16);
            
             // Now we need to zero pad it if you actually want the full 32 chars.
            
             while (hashtext.length() < 32) {
             hashtext = "0" + hashtext;
             } */
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.toString());
        }

        return "";
    } // fin método cifrarMD5 (tipo 4)

  
}
