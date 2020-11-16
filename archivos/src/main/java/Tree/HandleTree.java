/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import Access.HandleFiles;
import Data.Data;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author edanP
 */
public class HandleTree {
    
    File correos = new File("C:\\MEIA\\correos.txt");
    HandleFiles handler = new HandleFiles();

    public void readTree() {
        ArrayList temp = handler.ReadFile(correos);

        for (int i = 0; i < temp.size(); i++) {
            Correo aux = createCorreo(temp.get(i).toString());
            if (!"0".equals(aux.getEstatus())) {
                Data.Instance().tree.add(aux);
            }
        }

        System.out.println("CARGA TERMINADA");
    }

    private Correo createCorreo(String line) {
        String[] fields = line.split("\\|");
        return new Correo(
                fields[3],
                fields[4],
                fields[5],
                fields[6],
                fields[7],
                fields[8],
                fields[9]
        );
    }

}
