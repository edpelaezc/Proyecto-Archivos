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
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edanP
 */
public class HandleFiles {

    public boolean conteo() {
        File bitacora_usuario = new File("C:\\MEIA\\bitacora_usuario.txt");
        File usuario = new File("C:\\MEIA\\usuario.txt");
        ArrayList bitacora = ReadFile(bitacora_usuario);
        ArrayList aux_usuario = ReadFile(usuario);
                        
        return bitacora.isEmpty() && aux_usuario.isEmpty();
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
                ArrayList desc = ReadFile(desc_bitacora_usuario);
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

                } else {
                    // insertar en usuario todo lo que hay en bitácora                     
                    HandleUsuario(usuario.getUsuario());

                    // insertar en bitácora el nuevo
                    PrintWriter writer = new PrintWriter(bitacora_usuario);
                    writer.print("");
                    writer.close();
                    desc.set(6, "registros_activos: 0"); // numero de registros activos 
                    HandleBitacora(usuario);
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
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

    private void HandleUsuario(String username) {
        File usuario = new File("C:\\MEIA\\usuario.txt");
        File desc_usuario = new File("C:\\MEIA\\desc_usuario.txt");
        File bitacora_usuario = new File("C:\\MEIA\\bitacora_usuario.txt");

        ArrayList bitacora = ReadFile(bitacora_usuario); // usuarios en bitacora
        ArrayList aux_usuario = ReadFile(usuario); // usuarios en usuario.txt
        ArrayList allUsers = new ArrayList();

        // usuarios en bitacora
        for (int i = 0; i < bitacora.size(); i++) {
            allUsers.add(createUsuario(bitacora.get(i).toString()));
        }

        // usuarios en usuario.txt
        for (int i = 0; i < aux_usuario.size(); i++) {
            allUsers.add(createUsuario(aux_usuario.get(i).toString()));
        }

        Collections.sort(allUsers);

        // escribir usuario ordenados por su clave única
        try {
            PrintWriter userWriter = new PrintWriter(usuario);
            for (int i = 0; i < allUsers.size(); i++) {
                userWriter.println(allUsers.get(i).toString());
            }
            userWriter.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private Usuario createUsuario(String line) {
        String[] fields = line.split("|");
        return new Usuario(
                fields[0],
                fields[1],
                fields[2],
                fields[3],
                Integer.parseInt(fields[4]),
                fields[5],
                fields[6],
                fields[7],
                fields[8],
                Integer.parseInt(fields[9])
        );
    }

    public void writeUser(Usuario input) {
        HandleBitacora(input);
    }

}
