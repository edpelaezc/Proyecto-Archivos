/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author llaaj
 */
public class Receptor {
    
    public Boolean userExists(String user){
        try {
            File fUsuario = new File("C:\\MEIA\\usuario.txt");
            File fBitacoraUsuario = new File("C:\\MEIA\\usuario.txt");
            File fLista = new File("C:\\MEIA\\lista.txt");
            
            Scanner scan = new Scanner(fUsuario);
            while (scan.hasNextLine()){
                String[] data = scan.nextLine().split("\\|");
                if (data[0] == user) {
                    return true;
                }
            }
            
            scan = new Scanner(fBitacoraUsuario);
            while (scan.hasNextLine()){
                String[] data = scan.nextLine().split("\\|");
                if (data[0] == user) {
                    return true;
                }
            }
            
            scan = new Scanner(fLista);
            while (scan.hasNextLine()){
                String[] data = scan.nextLine().split("\\|");
                if (data[1] == user) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }
}
