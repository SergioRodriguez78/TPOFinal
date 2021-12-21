/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio Rodriguez
 */
@Entity
public class Administrador implements Serializable {

     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idA;

    @Temporal(TemporalType.DATE)
    private Date fechaNac;

    @Basic
    private String nombre;
    private String apellido;
    private String direccion;
    private String dni;
    private String celular;
    private String email;
    private boolean habilitado;

    @OneToOne
    private Usuario usuario;
    @OneToMany
    private List<Empleado> empleados;
    @OneToMany
    private List<Venta> ventas;
    @OneToMany
    private List<Cliente> clientes;

    public Administrador() {
    }

    public Administrador(int idA, Date fechaNac, String nombre, String apellido, String direccion, String dni, String celular, String email, boolean habilitado, Usuario usuario, List<Empleado> empleados, List<Venta> ventas, List<Cliente> clientes) {
        this.idA = idA;
        this.fechaNac = fechaNac;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.dni = dni;
        this.celular = celular;
        this.email = email;
        this.habilitado = habilitado;
        this.usuario = usuario;
        this.empleados = empleados;
        this.ventas = ventas;
        this.clientes = clientes;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

  

}
