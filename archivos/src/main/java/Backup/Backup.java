/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backup;

/**
 *
 * @author llaaj
 */
public class Backup {
    private String ruta_absoluta;
    private String usuario;
    private String fecha_transaccion;
    
    /**
     * Constructor de objeto Backup
     * @param ruta_absoluta ruta destino del backup     
     * @param usuario nombre de usuario del administrador
     * @param fecha_transaccion fecha actual
     */
    public Backup(String ruta_absoluta, String usuario, String fecha_transaccion){
        this.ruta_absoluta = ruta_absoluta;
        this.usuario = usuario;
        this.fecha_transaccion = fecha_transaccion;
    }
    
    /**
     * Método toString de la clase Backup
     * @return retorna el objeto con un formato separado por "|"
     */
    public String toString(){
        return this.ruta_absoluta + "|" + this.usuario + "|" + this.fecha_transaccion;
    }
}
