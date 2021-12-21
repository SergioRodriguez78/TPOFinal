/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import persistencia.controlPersistencia;

/**
 *
 * @author Sergio Rodriguez
 */
public class Control {

    controlPersistencia controladoraPersistencia = new controlPersistencia();



    public boolean verificarUsuario(String usuario, String contrasenia) {
        List<Usuario> usuarios = controladoraPersistencia.getUsuarios();
        if (usuarios != null) {
            for (Usuario u : usuarios) {
                if (u.getnUsuario().equals(usuario) && u.getContrasenia().equals(contrasenia)) {
                    return true;
                }
            }

        }
        return false;

    }

/////////////////////////////////////////////EMPLEADO////////////////////////////////////7
    public void crearEmpleado(String nombre, String apellido, String direccion, String dni, String celular, String email, String nombreUsu, String contrasenia, String cargo, double sueldo, boolean habilitado, Date fecha, byte[] foto) {
        Empleado empleado = new Empleado();
        Usuario usuario = new Usuario();

        usuario.setnUsuario(nombreUsu);
        usuario.setContrasenia(contrasenia);
        usuario.setFoto(foto);
        usuario.setHabilitado(habilitado);

        empleado.setUsuario(usuario);
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setCargo(cargo);
        empleado.setDireccion(direccion);
        empleado.setDni(dni);
        empleado.setCelular(celular);
        empleado.setEmail(email);
        empleado.setSueldo(sueldo);
        empleado.setFechaNac(fecha);
        empleado.setHabilitado(habilitado);

        controladoraPersistencia.crearUsuario(usuario);
        controladoraPersistencia.crearEmpleado(empleado);
        List<Administrador> admins = this.getAdministradores();
        List<Empleado> empleados = this.getEmpleados();
        for (Administrador a : admins) {
            a.setEmpleados(empleados);
            controladoraPersistencia.updateAdministrador(a);
        }

        usuario.setEmpleado(empleado);
        controladoraPersistencia.updateUsuario(usuario);
    }

    public List<Empleado> getEmpleados() {
        return controladoraPersistencia.getEmpleados();
    }

    public Empleado getEmpleadoParticular(int id) {
        return controladoraPersistencia.getEmpleadoParticular(id);
    }

    ////////////////////////////////////////ADMINISTRADOR////////////////////////////////////////////
    public void crearAdministrador(String nombre, String apellido, String direccion, String dni, String celular, String email, String nombreUsu, String contrasenia, boolean habilitado, byte[] foto, Date fecha) {
        Administrador admin = new Administrador();
        Usuario usuario = new Usuario();

        usuario.setnUsuario(nombreUsu);
        usuario.setContrasenia(contrasenia);
        usuario.setFoto(foto);
        usuario.setHabilitado(habilitado);
        admin.setUsuario(usuario);
        admin.setNombre(nombre);
        admin.setApellido(apellido);
        admin.setDireccion(direccion);
        admin.setDni(dni);
        admin.setCelular(celular);
        admin.setEmail(email);
        admin.setFechaNac(fecha);
        admin.setHabilitado(habilitado);

        admin.setEmpleados(this.getEmpleados());
        admin.setVentas(this.getVentas());
        admin.setClientes(this.getClientes());

        controladoraPersistencia.crearAdministrador(admin, usuario);
        usuario.setAdmin(admin);
        controladoraPersistencia.updateUsuario(usuario);
    }

    public List<Administrador> getAdministradores() {
        List<Administrador> admins= controladoraPersistencia.getAdministradores();
        List<Administrador> ad=new ArrayList<>();
        for(Administrador a:admins)
        {
            if(a.isHabilitado())
            {
                ad.add(a);
            }
        }
        return  ad;
    }

    public Administrador getAdministradorParticular(int id) {
        return controladoraPersistencia.getAdministradorParticular(id);
    }
    /////////////////////////////VENTA////////////////////////////////////////////

