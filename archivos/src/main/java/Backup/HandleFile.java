/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backup;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Access.Usuario;
import Backup.Backup;
import java.util.ArrayList;

/**
 *
 * @author llaaj
 */
public class HandleFile {
    File bitacora_backup = new File("C:\\MEIA\\bitacora_backup.txt");
    File desc_bitacora_backup = new File("C:\\MEIA\\desc_bitacora_backup.txt");
    
    /**
     * HandleFile constructor
     */
    public HandleFile(){
    }
    
    /**
     * Crea o modifica el archivo desc_bitacora_backup e inserta los registros
     * en el archivo bitacora_backup
     * @param usuario nombre del usuario administrador
     * @param ruta_absoluta ruta
     */
    public void HandleBitacora(String usuario, String ruta_absoluta){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String fecha = dtf.format(now);
        Backup backup = new Backup(ruta_absoluta, usuario, fecha);
        try {
            if (desc_bitacora_backup.createNewFile()) {
                PrintWriter descWriter = new PrintWriter(desc_bitacora_backup);
                descWriter.print(
                        "nombre_simbolico: bitacora_usuario\n"
                        + "fecha_creacion: " + fecha + "\n"
                        + "usuario_creacion: " + usuario + "\n"
                        + "fecha_modificacion: " + fecha + "\n"
                        + "usuario_modificacion: " + usuario + "\n"
                        + "#_registros: 1\n");
                descWriter.close();
                
                FileWriter writer = new FileWriter(bitacora_backup, true);
                BufferedWriter bw = new BufferedWriter(writer);
                bw.write(backup.toString() + System.getProperty("line.separator"));
                bw.close();
                writer.close();
            } else {
                System.out.println("File already exists: " + desc_bitacora_backup.getName());
                ArrayList desc = ReadFile(desc_bitacora_backup);
                
                // actualizar fecha
                desc.set(3, "fecha_modificacion: " + fecha);
                // actualizar usuario modificacion
                desc.set(4, "usuario_modificacion: " + usuario);
                
                // aumentar el número de registros
                String[] aux = desc.get(5).toString().split(" ");
                int registros = Integer.parseInt(aux[1]);
                registros++;
                // actualizar registro
                desc.set(5, "#_registros: " + registros);
                
                // modificar archivo
                PrintWriter descWriter = new PrintWriter(desc_bitacora_backup);
                for (int i = 0; i < desc.size(); i++) {
                    descWriter.println(desc.get(i).toString());
                }
                descWriter.close();
                    
                FileWriter writer = new FileWriter(bitacora_backup, true);
                BufferedWriter bw = new BufferedWriter(writer);
                bw.write(backup.toString() + System.getProperty("line.separator"));
                bw.close();
                writer.close();
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    private ArrayList ReadFile(File input) {
        FileReader lectura;
        ArrayList response = new ArrayList();

        try {
            //crear el lector
            lectura = new FileReader(input);
            BufferedReader reader = new BufferedReader(lectura);
            String linea = "";

            try {
                linea = reader.readLine();

                while (linea != null) {
                    if (!"".equals(linea)) {
                        response.add(linea);
                    }
                    linea = reader.readLine();
                }

                lectura.close();
                reader.close();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            // archivo no encontrado
            System.out.println(ex.getMessage());
        }
        return response;
    }
}
