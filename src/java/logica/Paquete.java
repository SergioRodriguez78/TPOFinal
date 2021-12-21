/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Sergio Rodriguez
 */
@Entity
public class Paquete implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigoP;
    @Basic
    private double costo;
    private boolean habilitado;

    @ManyToMany
    private List<Servicio> servicios;
    @OneToMany
    private List<Venta> ventasP;

    public Paquete() {
    }

    public Paquete(int codigoP, double costo, boolean habilitado, List<Servicio> servicios, List<Venta> ventasP) {
        this.codigoP = codigoP;
        this.costo = costo;
        this.habilitado = habilitado;
        this.servicios = servicios;
        this.ventasP = ventasP;
    }

    public int getCodigoP() {
        return codigoP;
    }

    public void setCodigoP(int codigoP) {
        this.codigoP = codigoP;
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

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public List<Venta> getVentasP() {
        return ventasP;
    }

    public void setVentasP(List<Venta> ventasP) {
        this.ventasP = ventasP;
    }

    public double calcularCostoPaquete(List<Servicio> servicios)
    {
        double costo=0.0;
        
        for(Servicio s:servicios)
        {
            costo+=s.getCosto();
        }
          costo=( costo - (costo*0.1))  ;
          
          return costo;
          
    }
   

}