    public List<Venta> getVentas() {
        List<Venta> ventas= controladoraPersistencia.getVentas();
        List<Venta> ad=new ArrayList<>();
        for(Venta a:ventas)
        {
            if(a.isHabilitado())
            {
                ad.add(a);
            }
        }
        return  ad;
    }

    public Venta getVentaParticular(int id) {
        return controladoraPersistencia.getVentaParticular(id);
    }
    public void updateVenta(Venta venta, Empleado empleado, Paquete p) {
       
        controladoraPersistencia.updatePaquete(p);
       controladoraPersistencia.updateVenta(venta);
       
        List<Administrador> admins = this.getAdministradores();
        List<Venta> v = this.getVentas();
        List<Cliente> c = this.getClientes();
        for (Administrador a : admins) {
            a.setVentas(v);
            a.setClientes(c);

            controladoraPersistencia.updateAdministrador(a);

        }
         List<Venta> ventaE= this.getVentas();
        List<Venta> ventaA= new ArrayList<>();
        for(Venta ve: ventaE)
        {
            if(ve.getEmpleado().getIdEmpleado()==empleado.getIdEmpleado())
            {
                    ventaA.add(ve);
            }
            
            
        }
        empleado.setVentas(ventaA);
        controladoraPersistencia.updateEpleado(empleado);
              
            
            
      
    }

    public void crearVentaServicio(Servicio servicio, String Destino, Date fechaS, String nombre, String apellido, String direccion, String dni, String celular, String email, Date fechaN, String pago, Empleado empleado) {
        boolean habilitado = true;
        servicio.setDestino(Destino);
        servicio.setFechaS(fechaS);
        servicio.setHabilitado(habilitado);

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDireccion(direccion);
        cliente.setDni(dni);
        cliente.setCelular(celular);
        cliente.setEmail(email);
        cliente.setFechaNac(fechaN);
        cliente.setHabilitado(habilitado);

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setEmpleado(empleado);
        venta.setServicio(servicio);
        venta.setHabilitado(habilitado);
        venta.setTipoPago(pago);

        controladoraPersistencia.crearServicio(servicio);
        controladoraPersistencia.crearCliente(cliente);
        controladoraPersistencia.crearVenta(venta);

           List<Venta> ventaE= this.getVentas();
        List<Venta> ventaA= new ArrayList<>();
        for(Venta ve: ventaE)
        {
            if(ve.getEmpleado().getIdEmpleado()==empleado.getIdEmpleado())
            {
                    ventaA.add(ve);
            }
            
            
        }
        empleado.setVentas(ventaA);
        controladoraPersistencia.updateEpleado(empleado);
           

    }

    public void crearVentaPaquete(String nombre, String apellido, String direccion, String dni, String celular, String email, String pago, Date fechaN, Empleado empleado, List<Servicio> servicios) {
        boolean habilitado = true;
        Paquete paq = new Paquete();
        paq.setServicios(servicios);
        paq.setCosto(this.calcularCostoPaquete(servicios));
        paq.setHabilitado(habilitado);

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDireccion(direccion);
        cliente.setDni(dni);
        cliente.setCelular(celular);
        cliente.setEmail(email);
        cliente.setFechaNac(fechaN);
        cliente.setHabilitado(habilitado);

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setPaquete(paq);
        venta.setEmpleado(empleado);
        venta.setTipoPago(pago);
        venta.setHabilitado(habilitado);

        controladoraPersistencia.crearPaquete(paq);
        controladoraPersistencia.crearCliente(cliente);
        controladoraPersistencia.crearVenta(venta);

        List<Administrador> admins = this.getAdministradores();
        List<Venta> v = this.getVentas();
        List<Cliente> c = this.getClientes();
        for (Administrador a : admins) {
            a.setVentas(v);
            a.setClientes(c);

       
      
            controladoraPersistencia.updateAdministrador(a);

        }
        
        List<Venta> ventaE= this.getVentas();
        List<Venta> ventaA= new ArrayList<>();
        for(Venta ve: ventaE)
        {
            if(ve.getEmpleado().getIdEmpleado()==empleado.getIdEmpleado())
            {
                    ventaA.add(ve);
            }
            
            
        }
        empleado.setVentas(ventaA);
        controladoraPersistencia.updateEpleado(empleado);
      
        
    }
    

