/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaUsuario;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ListaUsuario.Usuario;
import java.awt.List;
import java.util.ArrayList;
/**
 *
 * @author llaaj
 */
public class ListaUsuario {
    File indice = new File("C:\\MEIA\\lista_usuario.txt");
    File desc_indice = new File("C:\\MEIA\\desc_lista_usuario.txt");
    File bloque = new File("C:\\MEIA\\bloque_usuario.txt");
    File desc_bloque = new File("C:\\MEIA\\desc_bloque_usuario.txt");
    int maximo = 5;
    
    public Boolean insertar(String key, String descripcion){
        String[] registro = buscar(key);
        if (registro != null) {
            escribirBloque(key, descripcion);
            escribirIndice(key);
            return true;
        }
        else {
            return false;
        }
    }
    
    public String[] buscar(String key){
        // Leer en Secuencial Indizado
        try {
            FileReader fr = new FileReader(indice);
            BufferedReader br = new BufferedReader(fr);

            String linea = "";
            ArrayList secIndizado = new ArrayList<String>();
            while((linea = br.readLine()) != null){
                secIndizado.add(linea);
            }

            // buscar cuál es el índice del registro inicial
            ArrayList desc = ReadFile(desc_indice);
            String[] campo_registro = desc.get(9).toString().split(" ");
            Integer nReg = Integer.parseInt(campo_registro[1]);

            // buscar registro inicial
            String[] registro = buscarRegistroEnLista(secIndizado, nReg);

            Boolean encontrado = false;
            while(!encontrado){
                if (key.compareTo(registro[2]) > 0){
                    Integer sig = Integer.parseInt(registro[3]);
                    
                    if (sig != 0) {
                        registro = buscarRegistroEnLista(secIndizado, sig);
                    } 
                    else {
                        encontrado = true;
                    }
                }
                else {
                    encontrado = true;      
                    return registro;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // not found
        return null;
    }
    
    public Boolean modificar(String key, String descripcion){
        // leer archivos
        ArrayList listIndice = ReadFile(indice);
        ArrayList listBloque = ReadFile(bloque);
        
        String[] registro = buscarRegistroEnBloque(listBloque, key);
        
        if (registro != null) {
            registro[1] = descripcion;
            escribirBloque(registro[0], registro[1], true);
            return true;
        }
        else {
            return false;
        }
    }
    
    public Boolean eliminar(String key){
        String[] elemento = buscar(key);
        if (elemento != null) {
            escribirBloque(key, "", false);
            escribirIndice(key, false);
            return true;
        }
        else {
            return false;
        }
    }
       
    private void escribirBloque(String key, String descripcion){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String fecha = dtf.format(now);
        
        String[] key_parts = key.split(",");
        Usuario user = new Usuario(key_parts[0], key_parts[1], key_parts[2], descripcion, fecha, true);
        
        try {
            if (bloque.createNewFile()) { // NO EXISTE EL ARCHIVO
                System.out.println("File created: " + bloque.getName());

                // crear maestro
                PrintWriter descWriter = new PrintWriter(bloque);
                descWriter.println(user.toString());
                descWriter.close();
                
                escribirDescBloque(user.toString());
            } else {
                
                String[] registro = buscar(key);
                if (registro == null) {
                    // crear maestro
                    PrintWriter descWriter = new PrintWriter(bloque);
                    descWriter.println(user.toString());
                    descWriter.close();
                    
                    escribirDescBloque(user.toString());
                }
                else {
                    // error
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void escribirBloque(String key, String descripcion, Boolean active){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String fecha = dtf.format(now);
        
        String[] key_parts = key.split(",");
        Usuario user = new Usuario(key_parts[0], key_parts[1], key_parts[2], descripcion, fecha, active);
        
        try {

            String[] registro = buscar(key);
            if (registro != null) {
                ArrayList bloq = ReadFile(bloque);
                
                registro = buscarRegistroEnBloque(bloq, key);
                String reg = "";
                for (int i = 0; i < registro.length; i++) {
                    reg += registro[i];
                }
                
                int index = bloq.indexOf(reg);
                
                bloq.set(index, reg);
                
                PrintWriter descWriter = new PrintWriter(desc_bloque);
                for (int i = 0; i < bloq.size(); i++) {
                    descWriter.println(bloq.get(i).toString());
                }
                descWriter.close();
                
                escribirDescBloque(user.toString());
            }
            else {
                // error
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void escribirIndice(String key){       
        String[] keyFields = key.split(",");
        String usuario = keyFields[1];
        try {
            if (bloque.createNewFile()) { // NO EXISTE EL ARCHIVO

                // crear Indice
                PrintWriter indWriter = new PrintWriter(bloque);
                indWriter.print("1|1.1|" + key + "|0|1");
                indWriter.close();
                
                escribirDescIndice(usuario);
            } else {

                // Leer
                FileReader fr = new FileReader(indice);
                BufferedReader br = new BufferedReader(fr);
                
                String linea = "";
                ArrayList lista = new ArrayList<String>();
                while((linea = br.readLine()) != null){
                    lista.add(linea);
                }
                
                String[] campos = lista.get(lista.size() - 1).toString().split("|");
                int lastReg = Integer.parseInt(campos[0]);
                lastReg++;
                
                // buscar cuál es el índice del registro inicial
                ArrayList desc = ReadFile(desc_indice);
                String[] campo_registro = desc.get(9).toString().split(" ");
                Integer nReg = Integer.parseInt(campo_registro[1]);
                
                // buscar registro inicial
                String[] registro = buscarRegistroEnLista(lista, nReg);
                
                FileWriter writer = new FileWriter(indice, true);
                BufferedWriter bw = new BufferedWriter(writer);
                
                // buscar su posicion
                Boolean encontrado = false;
                
                while(!encontrado){
                    if (key.compareTo(registro[2]) < 0) {
                        encontrado = true;
                        
                        // siguiente = [0]
                        bw.write(lastReg + "|1." + lastReg + "|" + key 
                                + "|" + registro[0] + "|1"
                                + System.getProperty("line.separator"));
                        bw.close();
                        writer.close();
                        
                        // cambiar registro inicial
                    }
                    else if (key.compareTo(registro[2]) > 0){
                        Integer sig = Integer.parseInt(registro[3]);
                        registro = buscarRegistroEnLista(lista, sig);
                    }
                    else{
                        // la llave ya existe
                    }
                }
                
                escribirDescIndice(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void escribirIndice(String key, Boolean active){       
        String[] keyFields = key.split(",");
        String usuario = keyFields[1];
        try {
            // Leer
            FileReader fr = new FileReader(indice);
            BufferedReader br = new BufferedReader(fr);

            String linea = "";
            ArrayList lista = new ArrayList<String>();
            while((linea = br.readLine()) != null){
                lista.add(linea);
            }

            String[] campos = lista.get(lista.size() - 1).toString().split("|");
            int lastReg = Integer.parseInt(campos[0]);
            lastReg++;

            // buscar cuál es el índice del registro inicial
            ArrayList desc = ReadFile(desc_indice);
            String[] campo_registro = desc.get(9).toString().split(" ");
            Integer nReg = Integer.parseInt(campo_registro[1]);

            // buscar registro inicial
            String[] registro = buscarRegistroEnLista(lista, nReg);

            FileWriter writer = new FileWriter(indice, true);
            BufferedWriter bw = new BufferedWriter(writer);

            // buscar su posicion
            ArrayList index = ReadFile(indice);
            Boolean encontrado = false;

            while(!encontrado){
                if (key.compareTo(registro[2]) < 0) {
                    encontrado = true;

                    String reg = "";
                    for (int i = 0; i < registro.length; i++) {
                        reg += registro[i];
                    }
                    int i = index.indexOf(reg);
                    
                    // eliminacion logica
                    registro[4] = "0";
                    String newReg = "";
                    for (int j = 0; j < registro.length; j++) {
                        newReg += registro[j];
                    }
                    String newSiguiente = registro[3];
                    // escribir
                    index.set(i, reg);
                    
                    // cambiar orden
                    String nRegBorrado = registro[0];
                    
                    for (int j = 0; j < index.size(); j++) {
                        String[] regAnterior = index.get(i).toString().split("|");
                        if (regAnterior[3] == nRegBorrado && regAnterior[4] != "0") {
                            regAnterior[3] = newSiguiente;
                        }
                    }
                    
                    // cambiar registro inicial
                }
                else if (key.compareTo(registro[2]) > 0){
                    Integer sig = Integer.parseInt(registro[3]);
                    registro = buscarRegistroEnLista(lista, sig);
                }
                else{
                    // la llave ya existe
                }
                
                
                escribirDescIndice(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String[] buscarRegistroEnLista(ArrayList lista, Integer nReg){
        // buscar registro inicial
        String[] registro = null;
        for (int i = 0; i < lista.size(); i++) {
            registro = lista.get(i).toString().split("|");
            if (registro[0] == nReg.toString()) {
                return registro;
            }
        }
        return null;
    }
    
    private String[] buscarRegistroEnBloque(ArrayList lista, String key){
        // buscar registro inicial
        String[] registro = null;
        for (int i = 0; i < lista.size(); i++) {
            registro = lista.get(i).toString().split("|");
            if (registro[0] == key) {
                return registro;
            }
        }
        return null;
    }
    
    private void escribirDescBloque(String usuario){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String fecha = dtf.format(now);
        
        try {
            if (desc_bloque.createNewFile()) { // NO EXISTE EL ARCHIVO
                System.out.println("File created: " + desc_bloque.getName());

                // crear descriptor
                PrintWriter descWriter = new PrintWriter(desc_bloque);
                descWriter.print("nombre_simbolico: maestro\n"
                        + "fecha_creacion: " + fecha + "\n"
                        + "usuario_creacion: " + usuario + "\n"
                        + "fecha_modificacion: " + fecha + "\n"
                        + "usuario_modificacion: " + usuario + "\n"
                        + "#_registros: 1\n"
                        + "registros_activos: 1\n"
                        + "registros_inactivos: 0\n"
                        + "max_reorganizacion: " + maximo);
                descWriter.close();
                
            } else {
                ArrayList desc = ReadFile(desc_bloque);
                ArrayList bloq = ReadFile(bloque);
                
                // actualizar fecha
                desc.set(3, "fecha_modificacion: " + fecha);
                // actualizar usuario modificacion
                desc.set(4, "usuario_modificacion: " + usuario);
                
                // aumentar el número de registros
                Integer registros = bloq.size();
                // actualizar registro
                desc.set(5, "#_registros: " + registros);
                
                // registros activos e inactivos
                Integer activos = registrosActivos(bloq);
                Integer inactivos = registros - activos;
                
                desc.set(6, "registros_activos: " + activos);
                desc.set(7, "registros_inactivos: " + inactivos);
                
                // modificar archivo
                PrintWriter descWriter = new PrintWriter(desc_bloque);
                for (int i = 0; i < desc.size(); i++) {
                    descWriter.println(desc.get(i).toString());
                }
                descWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    private void escribirDescIndice(String usuario){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String fecha = dtf.format(now);
        
        try {
            if (desc_indice.createNewFile()) { // NO EXISTE EL ARCHIVO
                System.out.println("File created: " + desc_indice.getName());

                // crear descriptor
                PrintWriter descWriter = new PrintWriter(desc_indice);
                descWriter.print("nombre_simbolico: desc_indice\n"
                        + "fecha_creacion: " + fecha + "\n"
                        + "usuario_creacion: " + usuario + "\n"
                        + "fecha_modificacion: " + fecha + "\n"
                        + "usuario_modificacion: " + usuario + "\n"
                        + "#_registros: 1\n"
                        + "registros_activos: 1\n"
                        + "registros_inactivos: 0\n"
                        + "max_reorganizacion: " + maximo
                        + "registro_inicial: 1"
                        + "No. Bloques: 1");
                descWriter.close();
                
            } else {
                ArrayList desc = ReadFile(desc_indice);
                ArrayList index = ReadFile(indice);
                
                // actualizar fecha
                desc.set(3, "fecha_modificacion: " + fecha);
                // actualizar usuario modificacion
                desc.set(4, "usuario_modificacion: " + usuario);
                
                // aumentar el número de registros
                Integer registros = index.size();
                // actualizar registro
                desc.set(5, "#_registros: " + registros);
                
                // registros activos e inactivos
                Integer activos = registrosActivos(index);
                Integer inactivos = registros - activos;
                
                desc.set(6, "registros_activos: " + activos);
                desc.set(7, "registros_inactivos: " + inactivos);
                
                // modificar archivo
                PrintWriter descWriter = new PrintWriter(desc_bloque);
                for (int i = 0; i < desc.size(); i++) {
                    descWriter.println(desc.get(i).toString());
                }
                descWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
    
    private Integer registrosActivos(ArrayList list){
        String[] registro = null;
        Integer count = 0;
        for (int i = 0; i < list.size(); i++) {
            registro = list.get(i).toString().split("|");
            if (registro[registro.length - 1] == "1") {
                count++;
            }
        }
        
        return count;
    }
}
