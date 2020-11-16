/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Access.Usuario;
import Tree.Correo;
import Tree.Tree;

/**
 * Patrón singleton, usado para persistir el usuario actual en la aplicación.
 * @author edanP
 */
public class Data {

    private static Data instance = null;

    /**
     * Crear instancia de singleton
     * @return Instancia singleton
     */
    public static Data Instance() {        
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    public Usuario user = null;
    public Tree tree = new Tree();
    public int actual = 0;
    public Correo ver = null;
}