    ///////////////////////////////////CLIENTE///////////////////////////
    public List<Cliente> getClientes() {
        List<Cliente> clientes= controladoraPersistencia.getClientes();
        List<Cliente> ad=new ArrayList<>();
        for(Cliente a:clientes)
        {
            if(a.isHabilitado())
            {
                ad.add(a);
            }
        }
        return  ad;
      
    }

    public Cliente getClienteParticular(int id) {
        return controladoraPersistencia.getClienteParticular(id);
    }
    public void updateCliente(Cliente cliente)
    {
        controladoraPersistencia.updateCliente(cliente);
    }

    //////////////////////////////////USUARIO//////////////////////////////
    public List<Usuario> getUsuarios() {
        List<Usuario> usus= controladoraPersistencia.getUsuarios();
        List<Usuario> ad=new ArrayList<>();
        for(Usuario a:usus)
        {
            if(a.isHabilitado())
            {
                ad.add(a);
            }
        }
        return  ad;
    }

    public Usuario getUsuarioParticular(int id) {
        return controladoraPersistencia.getUsuarioParticular(id);
    }

    public int getIdUsuarioByName(String nombreUsuario) {
        List<Usuario> usu = controladoraPersistencia.getUsuarios();
        int id = 0;
        for (Usuario u : usu) {
            if (nombreUsuario.equals(u.getnUsuario())) {
                id = u.getIdU();
            }
        }
        return id;
    }
    public void borrarCliente(int id) {
        controladoraPersistencia.eliminarCliente(id);
        
    }
    public void modificarCliente(String nombre, String apellido, String direccion, String dni, String celular, String email, int id, Date fechaN) {
      Cliente cliente = controladoraPersistencia.getClienteParticular(id);
      cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDireccion(direccion);
        cliente.setDni(dni);
        cliente.setCelular(celular);
        cliente.setEmail(email);
        cliente.setFechaNac(fechaN);
        
        controladoraPersistencia.updateCliente(cliente);
    }
    
    public Cliente getClienteByDni(String dni)
    {
            List<Cliente> clientes= this.getClientes();
            Cliente c= null;
            for(Cliente a: clientes)
            {
                    if(a.getDni().equals(dni)){
                        c=a;
                    }
            }
            
            return c;
    }

    ////////////////////////////////////SERVICIO/////////////////////////////////////
    public Servicio getServicioParticular(int id) {
        return controladoraPersistencia.getServicioParticular(id);
    }

    public List<Servicio> getServicios() {
        List<Servicio> servs = controladoraPersistencia.getServicios();
        List<Servicio> ad=new ArrayList<>();
        for(Servicio a:servs)
        {
            if(a.isHabilitado())
            {
                ad.add(a);
            }
        }
        return  ad;
    }

    public Servicio hallarServicioDeseado(String[] servicios) {
        Servicio ser = null;
        List<Servicio> s = this.getServicios();
        for (String ss : servicios) {
            for (Servicio a : s) {
                if (ss.equals(String.valueOf(a.getCodigoS()))) {
                    ser = a;
                }
            }

        }
        return ser;
    }

    ////////////////////////////////////////////////////////////PAQUETE/////////////////////////////////
    public Paquete getPaqueteParticular(int id) {
        return controladoraPersistencia.getPaqueteParticular(id);
    }

    public List<Paquete> getPaquetes() {
       List<Paquete> pqs= controladoraPersistencia.getPaquetes();
        List<Paquete> ad=new ArrayList<>();
        for(Paquete a:pqs)
        {
            if(a.isHabilitado())
            {
                ad.add(a);
            }
        }
        return  ad;
    }

