/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backup;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ADT.Usuario;
import Backup.Backup;

/**
 *
 * @author llaaj
 */
public class HandleFile {
    File bitacora_backup = new File("C:\\MEIA\\bitacora_backup");
    File desc_bitacora_backup = new File("C:\\MEIA\\desc_bitacora_backup");
    
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
}
