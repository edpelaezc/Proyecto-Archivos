/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Maneja los archivos para ingreso de usuarios.
 *
 * @author edanP
 */
public class HandleFiles {

    File usuario = new File("C:\\MEIA\\usuario.txt");
    File desc_usuario = new File("C:\\MEIA\\desc_usuario.txt");
    File bitacora_usuario = new File("C:\\MEIA\\bitacora_usuario.txt");
    File desc_bitacora_usuario = new File("C:\\MEIA\\desc_bitacora_usuario.txt");

    /**
     * Conteo de usuarios. Utilizado para validar el ingreso del administrador y
     * de usuarios comunes.
     *
     * @return
     */
    public boolean conteo() {
        ArrayList bitacora = ReadFile(bitacora_usuario);
        ArrayList aux_usuario = ReadFile(usuario);

        return bitacora.isEmpty() && aux_usuario.isEmpty();
    }

    /**
     * Maneja la escritura en el archivo bitacora usuario, si excede el máximo
     * de reorganización este método ejecutará HandleUsuario, para trasladar los
     * datos.
     *
     * @param usuario Usuario que se insertará.
     */
    private void HandleBitacora(Usuario usuario) {
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
                    HandleUsuario(usuario.getUsuario(), fecha);

                    // insertar en bitácora el nuevo
                    PrintWriter writer = new PrintWriter(bitacora_usuario);
                    writer.print("");
                    writer.close();

                    // actualizar desc bitacora                    
                    desc.set(3, "fecha_modificacion: " + fecha);

                    desc.set(4, "usuario_modificacion: " + usuario.getUsuario());
                    desc.set(5, "registros_activos: 0"); // numero de registros
                    desc.set(6, "registros_activos: 0"); // numero de registros activos 

                    PrintWriter descWriter = new PrintWriter(desc_bitacora_usuario);
                    for (int i = 0; i < 9; i++) {
                        descWriter.println(desc.get(i).toString());
                    }
                    descWriter.close();

                    HandleBitacora(usuario);
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Lee el archivo indicado
     *
     * @param input Archivo a leer
     * @return Arraylist que contiene las lineas del archivo.
     */
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

    /**
     * Maneja la escritura de usuarios, puede crear o actualizar tanto el
     * descriptor como el archivo de datos de usuario.
     *
     * @param username Usuario que modifica el archivo.
     * @param fecha Fecha de modificación del archivo.
     */
    private void HandleUsuario(String username, String fecha) {

        ArrayList bitacora = ReadFile(bitacora_usuario); // usuarios en bitacora
        ArrayList aux_usuario = ReadFile(usuario); // usuarios en usuario.txt
        ArrayList<Usuario> allUsers = new ArrayList<Usuario>();

        // usuarios en bitacora
        for (var item : bitacora) {
            System.out.println(item.toString());
            allUsers.add(createUsuario(item.toString()));
        }

        // usuarios en usuario.txt
        for (int i = 0; i < aux_usuario.size(); i++) {
            allUsers.add(createUsuario(aux_usuario.get(i).toString()));
        }

        Collections.sort(allUsers);

        try {
            // crear o actulizar
            if (desc_usuario.createNewFile()) {
                PrintWriter descWriter = new PrintWriter(desc_usuario);
                descWriter.print("nombre_simbolico: usuario\n"
                        + "fecha_creacion: " + fecha + "\n"
                        + "usuario_creacion: " + username + "\n"
                        + "fecha_modificacion: " + fecha + "\n"
                        + "usuario_modificacion: " + username + "\n"
                        + "#_registros: 5\n"
                        + "registros_activos: 5\n"
                        + "registros_inactivos: 0\n");
                descWriter.close();
            } else {
                ArrayList desc = ReadFile(desc_usuario);
                String[] aux;

                //actualizar campos
                desc.set(3, "fecha_modificacion: " + fecha);

                // actualizar usuario modificacion
                desc.set(4, "usuario_modificacion: " + username);

                // actualizar conteo                
                aux = desc.get(5).toString().split(" ");
                aux[1] = String.valueOf(Integer.parseInt(aux[1]) + 5);
                desc.set(5, aux[0] + " " + aux[1]); // numero de registros                  

                PrintWriter descWriter = new PrintWriter(desc_usuario);
                for (int i = 0; i < 8; i++) {
                    descWriter.println(desc.get(i).toString());
                }
                descWriter.close();
            }

            // escribir usuario ordenados por su clave única
            PrintWriter userWriter = new PrintWriter(usuario);
            for (int i = 0; i < allUsers.size(); i++) {
                userWriter.println(allUsers.get(i).toString());
            }
            userWriter.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método para crear Usuarios
     *
     * @param line Linea contenida en el archivo, separada por "|"
     * @return Usuario con sus parámetros inicializados.
     */
    private Usuario createUsuario(String line) {
        String[] fields = line.split("\\|");
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

    /**
     * Escribe un registro en bitacora o en usuario dependiendo del máximo de
     * reorganización del archivo de bitacora.
     *
     * @param input Usuario a escribir
     */
    public void writeUser(Usuario input) {
        HandleBitacora(input);
    }

    /**
     * Verifica que la llave única del registro a insertar no sea repetida.
     *
     * @param username Llave única.
     * @return true si se puede insertar ese registro; false si la llave está
     * repetida.
     */
    public boolean uniqueKey(String username) {

        ArrayList bitacora = ReadFile(bitacora_usuario); // usuarios en bitacora
        ArrayList aux_usuario = ReadFile(usuario); // usuarios en usuario.txt

        for (var object : aux_usuario) {
            if (object.toString().startsWith(username)) {
                return false;
            }
        }

        for (var object : bitacora) {
            if (object.toString().startsWith(username)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica si el usuario y contraseña son correctos y permite el acceso al
     * usuario.
     *
     * @param username Nombre de usuario
     * @param password Contraseña. (Se cifra para hacer la comparación)
     * @return Usuario inicializado si existe, de lo contrario null
     */
    public Usuario login(String username, String password) {

        ArrayList bitacora = ReadFile(bitacora_usuario); // usuarios en bitacora
        ArrayList aux_usuario = ReadFile(usuario); // usuarios en usuario.txt

        for (var object : aux_usuario) {
            if (object.toString().contains(username) && object.toString().contains(password)) {
                return createUsuario(object.toString());
            }
        }

        for (var object : bitacora) {
            if (object.toString().contains(username) && object.toString().contains(password)) {
                return createUsuario(object.toString());
            }
        }
        
        return null;
    }

}
