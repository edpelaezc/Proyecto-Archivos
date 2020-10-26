/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaUsuario;

/**
 *
 * @author llaaj
 */
public class Usuario {
    String nombre_lista;
    String usuario;
    String usuario_asociado;
    String descripcion;
    String fecha_creacion;
    Boolean status;

    public Usuario(String lista, String usuario, String usuario_asociado, String descripcion, String fecha, Boolean estado){
        this.nombre_lista = lista;
        this.usuario = usuario;
        this.usuario_asociado = usuario_asociado;
        this.descripcion = descripcion;
        this.fecha_creacion = fecha;
        this.status = estado;
    }
    
    public String toString(){
        if (this.status) 
            return this.nombre_lista + "," + this.usuario + "," + this.usuario_asociado + "|" 
                + this.descripcion + "|" + this.fecha_creacion + "|1\n";
        
        return this.nombre_lista + "," + this.usuario + "," + this.usuario_asociado + "|" 
                + this.descripcion + "|" + this.fecha_creacion + "|0\n";
    }
}
