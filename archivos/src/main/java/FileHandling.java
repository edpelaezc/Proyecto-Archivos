import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;
import static javax.swing.JOptionPane.showMessageDialog;

public class FileHandling {
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
    public void Write_Text(String[] old_Line, String[] new_Line, String ruta){
        String nueva_linea = Arrays.toString(old_Line);
        String vieja_linea = Arrays.toString(new_Line);
        Remove_Line(vieja_linea, nueva_linea, ruta);
    
    }
    
    private void Remove_Line(String lineToRemove, String ruta, String newLine) {
 
    try {
 
        File inFile = new File(ruta);
        File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
 
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
 
        String line = null;
        while ((line = br.readLine()) != null) {
 
            if (!line.trim().equals(lineToRemove)) {
                pw.println(line);
                pw.println(newLine);
                pw.flush();
            }
        }
        pw.close();
        br.close();

    } catch (FileNotFoundException ex) {
        ex.printStackTrace();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}
    
}
