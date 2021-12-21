/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Sergio Rodriguez
 */
@Entity
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idV;
    @Basic
    private String tipoPago;
    private boolean habilitado;

    @ManyToOne
    private Empleado empleado;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Paquete paquete;

    @ManyToOne
    private Servicio servicio;

    @ManyToOne
    private Administrador admin;

    public Venta() {
    }

    public Venta(int idV, String tipoPago, boolean habilitado, Empleado empleado, Cliente cliente, Paquete paquete, Servicio servicio, Administrador admin) {
        this.idV = idV;
        this.tipoPago = tipoPago;
        this.habilitado = habilitado;
        this.empleado = empleado;
        this.cliente = cliente;
        this.paquete = paquete;
        this.servicio = servicio;
        this.admin = admin;
    }

    public int getIdV() {
        return idV;
    }

    public void setIdV(int idV) {
        this.idV = idV;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    

}
