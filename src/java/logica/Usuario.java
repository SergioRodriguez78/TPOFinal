/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 *
 * @author Sergio Rodriguez
 */
@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idU;

    @Basic
    private String nUsuario;
    private String contrasenia;
    private boolean habilitado;

    @Lob
    @Column(name = "imagen")
    private byte[] foto;

    @OneToOne
    private Empleado empleado;

    @OneToOne
    private Administrador admin;
    
    public Usuario() {
    }

    public Usuario(int idU, String nUsuario, String contrasenia, boolean habilitado, byte[] foto, Empleado empleado, Administrador admin) {
        this.idU = idU;
        this.nUsuario = nUsuario;
        this.contrasenia = contrasenia;
        this.habilitado = habilitado;
        this.foto = foto;
        this.empleado = empleado;
        this.admin = admin;
    }

    
    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getnUsuario() {
        return nUsuario;
    }

    public void setnUsuario(String nUsuario) {
        this.nUsuario = nUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

   
}
