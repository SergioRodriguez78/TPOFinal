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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio Rodriguez
 */
@Entity
public class Servicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigoS;
    
    @Temporal(TemporalType.DATE)
     private Date fechaS;
     
     @Basic
    private String nombre;
    private String descripcion;
    private String destino;
    private double costo;
    private boolean habilitado;
    
    @OneToMany
    private List<Venta> ventas;
    
    @ManyToMany
    private List<Paquete> paquetes;

    public Servicio() {
    }

    public Servicio(int codigoS, Date fechaS, String nombre, String descripcion, String destino, double costo, boolean habilitado, List<Venta> ventas, List<Paquete> paquetes) {
        this.codigoS = codigoS;
        this.fechaS = fechaS;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.destino = destino;
        this.costo = costo;
        this.habilitado = habilitado;
        this.ventas = ventas;
        this.paquetes = paquetes;
    }

    public int getCodigoS() {
        return codigoS;
    }

    public void setCodigoS(int codigoS) {
        this.codigoS = codigoS;
    }

    public Date getFechaS() {
        return fechaS;
    }

    public void setFechaS(Date fechaS) {
        this.fechaS = fechaS;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    
    
}
