package Contacts;

import Contacts.AdminContactos;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import Access.HandleFiles;
import Access.Usuario;
import Contacts.Contacto;

public class HandleContacts {

    File contactos = new File("C:\\MEIA\\contactos.txt");
    File desc_contactos = new File("C:\\MEIA\\desc_contactos.txt");
    File bitacora_contactos = new File("C:\\MEIA\\bitacora_contactos.txt");
    File desc_bitacora_contactos = new File("C:\\MEIA\\desc_bitacora_contactos.txt");
    int maximo = 2;

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

    private void HandleBitacora(Contacto contacto) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String fecha = dtf.format(now);

        // verificar archivo desc_bitacora_usuario.txt para registro
        try {
            if (desc_bitacora_contactos.createNewFile()) { // NO EXISTE EL ARCHIVO
                System.out.println("File created: " + desc_bitacora_contactos.getName());

                // crear descriptor
                PrintWriter descWriter = new PrintWriter(desc_bitacora_contactos);
                descWriter.print("nombre_simbolico: bitacora_contactos\n"
                        + "fecha_creacion: " + fecha + "\n"
                        + "usuario_creacion: " + contacto.getUsuario() + "\n"
                        + "fecha_modificacion: " + fecha + "\n"
                        + "usuario_modificacion: " + contacto.getUsuario() + "\n"
                        + "#_registros: 1\n"
                        + "registros_activos: 1\n"
                        + "registros_inactivos: 0\n"
                        + "max_reorganizacion: " + maximo);
                descWriter.close();

                // insertar en archivo bitacora_usuario.txt para registro
                try {
                    FileWriter writer = new FileWriter(bitacora_contactos, true);
                    BufferedWriter bw = new BufferedWriter(writer);
                    bw.write(contacto.toString() + System.getProperty("line.separator"));
                    bw.close();
                    writer.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            } else { // YA EXISTE EL ARCHIVO
                System.out.println("File already exists: " + desc_bitacora_contactos.getName());
                ArrayList desc = ReadFile(desc_bitacora_contactos);
                String[] aux;

                // validar el max de reorganización
                aux = desc.get(5).toString().split(" ");
                int cont = Integer.parseInt(aux[1]);

                // insertar en bitacora usuario
                if (cont < maximo) {
                    //actualizar campos
                    desc.set(3, "fecha_modificacion: " + fecha);

                    // actualizar usuario modificacion
                    desc.set(4, "usuario_modificacion: " + contacto.getUsuario());

                    // actualizar conteo
                    aux = desc.get(5).toString().split(" ");
                    aux[1] = String.valueOf(Integer.parseInt(aux[1]) + 1);
                    desc.set(5, aux[0] + " " + aux[1]); // numero de registros

                    aux = desc.get(6).toString().split(" ");
                    aux[1] = String.valueOf(Integer.parseInt(aux[1]) + 1);
                    desc.set(6, aux[0] + " " + aux[1]); // numero de registros activos

                    PrintWriter descWriter = new PrintWriter(desc_bitacora_contactos);
                    for (int i = 0; i < 9; i++) {
                        descWriter.println(desc.get(i).toString());
                    }
                    descWriter.close();

                    // insertar en archivo bitacora_usuario.txt para registro
                    try {
                        FileWriter writer = new FileWriter(bitacora_contactos, true);
                        BufferedWriter bw = new BufferedWriter(writer);
                        bw.write(contacto.toString() + System.getProperty("line.separator"));
                        bw.close();
                        writer.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                } else {
                    // insertar en usuario todo lo que hay en bitácora
                    HandleUsuario(contacto, fecha);

                    // insertar en bitácora el nuevo
                    PrintWriter writer = new PrintWriter(bitacora_contactos);
                    writer.print("");
                    writer.close();

                    // actualizar desc bitacora
                    desc.set(3, "fecha_modificacion: " + fecha);

                    desc.set(4, "usuario_modificacion: " + contacto.getUsuario());
                    desc.set(5, "registros_activos: 0"); // numero de registros
                    desc.set(6, "registros_activos: 0"); // numero de registros activos

                    PrintWriter descWriter = new PrintWriter(desc_bitacora_contactos);
                    for (int i = 0; i < 9; i++) {
                        descWriter.println(desc.get(i).toString());
                    }
                    descWriter.close();

                    HandleBitacora(contacto);
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Contacto createContacto(String line) {
        String[] fields = line.split("\\|");
        return new Contacto(
                fields[0],
                fields[1],
                fields[2],
                fields[3],
                Integer.parseInt(fields[4])
        );
    }

    private void HandleUsuario(Contacto contacto, String fecha) {

        ArrayList bitacora = ReadFile(bitacora_contactos); // usuarios en bitacora
        ArrayList aux_usuario = ReadFile(contactos); // usuarios en usuario.txt
        ArrayList<Contacto> allUsers = new ArrayList<Contacto>();

        // usuarios en bitacora
        for (var item : bitacora) {
            System.out.println(item.toString());
            allUsers.add(createContacto(item.toString()));
        }

        // usuarios en usuario.txt
        for (int i = 0; i < aux_usuario.size(); i++) {
            allUsers.add(createContacto(aux_usuario.get(i).toString()));
        }

        Collections.sort(allUsers);

        try {
            // crear o actulizar
            if (desc_contactos.createNewFile()) {
                PrintWriter descWriter = new PrintWriter(desc_contactos);
                descWriter.print("nombre_simbolico: contactos\n"
                        + "fecha_creacion: " + fecha + "\n"
                        + "usuario_creacion: " + contacto.getUsuario() + "\n"
                        + "fecha_modificacion: " + fecha + "\n"
                        + "usuario_modificacion: " + contacto.getUsuario() + "\n"
                        + "#_registros: " + maximo + "\n"
                        + "registros_activos: " + maximo + "\n"
                        + "registros_inactivos: 0\n");
                descWriter.close();
            } else {
                ArrayList desc = ReadFile(desc_contactos);
                String[] aux;

                //actualizar campos
                desc.set(3, "fecha_modificacion: " + fecha);

                // actualizar usuario modificacion
                desc.set(4, "usuario_modificacion: " + contacto.getUsuario());

                // actualizar conteo
                aux = desc.get(5).toString().split(" ");
                aux[1] = String.valueOf(Integer.parseInt(aux[1]) + maximo);
                desc.set(5, aux[0] + " " + aux[1]); // numero de registros
                
                // actualizar conteo
                aux = desc.get(6).toString().split(" ");
                aux[1] = String.valueOf(Integer.parseInt(aux[1]) + maximo);
                desc.set(6, aux[0] + " " + aux[1]); // numero de registros                

                PrintWriter descWriter = new PrintWriter(desc_contactos);
                for (int i = 0; i < 8; i++) {
                    descWriter.println(desc.get(i).toString());
                }
                descWriter.close();
            }

            // escribir usuario ordenados por su clave única
            PrintWriter userWriter = new PrintWriter(contactos);
            for (int i = 0; i < allUsers.size(); i++) {
                userWriter.println(allUsers.get(i).toString());
            }
            userWriter.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean uniqueKey(String username, String contact) {

        ArrayList bitacora = ReadFile(bitacora_contactos); // usuarios en bitacora
        ArrayList aux_usuario = ReadFile(contactos); // usuarios en usuario.txt

        for (var object : aux_usuario) {
            String[] fields = object.toString().split("\\|");
            if (fields[0].contains(username) && fields[1].contains(contact)) {
                return false;
            }
        }

        for (var object : bitacora) {
            String[] fields = object.toString().split("\\|");
            if (fields[0].contains(username) && fields[1].contains(contact)) {
                return false;
            }
        }
        return true;
    }

    public Usuario search(String searchString) {

        File usuario = new File("C:\\MEIA\\usuario.txt");
        File bitacora_usuario = new File("C:\\MEIA\\bitacora_usuario.txt");
        ArrayList<String> aux_usuario = ReadFile(usuario);
        ArrayList<String> l_bitacora_usuario = ReadFile(bitacora_usuario);
        ArrayList<String> ct = ReadFile(contactos);
        ArrayList<String> bt = ReadFile(bitacora_contactos);

        // para agregar
        for (var object : aux_usuario) {
            if (object.contains(searchString)) {
                return createUsuario(object);
            }
        }

        for (var object : l_bitacora_usuario) {
            if (object.contains(searchString)) {
                return createUsuario(object);
            }
        }

        for (var object : ct) {
            String[] fields = object.split("\\|");
            if (fields[1].contains(searchString)) {
                for (var item : aux_usuario) {
                    return createUsuario(item);
                }
            }
        }

        for (var object : bt) {
            String[] fields = object.split("\\|");
            if (fields[1].contains(searchString)) {
                for (var item : l_bitacora_usuario) {
                    return createUsuario(item);
                }
            }
        }

        return null;
    }

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

    public void writeContact(Contacto input) {
        HandleBitacora(input);
    }

    public boolean removeContact(String usuario, String contacto) {
        ArrayList bitacora = ReadFile(bitacora_contactos); // usuarios en bitacora
        ArrayList aux_contactos = ReadFile(contactos); // usuarios en usuario.txt        
        boolean bita = false;
        boolean maestro = false;
        String match = "";

        // usuarios en bitacora
        for (var item : bitacora) {
            if (item.toString().contains(usuario) && item.toString().contains(contacto)) {
                match = item.toString();
                bita = true;
            }
        }

        // usuarios en maestro
        for (var item : aux_contactos) {
            if (item.toString().contains(usuario) && item.toString().contains(contacto)) {
                match = item.toString();
                maestro = true;
            }
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String fecha = dtf.format(now);

        // eliminar registro de bitacora
        if (bita) {            
            
            ArrayList desc = ReadFile(desc_bitacora_contactos);
            String[] aux;

            //actualizar campos
            desc.set(3, "fecha_modificacion: " + fecha);

            // actualizar usuario modificacion
            desc.set(4, "usuario_modificacion: " + usuario);

            // actualizar conteo
            aux = desc.get(5).toString().split(" ");
            aux[1] = String.valueOf(Integer.parseInt(aux[1]) - 1);
            desc.set(5, aux[0] + " " + aux[1]); // numero de registros  
            
            // actualizar conteo
            aux = desc.get(6).toString().split(" ");
            aux[1] = String.valueOf(Integer.parseInt(aux[1]) - 1);
            desc.set(6, aux[0] + " " + aux[1]); // numero de registros   
            
            
            try {
                
                // descriptor contactos
                PrintWriter descWriter = new PrintWriter(desc_bitacora_contactos);
                for (int i = 0; i < 9; i++) {
                    descWriter.println(desc.get(i).toString());
                }
                descWriter.close();
                
                // escribir usuario ordenados por su clave única
                PrintWriter userWriter = new PrintWriter(bitacora_contactos);
                for (int i = 0; i < bitacora.size(); i++) {
                    if (bitacora.get(i).toString() != match) {
                        userWriter.println(bitacora.get(i).toString());
                    }
                }
                userWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;

        } else if (maestro) {

            ArrayList desc = ReadFile(desc_contactos);
            String[] aux;

            //actualizar campos
            desc.set(3, "fecha_modificacion: " + fecha);

            // actualizar usuario modificacion
            desc.set(4, "usuario_modificacion: " + usuario);

            // actualizar conteo
            aux = desc.get(5).toString().split(" ");
            aux[1] = String.valueOf(Integer.parseInt(aux[1]) - 1);
            desc.set(5, aux[0] + " " + aux[1]); // numero de registros   
            
            // actualizar conteo
            aux = desc.get(6).toString().split(" ");
            aux[1] = String.valueOf(Integer.parseInt(aux[1]) - 1);
            desc.set(6, aux[0] + " " + aux[1]); // numero de registros              

            try {
                // descriptor contactos
                PrintWriter descWriter = new PrintWriter(desc_contactos);
                for (int i = 0; i < 8; i++) {
                    descWriter.println(desc.get(i).toString());
                }
                descWriter.close();

                // escribir usuario ordenados por su clave única
                PrintWriter userWriter = new PrintWriter(contactos);
                for (int i = 0; i < aux_contactos.size(); i++) {
                    if (aux_contactos.get(i).toString() != match) {
                        userWriter.println(aux_contactos.get(i).toString());
                    }
                }
                userWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;

        } else {
            return false;
        }
    }

}
