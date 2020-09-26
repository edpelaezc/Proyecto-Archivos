import java.io.*;
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
}
