/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Access.Usuario;

/**
 * Patrón singleton, usado para persistir el usuario actual en la aplicación.
 * @author edanP
 */
public class Data {

    private static Data instance = null;

    /**
     * Crear instancia de singleton
     * @return 
     */
    public static Data Instance() {        
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    public Usuario user = null;
}