    public List<Servicio> hallarServicios(String[] paquete) {
        List<Servicio> ser = new ArrayList<>();

        List<Servicio> s = controladoraPersistencia.getServicios();

        for (int i = 0; i < paquete.length; i++) {
            for (Servicio a : s) {
                if (paquete[i].equals(String.valueOf(a.getCodigoS()))) {
                    ser.add(a);
                }

            }

        }
        return ser;
    }

    public double calcularCostoPaquete(List<Servicio> servicios) {
        Paquete p = new Paquete();
        return p.calcularCostoPaquete(servicios);
    }

    public String convertiirFecha(Date fecha) {
        String pattern = "yyyy-MM-dd";
        String date="";
        try {
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        date = simpleDateFormat.format(new Date());
        } catch (Exception e) {
            
        }
      

        return date;
    }

    public void borrarVenta(Venta venta) {
        
        controladoraPersistencia.eliminarVenta(venta.getIdV());
    }

    public void crearVentaDS(Cliente cl, Servicio ser, Date fechaS, String destino, Empleado empleado, String pago) {
     
       boolean habilitado= true;
       ser.setDestino(destino);
     ser.setFechaS(fechaS);
     ser.setHabilitado(habilitado);
     
        Venta venta= new Venta();
        venta.setHabilitado(habilitado);
        venta.setCliente(cl);
        venta.setEmpleado(empleado);
        venta.setServicio(ser);
        venta.setTipoPago(pago);
        
        controladoraPersistencia.crearServicio(ser);
        controladoraPersistencia.crearVenta(venta);
        
        List<Administrador> admins = this.getAdministradores();
        List<Venta> v = this.getVentas();
        List<Cliente> c = this.getClientes();
        for (Administrador a : admins) {
            a.setVentas(v);
            a.setClientes(c);

       
      
            controladoraPersistencia.updateAdministrador(a);

        }
        
        List<Venta> ventaE= this.getVentas();
        List<Venta> ventaA= new ArrayList<>();
        for(Venta ve: ventaE)
        {
            if(ve.getEmpleado().getIdEmpleado()==empleado.getIdEmpleado())
            {
                    ventaA.add(ve);
            }
            
            
        }
        empleado.setVentas(ventaA);
        controladoraPersistencia.updateEpleado(empleado);
      
       
        
    }

    public void crearVentaDP(Cliente cl, String pago, Empleado empleado, List<Servicio> servicios) {
         boolean habilitado = true;
        Paquete paq = new Paquete();
        paq.setServicios(servicios);
        paq.setCosto(this.calcularCostoPaquete(servicios));
        paq.setHabilitado(habilitado);
        
         Venta venta = new Venta();
        venta.setCliente(cl);
        venta.setPaquete(paq);
        venta.setEmpleado(empleado);
        venta.setTipoPago(pago);
        venta.setHabilitado(habilitado);
        
        controladoraPersistencia.crearPaquete(paq);
        controladoraPersistencia.crearVenta(venta);
        
        List<Administrador> admins = this.getAdministradores();
        List<Venta> v = this.getVentas();
        List<Cliente> c = this.getClientes();
        for (Administrador a : admins) {
            a.setVentas(v);
            a.setClientes(c);

       
      
            controladoraPersistencia.updateAdministrador(a);

        }
        
          List<Venta> ventaE= this.getVentas();
        List<Venta> ventaA= new ArrayList<>();
        for(Venta ve: ventaE)
        {
            if(ve.getEmpleado().getIdEmpleado()==empleado.getIdEmpleado())
            {
                    ventaA.add(ve);
            }
            
            
        }
        empleado.setVentas(ventaA);
        controladoraPersistencia.updateEpleado(empleado);
    }

    public void updateEmpleado(Empleado emple, Usuario usu) {
        controladoraPersistencia.updateUsuario(usu);
       controladoraPersistencia.updateEpleado(emple);
       
    }

    

   
    

    

}
