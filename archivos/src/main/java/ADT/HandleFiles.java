/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edanP
 */
public class HandleFiles {

    public int conteo() {
        File desc_bitacora_usuario = new File("C:\\MEIA\\desc_bitacora_usuario.txt");
        ArrayList desc = ReadBitacoraDesc(desc_bitacora_usuario);
        String[] aux;
        aux = desc.get(5).toString().split(" ");
        return Integer.parseInt(aux[1]);
    }

    private void HandleBitacora(Usuario usuario) {
        File bitacora_usuario = new File("C:\\MEIA\\bitacora_usuario.txt");
        File desc_bitacora_usuario = new File("C:\\MEIA\\desc_bitacora_usuario.txt");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String fecha = dtf.format(now);

        // verificar archivo desc_bitacora_usuario.txt para registro
        try {
            if (desc_bitacora_usuario.createNewFile()) { // NO EXISTE EL ARCHIVO
                System.out.println("File created: " + desc_bitacora_usuario.getName());

                // crear descriptor
                PrintWriter descWriter = new PrintWriter(desc_bitacora_usuario);
                descWriter.print("nombre_simbolico: bitacora_usuario\n"
                        + "fecha_creacion: " + fecha + "\n"
                        + "usuario_creacion: " + usuario.getUsuario() + "\n"
                        + "fecha_modificacion: " + fecha + "\n"
                        + "usuario_modificacion: " + usuario.getUsuario() + "\n"
                        + "#_registros: 1\n"
                        + "registros_activos: 1\n"
                        + "registros_inactivos: 0\n"
                        + "max_reorganizacion: 5");
                descWriter.close();

                // insertar en archivo bitacora_usuario.txt para registro
                try {
                    FileWriter writer = new FileWriter(bitacora_usuario, true);
                    BufferedWriter bw = new BufferedWriter(writer);
                    bw.write(usuario.toString() + System.getProperty("line.separator"));
                    bw.close();
                    writer.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            } else { // YA EXISTE EL ARCHIVO
                System.out.println("File already exists: " + desc_bitacora_usuario.getName());
                ArrayList desc = ReadBitacoraDesc(desc_bitacora_usuario);
                String[] aux;

                // validar el max de reorganización
                aux = desc.get(5).toString().split(" ");
                int cont = Integer.parseInt(aux[1]);

                // insertar en bitacora usuario
                if (cont < 5) {
                    //actualizar campos
                    desc.set(3, "fecha_modificacion: " + fecha);

                    // actualizar usuario modificacion
                    desc.set(4, "usuario_modificacion: " + usuario.getUsuario());

                    // actualizar conteo                
                    aux = desc.get(5).toString().split(" ");
                    aux[1] = String.valueOf(Integer.parseInt(aux[1]) + 1);
                    desc.set(5, aux[0] + " " + aux[1]); // numero de registros

                    aux = desc.get(6).toString().split(" ");
                    aux[1] = String.valueOf(Integer.parseInt(aux[1]) + 1);
                    desc.set(6, aux[0] + " " + aux[1]); // numero de registros activos                    

                    PrintWriter descWriter = new PrintWriter(desc_bitacora_usuario);
                    for (int i = 0; i < 9; i++) {
                        descWriter.println(desc.get(i).toString());
                    }
                    descWriter.close();

                    // insertar en archivo bitacora_usuario.txt para registro
                    try {
                        FileWriter writer = new FileWriter(bitacora_usuario, true);
                        BufferedWriter bw = new BufferedWriter(writer);
                        bw.write(usuario.toString() + System.getProperty("line.separator"));
                        bw.close();
                        writer.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                } else { // insertar en usuario

                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private ArrayList ReadBitacoraDesc(File input) {
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

    private void HandleUsuario(String username) {
        File usuario = new File("C:\\MEIA\\usuario.txt");
        File desc_usuario = new File("C:\\MEIA\\desc_usuario.txt");
        // verificar archivo usuario.txt para login
        try {
            if (usuario.createNewFile()) {
                System.out.println("File created: " + usuario.getName());
            } else {
                System.out.println("File already exists: " + usuario.getName());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // verificar archivo desc_usuario.txt para login
        try {
            if (desc_usuario.createNewFile()) {
                System.out.println("File created: " + desc_usuario.getName());

            } else {
                System.out.println("File already exists: " + desc_usuario.getName());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeUser(Usuario input) {
        HandleBitacora(input);
    }

}
