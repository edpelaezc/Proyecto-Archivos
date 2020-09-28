package Admin;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;
import static javax.swing.JOptionPane.showMessageDialog;

public class FileHandling {
    /**
     * Obtención de  
     * @param ruta
     * @param usuario
     * @return 
     */
    
    public String[] Read_Text(String ruta, String usuario)
    {
        String[] txt = {};
        try{
            BufferedReader archivo = new BufferedReader(new FileReader(ruta));
            String temp = "";
            String bfr;
            while((bfr = archivo.readLine()) != null){
                String separador = Pattern.quote("|");
                txt = bfr.split(separador);
                if (usuario.equals(txt[0])) {
                    break;
                }
            }
        }
        catch(Exception e){
            showMessageDialog(null, e); 
        }
        return txt;
    }
    /**
     * Escribir en archivo los cambios realizados al usuario
     * @param old_Line
     * @param new_Line
     * @param ruta 
     */
    public void Write_Text(String[] old_Line, String[] new_Line, String ruta){
        String nueva_linea = Arrays.toString(new_Line).replace("[", "").replace("]", "");
        String vieja_linea = Arrays.toString(old_Line).replace("[", "").replace("]", "");
        nueva_linea = nueva_linea.replace("," , "|").replace(" ","");
        vieja_linea = vieja_linea.replace("," , "|").replace(" ","");
        Remove_Line(vieja_linea.trim(), nueva_linea.trim(), ruta);
    }
    
    /**
     * Sustitución de linea usuario en archivo
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

    } catch (FileNotFoundException ex) {
        ex.printStackTrace();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}
    
}
