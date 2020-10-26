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
        ArrayList ind = ReadFile(indice);
        String[] registro = null;
        for (int i = 0; i < ind.size(); i++) {
            String[] partes = ind.get(i).toString().split("\\|");
            if (partes[2].compareTo(key) == 0) {
                registro = partes;
            }
        }
        
        
        if (registro == null) {
            escribirBloque(key, descripcion);
            escribirIndice(key);
            
            ArrayList desc = ReadFile(bloque);
            if (desc.size() % maximo == 0) {
                eliminarFisicamente();
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    private void eliminarFisicamente(){
        ArrayList bloq = ReadFile(bloque);
        ArrayList index = ReadFile(indice);
        
        ArrayList newBloq = new ArrayList<String>();
        for (int i = 0; i < bloq.size(); i++) {
            String[] registro = bloq.get(i).toString().split("\\|");
            if (registro[3].equals("1")) {
                newBloq.add(bloq.get(i));
            }
        }
        
        ArrayList newIndex = new ArrayList<String>();
        for (int i = 0; i < index.size(); i++) {
            String[] registro = index.get(i).toString().split("\\|");
            if (registro[4].equals("1")) {
                newIndex.add(index.get(i));
            }
        }
        
        try {
            if (bloque.delete()) {
                bloque.createNewFile();
                
                // modificar archivo
                FileWriter writer = new FileWriter(bloque, false);
                BufferedWriter bwriter = new BufferedWriter(writer);
                for (int k = 0; k < newBloq.size(); k++) {
                    bwriter.write(newBloq.get(k).toString() + "\n");
                }
                bwriter.close();
                writer.close();
            }
            
            if (indice.delete()) {
                indice.createNewFile();
                
                // modificar archivo
                FileWriter writer = new FileWriter(indice, false);
                BufferedWriter iwriter = new BufferedWriter(writer);
                for (int k = 0; k < newIndex.size(); k++) {
                    iwriter.write(newIndex.get(k).toString() + "\n");
                }
                iwriter.close();
                writer.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public String[] busqueda(String key){
        try {
            FileReader fr = new FileReader(bloque);
            BufferedReader br = new BufferedReader(fr);

            ArrayList bloq = ReadFile(bloque);
            String[] registro = null;
            for (int i = 0; i < bloq.size(); i++) {
                registro = bloq.get(i).toString().split("\\|");
                if (key.equals(registro[0])) {
                    return registro;
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        // not found
        return null;
    }
    
    private String[] buscar(String key){
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
                
                ArrayList ind = ReadFile(bloque);
                String[] registro = null;
                for (int i = 0; i < ind.size(); i++) {
                    String[] partes = ind.get(i).toString().split("\\|");
                    if (partes[0].compareTo(key) == 0) {
                        registro = partes;
                    }
                }
                
                if (registro == null) {
                    // crear maestro
                    ArrayList bloq = ReadFile(bloque);
                    bloq.add(user.toString());
                    
                    FileWriter writer = new FileWriter(bloque, false);
                    BufferedWriter indexWriter = new BufferedWriter(writer);
                    for (int k = 0; k < bloq.size(); k++) {
                        indexWriter.write(bloq.get(k).toString() + "\n");
                    }
                    indexWriter.close();
                    writer.close();
                    
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
            
            if (bloque.exists()) {
                String[] registro = buscar(key);
                if (registro != null) {
                    ArrayList bloq = ReadFile(bloque);

                    registro = buscarRegistroEnBloque(bloq, key);
                    String reg = "";
                    for (int i = 0; i < registro.length; i++) {
                        if (i == registro.length - 1) {
                            reg += registro[i];
                        }
                        else {
                            reg += registro[i] + "|";
                        }
                    }

                    int index = bloq.indexOf(reg);

                    String newreg = "";
                    for (int i = 0; i < registro.length; i++) {
                        if (i == registro.length - 1) {
                            newreg += "0";
                        }
                        else {
                            newreg += registro[i] + "|";
                        }
                    }
                    
                    bloq.set(index, newreg);

                    
                    FileWriter writer = new FileWriter(bloque, false);
                    BufferedWriter indexWriter = new BufferedWriter(writer);
                    for (int k = 0; k < bloq.size(); k++) {
                        indexWriter.write(bloq.get(k).toString() + "\n");
                    }
                    indexWriter.close();
                    writer.close();

                    escribirDescBloque(key_parts[1]);
                }
                else {
                    // error
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void escribirIndice(String key){       
        String[] keyFields = key.split(",");
        String usuario = keyFields[1];
        try {
            if (indice.createNewFile()) { // NO EXISTE EL ARCHIVO
                System.out.println("File created: " + indice.getName());
                
                // crear Indice
                PrintWriter indWriter = new PrintWriter(indice);
                indWriter.print("1|1.1|" + key + "|0|1");
                indWriter.close();
                
                escribirDescIndice(usuario, "1");
            } else {

                // Leer
                FileReader fr = new FileReader(indice);
                BufferedReader br = new BufferedReader(fr);
                
                String linea = "";
                ArrayList lista = new ArrayList<String>();
                while((linea = br.readLine()) != null){
                    lista.add(linea);
                }
                
                String[] campos = lista.get(lista.size() - 1).toString().split("\\|");
                Integer lastReg = Integer.parseInt(campos[0]);
                lastReg++;
                
                // buscar cuál es el índice del registro inicial
                ArrayList desc = ReadFile(desc_indice);
                String[] campo_registro = desc.get(9).toString().split(" ");
                Integer nReg = Integer.parseInt(campo_registro[1]);
                
                // buscar registro inicial
                String[] regActual = buscarRegistroEnLista(lista, nReg);
                
                
                
                String registroInicial = regActual[0];
                
                // buscar su posicion
                Boolean encontrado = false;
                Integer count = 0;
                while(!encontrado){
                    // comparar llaves, si es menor
                    if (key.compareTo(regActual[2]) < 0) { 
                        encontrado = true;
                        
                        // cambiar el apuntador del anterior
                        ArrayList archivoIndice = ReadFile(indice);
                        
                        //
                        for (int i = 0; i < archivoIndice.size(); i++) {
                            String[] regAnt = archivoIndice.get(i).toString().split("\\|");
                            
                            // comparar
                            //  siguiente-del-anterior con indice-del-actual
                            if (regAnt[3].equals(regActual[0])) {
                                // ------------------numero-----|--1.numero---|--llave------|-siguiente-|--activo
                                archivoIndice.set(i, regAnt[0]+"|"+regAnt[1]+"|"+regAnt[2]+"|"+lastReg+"|"+regAnt[4]);
                            }
                        }
                        
                        // siguiente-del-nuevo = [0]
                        archivoIndice.add(lastReg + "|1." + lastReg + "|" + key 
                                + "|" + regActual[0] + "|1");
                        
                        indice.delete();
                        indice.createNewFile();
                        FileWriter writer = new FileWriter(indice, false);
                        BufferedWriter indexWriter = new BufferedWriter(writer);
                        for (int k = 0; k < archivoIndice.size(); k++) {
                            indexWriter.write(archivoIndice.get(k).toString() + "\n");
                        }
                        indexWriter.close();
                        writer.close();
                        
                        if (count == 0) {
                            // cambiar registro inicial
                            registroInicial = lastReg.toString();
                        }
                    }
                    else if (key.compareTo(regActual[2]) > 0){
                        count++;
                        Integer sig = Integer.parseInt(regActual[3]);
                        
                        if (sig == 0) {
                            encontrado = true;
                        
                            // cambiar el apuntador del anterior
                            ArrayList archivoIndice = ReadFile(indice);
                            for (int i = 0; i < archivoIndice.size(); i++) {
                                String[] regAnt = archivoIndice.get(i).toString().split("\\|");
                                if (regAnt[3].equals(sig.toString())) {
                                    archivoIndice.set(i, regAnt[0]+"|"+regAnt[1]+"|"
                                        +regAnt[2]+"|"+lastReg+"|"+regAnt[4]);
                                }
                            }

                            // siguiente = [0]
                            archivoIndice.add(lastReg + "|1." + lastReg + "|" + key 
                                    + "|0|1");
                            
                            indice.delete();
                            indice.createNewFile();
                            FileWriter writer = new FileWriter(indice, false);
                            BufferedWriter indexWriter = new BufferedWriter(writer);
                            for (int k = 0; k < archivoIndice.size(); k++) {
                                indexWriter.write(archivoIndice.get(k).toString() + "\n");
                            }
                            indexWriter.close();
                            writer.close();
                        }
                        else {
                            regActual = buscarRegistroEnLista(lista, sig);
                        }
                    }
                    else{
                        // la llave ya existe
                    }
                }
                
                escribirDescIndice(usuario, registroInicial);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void escribirIndice(String key, Boolean active){       
        String[] keyFields = key.split(",");
        String usuario = keyFields[1];
        try {
            if (indice.exists()) {
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
                String registroInicial = registro[0];

                // buscar su posicion
                ArrayList index = ReadFile(indice);
                Boolean encontrado = false;
                Integer count = 0;
                while(!encontrado){
                    if (key.compareTo(registro[2]) < 0) {
                        encontrado = true;

                        String reg = registro[0]+"|"+registro[1]+"|"+registro[2]
                                +"|"+registro[3]+"|"+registro[4];
                        int i = index.indexOf(reg);

                        // eliminacion logica
                        registro[4] = "0";
                        String newReg = registro[0]+"|"+registro[1]+"|"+registro[2]
                                +"|"+registro[3]+"|"+registro[4];
                        String newSiguiente = registro[3];

                        // escribir
                        index.set(i, newReg);

                        // cambiar orden
                        String nRegBorrado = registro[0];

                        for (int j = 0; j < index.size(); j++) {
                            String[] regAnterior = index.get(i).toString().split("\\|");
                            if (regAnterior[3] == nRegBorrado && regAnterior[4] != "0") {
                                regAnterior[3] = newSiguiente;
                                index.set(i, regAnterior[0]+"|"+regAnterior[1]+"|"
                                        +regAnterior[2]+"|"+regAnterior[3]+"|"+regAnterior[4]);
                            }
                        }

                        // modificar archivo
                        indice.delete();
                        indice.createNewFile();
                        FileWriter writer = new FileWriter(indice, false);
                        BufferedWriter indexWriter = new BufferedWriter(writer);
                        for (int k = 0; k < index.size(); k++) {
                            indexWriter.write(index.get(k).toString());
                        }
                        indexWriter.close();

                        // cambiar registro inicial
                        if (count == 0) {
                            registroInicial = registro[3];
                        }
                    }
                    else if (key.compareTo(registro[2]) > 0){
                        count++;
                        Integer sig = Integer.parseInt(registro[3]);
                        registro = buscarRegistroEnLista(lista, sig);
                    }
                    
                    escribirDescIndice(usuario, registroInicial);
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String[] buscarRegistroEnLista(ArrayList lista, Integer nReg){
        // buscar registro inicial
        String[] registro = null;
        for (int i = 0; i < lista.size(); i++) {
            registro = lista.get(i).toString().split("\\|");
            if (registro[0].equals(nReg.toString())) {
                return registro;
            }
        }
        return null;
    }
    
    private String[] buscarRegistroEnBloque(ArrayList lista, String key){
        // buscar registro inicial
        String[] registro = null;
        for (int i = 0; i < lista.size(); i++) {
            registro = lista.get(i).toString().split("\\|");
            if (registro[0].equals(key)) {
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
    
    private void escribirDescIndice(String usuario, String regInicial){
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
                        + "max_reorganizacion: " + maximo + "\n"
                        + "registro_inicial: 1" + "\n"
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
                
                desc.set(9, "registro_inicial: " + regInicial);
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
            registro = list.get(i).toString().split("\\|");
            if (registro[registro.length - 1] == "1") {
                count++;
            }
        }
        
        return count;
    }
}
