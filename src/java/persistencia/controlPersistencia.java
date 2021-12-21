/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Administrador;
import logica.Cliente;
import logica.Empleado;
import logica.Paquete;
import logica.Servicio;
import logica.Usuario;
import logica.Venta;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Sergio Rodriguez
 */
public class controlPersistencia {

    UsuarioJpaController usuJPA = new UsuarioJpaController();
    EmpleadoJpaController empleJPA = new EmpleadoJpaController();
    AdministradorJpaController adminJPA = new AdministradorJpaController();
    ClienteJpaController clienJPA = new ClienteJpaController();
    ServicioJpaController servJPA = new ServicioJpaController();
    PaqueteJpaController paqJPA = new PaqueteJpaController();
    VentaJpaController ventaJPA = new VentaJpaController();

    ///////////////////////////////////EMPLEADO////////////////////////////////////////////
    public void crearEmpleado( Empleado empleado) {

        try {
            
            empleJPA.create(empleado);
        } catch (Exception e) {
            System.out.println("error al crear empleado y usuario");
        }
    }

    public List<Empleado> getEmpleados() {
        return empleJPA.findEmpleadoEntities();
    }

    public Empleado getEmpleadoParticular(int id) {
        return empleJPA.findEmpleado(id);
    }

    public void updateEpleado(Empleado empleado) {
        try {
            empleJPA.edit(empleado);
        } catch (Exception ex) {
            System.out.println("Error al modificar empleado");
        }

    }

    public void eliminarEmpleado(int id) {
        try {
            empleJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            System.out.println("Error al eliminar empleado");
        }
    }

    /////////////////////////////////////////////////ADMINSTRADOR///////////////////////////////////////////////
    public void crearAdministrador(Administrador admin, Usuario usuario) {
        try {
            usuJPA.create(usuario);
            adminJPA.create(admin);
            
        } catch (Exception e) {
            System.out.println("Error crear administrador");
        }
    }

    public List<Administrador> getAdministradores() {
        return adminJPA.findAdministradorEntities();
    }

    public Administrador getAdministradorParticular(int id) {
        return adminJPA.findAdministrador(id);
    }

    public void updateAdministrador(Administrador admin) {
        try {
            adminJPA.edit(admin);
        } catch (Exception ex) {
            System.out.println("Error modificar administrador");
        }
    }

    public void eliminarAdministrador(int id) {
        try {
            adminJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            System.out.println("Error elimianr administrador");
        }
    }
    //////////////////////////////////////CLIENTE///////////////////////////////////

    public void crearCliente(Cliente cliente) {
        try {
            clienJPA.create(cliente);
        } catch (Exception e) {
            System.out.println("Error creando cliente");
        }
    }

    public List<Cliente> getClientes() {
        return clienJPA.findClienteEntities();
    }

    public Cliente getClienteParticular(int id) {
        return clienJPA.findCliente(id);
    }

    public void updateCliente(Cliente cliente) {
        try {
            clienJPA.edit(cliente);
        } catch (Exception ex) {
            System.out.println("Error modificando al cliente");
        }

    }

    public void eliminarCliente(int id) {
        try {
            clienJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            System.out.println("Error borrando al cliente");
        }
    }

    ////////////////////////////////////////////////VENTA///////////////////////////////////
    public void crearVenta(Venta venta)
    {
        try {
            ventaJPA.create(venta);
                  
        } catch (Exception e) {
            System.out.println("Error creando venta");
        }
    
    }
    
    public List<Venta> getVentas()
    {
        return ventaJPA.findVentaEntities();
    }
    
    public Venta getVentaParticular(int id)
    {
     return ventaJPA.findVenta(id);
    }
    
    public void updateVenta(Venta venta)
    {
        try {
            ventaJPA.edit(venta);
        } catch (Exception ex) {
            System.out.println("Error modificando venta");
        }
                
    }
    
    public void eliminarVenta(int id)
    {
        try {
            ventaJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controlPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //////////////////////////////////////////////USUARIO///////////////////////////////////
    public void crearUsuario(Usuario usuario)
    {
        try {
            usuJPA.create(usuario);
        } catch (Exception e) {
        }
    }
    public void updateUsuario(Usuario usuario){
        try {
            usuJPA.edit(usuario);
        } catch (Exception ex) {
            
        }
    }
    public List<Usuario> getUsuarios() {
    return usuJPA.findUsuarioEntities();
}
    public Usuario getUsuarioParticular(int id){
        return usuJPA.findUsuario(id);
    }
    
    public void eliminarUsuario(int id)
    {
        try {
            usuJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            
        }
    }
    
    ///////////////////////////////////////SERVICIO//////////////////////////////////////////////
    
    public void crearServicio(Servicio servicio){
        try {
            servJPA.create(servicio);
        } catch (Exception e) {
        }
    }
    
    public List<Servicio> getServicios()
    {
    return servJPA.findServicioEntities();
    }
    
    public Servicio getServicioParticular(int id)
    {
    return servJPA.findServicio(id);
    }
    
    public void updateServicio(Servicio servicio){
        try {
            servJPA.edit(servicio);
        } catch (Exception ex) {
            Logger.getLogger(controlPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }}
        
        public void eliminarServicio(int id){
        try {
            servJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controlPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
//////////////////////////////////////////////////////PAQUETE///////////////////////////////////////////////
        public void crearPaquete(Paquete paquete)
        {
            try {
                paqJPA.create(paquete);
            } catch (Exception e) {
            }}
        public List<Paquete> getPaquetes()
        {
        return paqJPA.findPaqueteEntities();
        }
        public Paquete getPaqueteParticular(int id){
            return paqJPA.findPaquete(id);
        }
        
        public void updatePaquete(Paquete paquete){
        try {
            paqJPA.edit(paquete);
        } catch (Exception ex) {
            Logger.getLogger(controlPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        public void eliminarPaquete(int id){
        try {
            paqJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controlPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
        
    
    

