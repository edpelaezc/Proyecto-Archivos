/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author llaaj
 */
public class Receptor {
    
    /***
     * Retorna verdadero si un usuario o lista existe
     * @param user
     * @return 
     */
    public Boolean userExists(String user){
        try {
            File fUsuario = new File("C:\\MEIA\\usuario.txt");
            File fBitacoraUsuario = new File("C:\\MEIA\\usuario.txt");
            File fLista = new File("C:\\MEIA\\lista.txt");
            
            Scanner scan = new Scanner(fUsuario);
            while (scan.hasNextLine()){
                String[] data = scan.nextLine().split("\\|");
                if (data[0].equals(user)) {
                    return true;
                }
            }
            
            scan = new Scanner(fBitacoraUsuario);
            while (scan.hasNextLine()){
                String[] data = scan.nextLine().split("\\|");
                if (data[0].equals(user)) {
                    return true;
                }
            }
            
            scan = new Scanner(fLista);
            while (scan.hasNextLine()){
                String[] data = scan.nextLine().split("\\|");
                if (data[1].equals(user)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }
    
    /***
     * Retorna verdadero si es un usuario
     * falo si es una lista
     * nulo si no fue encontrado
     * @param userOrList
     * @return 
     */
    public Boolean isUserOrList(String userOrList){
        try {
            File fUsuario = new File("C:\\MEIA\\usuario.txt");
            File fBitacoraUsuario = new File("C:\\MEIA\\usuario.txt");
            File fLista = new File("C:\\MEIA\\lista.txt");
            
            Scanner scan = new Scanner(fUsuario);
            while (scan.hasNextLine()){
                String[] data = scan.nextLine().split("\\|");
                if (data[0].equals(userOrList)) {
                    return true;
                }
            }
            
            scan = new Scanner(fBitacoraUsuario);
            while (scan.hasNextLine()){
                String[] data = scan.nextLine().split("\\|");
                if (data[0].equals(userOrList)) {
                    return true;
                }
            }
            
            scan = new Scanner(fLista);
            while (scan.hasNextLine()){
                String[] data = scan.nextLine().split("\\|");
                if (data[1].equals(userOrList)) {
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
    
    public Boolean userHasList(String list, String user){
        try {
            File fBloque = new File("C:\\MEIA\\bloque_usuario.txt");
            Scanner scan = new Scanner(fBloque);
            
            while (scan.hasNextLine()){
                String[] data = scan.nextLine().split("\\|");
                String[] key = data[0].split(",");
                if (key[0].equals(list) && key[1].equals(user)) {
                        return true; // hay coincidencia con el usuario y la lista
                }
            }
            
            return false;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<String> getUsersFromList(String list){
        try {
            File fBloque = new File("C:\\MEIA\\bloque_usuario.txt");
            Scanner scan = new Scanner(fBloque);
            
            ArrayList<String> userList = new ArrayList<String>();
            while (scan.hasNextLine()){
                String[] data = scan.nextLine().split("\\|");
                String[] key = data[0].split(",");
                
                if (key[0].equals(list) && data[3].equals("1")) {
                    
                        userList.add(key[2]); // agregar usuario asociado
                }
            }
            
            return userList;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
}
