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
    
    public Backup(String ruta_absoulta, String usuario, String fecha_transaccion){
        this.ruta_absoluta = ruta_absoluta;
        this.usuario = usuario;
        this.fecha_transaccion = fecha_transaccion;
    }
    
    public String toString(){
        return this.ruta_absoluta + "|" + this.usuario + "|" + this.fecha_transaccion;
    }
}
