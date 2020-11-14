/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import java.util.Date;

/**
 * Esta clase representa el objeto correo que será utilizado en el árbol.
 *
 * @author edanP
 */
public class Correo implements Comparable<Correo> {

    private String emisor;
    private String receptor;
    private String fecha;
    private String asunto;
    private String mensaje;
    private String adjunto;
    private String estatus;

    public Correo(String emisor, String receptor, String fecha, String asunto, String mensaje, String adjunto, String estatus) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.fecha = fecha;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.adjunto = adjunto;
        this.estatus = estatus;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return this.getEmisor() + "|" + this.getReceptor()+ "|" + this.getFecha()+ "|" + this.getAsunto()+ "|" + this.getMensaje() + "|" + this.getAdjunto() + "|" + this.getEstatus();
    }

    @Override
    public int compareTo(Correo o) {
        int emisor = this.getEmisor().compareToIgnoreCase(o.getEmisor());
        int receptor = this.getReceptor().compareToIgnoreCase(o.getReceptor());
        int fecha = this.getFecha().compareToIgnoreCase(o.getFecha());
        
        if (emisor == 0) {            
            if (receptor == 0) {
                return fecha;
            } else {
                return receptor;
            }
        } else {
            return emisor;
        }
    }

}
