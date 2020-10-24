package Admin;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;
import static javax.swing.JOptionPane.showMessageDialog;

public class FileHandling {

    /**
     * Obtención de
     *
     * @param ruta Ruta archivo
     * @param usuario Nombre usuario
     * @return Usuario en array
     */
    File usuario = new File("C:\\MEIA\\usuario.txt");
    File desc_usuario = new File("C:\\MEIA\\desc_usuario.txt");
    File bitacora_usuario = new File("C:\\MEIA\\bitacora_usuario.txt");
    File desc_bitacora_usuario = new File("C:\\MEIA\\desc_bitacora_usuario.txt");
    File Mantenimiento_listas_Distribuidas = new File("C:\\MEIA\\lista.txt");

    public void Write_MLD(){
        
    
    }
    
    public String[] Read_Text(String ruta, String usuario) {
        String[] txt = {};
        try {
            BufferedReader archivo = new BufferedReader(new FileReader(ruta));
            String temp = "";
            String bfr;
            while ((bfr = archivo.readLine()) != null) {
                String separador = Pattern.quote("|");
                txt = bfr.split(separador);
                if (usuario.equals(txt[0])) {
                    break;
                }
            }

            if (txt.length == 0) {
                BufferedReader archivo2 = new BufferedReader(new FileReader("C:\\MEIA\\usuario.txt"));                
                String bfr2;
                while ((bfr2 = archivo2.readLine()) != null) {
                    String separador = Pattern.quote("|");
                    txt = bfr2.split(separador);
                    if (usuario.equals(txt[0])) {
                        break;
                    }
                }
            }

        } catch (Exception e) {
            showMessageDialog(null, e);
        }
        return txt;
    }

    /**
     * Escribir en archivo los cambios realizados al usuario
     *
     * @param old_Line Registro antiguo
     * @param new_Line Registro editado
     * @param ruta Ruta en el archivo que se escribirá
     */
    public void Write_Text(String[] old_Line, String[] new_Line, String ruta) {
        String nueva_linea = Arrays.toString(new_Line).replace("[", "").replace("]", "");
        String vieja_linea = Arrays.toString(old_Line).replace("[", "").replace("]", "");
        nueva_linea = nueva_linea.replace(",", "|").replace(" ", "");
        vieja_linea = vieja_linea.replace(",", "|").replace(" ", "");
        Remove_Line(vieja_linea.trim(), nueva_linea.trim(), ruta);
    }

    /**
     * Sustitución de linea usuario en archivo
     *
     * @param lineToRemove
     * @param newLine
     * @param ruta
     */
    private void Remove_Line(String lineToRemove, String newLine, String ruta) {

        try {

            File inFile = new File(ruta);
            String nombre = inFile.getAbsolutePath();
            File tempFile = new File(nombre + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String line = null;
            while ((line = br.readLine()) != null) {

                if (!line.trim().equals(lineToRemove.trim())) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.println(newLine.trim());
            pw.close();
            br.close();
            inFile.delete();

            // volver a escribir
            br = new BufferedReader(new FileReader(nombre + ".tmp"));
            pw = new PrintWriter(new FileWriter(ruta));
            while ((line = br.readLine()) != null) {
                pw.println(line);
                pw.flush();
            }
            pw.close();
            br.close();
            tempFile.delete();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
