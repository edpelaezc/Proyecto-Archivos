package Contacts;

import Access.Usuario;

public class Contacto implements Comparable<Contacto>{
    private String usuario;
    private String contacto;
    private String fecha_transaccion;
    private String usuario_transaccion;
    private int estatus;


    public Contacto(String usuario, String contacto, String fecha_transaccion, String usuario_transaccion, int estatus) {
        this.usuario = usuario;
        this.contacto = contacto;
        this.fecha_transaccion = fecha_transaccion;
        this.usuario_transaccion = usuario_transaccion;
        this.estatus = estatus;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getFecha_transaccion() {
        return fecha_transaccion;
    }

    public void setFecha_transaccion(String fecha_transaccion) {
        this.fecha_transaccion = fecha_transaccion;
    }

    public String getUsuario_transaccion() {
        return usuario_transaccion;
    }

    public void setUsuario_transaccion(String usuario_transaccion) {
        this.usuario_transaccion = usuario_transaccion;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String toString(){
        return this.getUsuario() + "|" + this.getContacto() + "|" + this.getFecha_transaccion() + "|" + this.getUsuario() + "|" + this.getEstatus();
    }

    /**
     * Override para comparar por medio de la llave compuesta {usuario, contacto}
     * @param o
     * @return
     */
    @Override
    public int compareTo(Contacto o) {
        int usuario = this.getUsuario().compareToIgnoreCase(o.getUsuario());
        int contacto = this.getContacto().compareToIgnoreCase(o.getContacto());

        if (usuario == 0) {
            return contacto;
        } else {
            return usuario;
        }
    }
}
