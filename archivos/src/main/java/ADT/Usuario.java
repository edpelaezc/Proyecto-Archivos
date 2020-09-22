/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author edanP
 */
public class Usuario {

    private String usuario;
    private String nombre;
    private String apellido;
    private String password;
    private int rol;
    private String fecha_nacimiento;
    private String correo_alterno;
    private String telefono;
    private String path_fotografia;
    private int estatus;

    public Usuario(String usuario, String nombre, String apellido, String password, int rol, String fecha_nacimiento, String correo_alterno, String telefono, String path_fotografia, int estatus) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.rol = rol;
        this.fecha_nacimiento = fecha_nacimiento;
        this.correo_alterno = correo_alterno;
        this.telefono = telefono;
        this.path_fotografia = path_fotografia;
        this.estatus = estatus;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the rol
     */
    public int getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(int rol) {
        this.rol = rol;
    }

    /**
     * @return the fecha_nacimiento
     */
    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * @param fecha_nacimiento the fecha_nacimiento to set
     */
    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    /**
     * @return the correo_alterno
     */
    public String getCorreo_alterno() {
        return correo_alterno;
    }

    /**
     * @param correo_alterno the correo_alterno to set
     */
    public void setCorreo_alterno(String correo_alterno) {
        this.correo_alterno = correo_alterno;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the path_fotografia
     */
    public String getPath_fotografia() {
        return path_fotografia;
    }

    /**
     * @param path_fotografia the path_fotografia to set
     */
    public void setPath_fotografia(String path_fotografia) {
        this.path_fotografia = path_fotografia;
    }

    /**
     * @return the estatus
     */
    public int getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
}
