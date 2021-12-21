/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Usuario;
import logica.Empleado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Administrador;
import logica.Venta;
import logica.Cliente;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Sergio Rodriguez
 */
public class AdministradorJpaController implements Serializable {

    public AdministradorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public AdministradorJpaController() {
        emf=Persistence.createEntityManagerFactory("TPOFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Administrador administrador) {
        if (administrador.getEmpleados() == null) {
            administrador.setEmpleados(new ArrayList<Empleado>());
        }
        if (administrador.getVentas() == null) {
            administrador.setVentas(new ArrayList<Venta>());
        }
        if (administrador.getClientes() == null) {
            administrador.setClientes(new ArrayList<Cliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = administrador.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdU());
                administrador.setUsuario(usuario);
            }
            List<Empleado> attachedEmpleados = new ArrayList<Empleado>();
            for (Empleado empleadosEmpleadoToAttach : administrador.getEmpleados()) {
                empleadosEmpleadoToAttach = em.getReference(empleadosEmpleadoToAttach.getClass(), empleadosEmpleadoToAttach.getIdEmpleado());
                attachedEmpleados.add(empleadosEmpleadoToAttach);
            }
            administrador.setEmpleados(attachedEmpleados);
            List<Venta> attachedVentas = new ArrayList<Venta>();
            for (Venta ventasVentaToAttach : administrador.getVentas()) {
                ventasVentaToAttach = em.getReference(ventasVentaToAttach.getClass(), ventasVentaToAttach.getIdV());
                attachedVentas.add(ventasVentaToAttach);
            }
            administrador.setVentas(attachedVentas);
            List<Cliente> attachedClientes = new ArrayList<Cliente>();
            for (Cliente clientesClienteToAttach : administrador.getClientes()) {
                clientesClienteToAttach = em.getReference(clientesClienteToAttach.getClass(), clientesClienteToAttach.getIdCliente());
                attachedClientes.add(clientesClienteToAttach);
            }
            administrador.setClientes(attachedClientes);
            em.persist(administrador);
            if (usuario != null) {
                Administrador oldAdminOfUsuario = usuario.getAdmin();
                if (oldAdminOfUsuario != null) {
                    oldAdminOfUsuario.setUsuario(null);
                    oldAdminOfUsuario = em.merge(oldAdminOfUsuario);
                }
                usuario.setAdmin(administrador);
                usuario = em.merge(usuario);
            }
            for (Empleado empleadosEmpleado : administrador.getEmpleados()) {
                Administrador oldAdminOfEmpleadosEmpleado = empleadosEmpleado.getAdmin();
                empleadosEmpleado.setAdmin(administrador);
                empleadosEmpleado = em.merge(empleadosEmpleado);
                if (oldAdminOfEmpleadosEmpleado != null) {
                    oldAdminOfEmpleadosEmpleado.getEmpleados().remove(empleadosEmpleado);
                    oldAdminOfEmpleadosEmpleado = em.merge(oldAdminOfEmpleadosEmpleado);
                }
            }
            for (Venta ventasVenta : administrador.getVentas()) {
                Administrador oldAdminOfVentasVenta = ventasVenta.getAdmin();
                ventasVenta.setAdmin(administrador);
                ventasVenta = em.merge(ventasVenta);
                if (oldAdminOfVentasVenta != null) {
                    oldAdminOfVentasVenta.getVentas().remove(ventasVenta);
                    oldAdminOfVentasVenta = em.merge(oldAdminOfVentasVenta);
                }
            }
            for (Cliente clientesCliente : administrador.getClientes()) {
                Administrador oldAdminOfClientesCliente = clientesCliente.getAdmin();
                clientesCliente.setAdmin(administrador);
                clientesCliente = em.merge(clientesCliente);
                if (oldAdminOfClientesCliente != null) {
                    oldAdminOfClientesCliente.getClientes().remove(clientesCliente);
                    oldAdminOfClientesCliente = em.merge(oldAdminOfClientesCliente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Administrador administrador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrador persistentAdministrador = em.find(Administrador.class, administrador.getIdA());
            Usuario usuarioOld = persistentAdministrador.getUsuario();
            Usuario usuarioNew = administrador.getUsuario();
            List<Empleado> empleadosOld = persistentAdministrador.getEmpleados();
            List<Empleado> empleadosNew = administrador.getEmpleados();
            List<Venta> ventasOld = persistentAdministrador.getVentas();
            List<Venta> ventasNew = administrador.getVentas();
            List<Cliente> clientesOld = persistentAdministrador.getClientes();
            List<Cliente> clientesNew = administrador.getClientes();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdU());
                administrador.setUsuario(usuarioNew);
            }
            List<Empleado> attachedEmpleadosNew = new ArrayList<Empleado>();
            for (Empleado empleadosNewEmpleadoToAttach : empleadosNew) {
                empleadosNewEmpleadoToAttach = em.getReference(empleadosNewEmpleadoToAttach.getClass(), empleadosNewEmpleadoToAttach.getIdEmpleado());
                attachedEmpleadosNew.add(empleadosNewEmpleadoToAttach);
            }
            empleadosNew = attachedEmpleadosNew;
            administrador.setEmpleados(empleadosNew);
            List<Venta> attachedVentasNew = new ArrayList<Venta>();
            for (Venta ventasNewVentaToAttach : ventasNew) {
                ventasNewVentaToAttach = em.getReference(ventasNewVentaToAttach.getClass(), ventasNewVentaToAttach.getIdV());
                attachedVentasNew.add(ventasNewVentaToAttach);
            }
            ventasNew = attachedVentasNew;
            administrador.setVentas(ventasNew);
            List<Cliente> attachedClientesNew = new ArrayList<Cliente>();
            for (Cliente clientesNewClienteToAttach : clientesNew) {
                clientesNewClienteToAttach = em.getReference(clientesNewClienteToAttach.getClass(), clientesNewClienteToAttach.getIdCliente());
                attachedClientesNew.add(clientesNewClienteToAttach);
            }
            clientesNew = attachedClientesNew;
            administrador.setClientes(clientesNew);
            administrador = em.merge(administrador);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.setAdmin(null);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                Administrador oldAdminOfUsuario = usuarioNew.getAdmin();
                if (oldAdminOfUsuario != null) {
                    oldAdminOfUsuario.setUsuario(null);
                    oldAdminOfUsuario = em.merge(oldAdminOfUsuario);
                }
                usuarioNew.setAdmin(administrador);
                usuarioNew = em.merge(usuarioNew);
            }
            for (Empleado empleadosOldEmpleado : empleadosOld) {
                if (!empleadosNew.contains(empleadosOldEmpleado)) {
                    empleadosOldEmpleado.setAdmin(null);
                    empleadosOldEmpleado = em.merge(empleadosOldEmpleado);
                }
            }
            for (Empleado empleadosNewEmpleado : empleadosNew) {
                if (!empleadosOld.contains(empleadosNewEmpleado)) {
                    Administrador oldAdminOfEmpleadosNewEmpleado = empleadosNewEmpleado.getAdmin();
                    empleadosNewEmpleado.setAdmin(administrador);
                    empleadosNewEmpleado = em.merge(empleadosNewEmpleado);
                    if (oldAdminOfEmpleadosNewEmpleado != null && !oldAdminOfEmpleadosNewEmpleado.equals(administrador)) {
                        oldAdminOfEmpleadosNewEmpleado.getEmpleados().remove(empleadosNewEmpleado);
                        oldAdminOfEmpleadosNewEmpleado = em.merge(oldAdminOfEmpleadosNewEmpleado);
                    }
                }
            }
            for (Venta ventasOldVenta : ventasOld) {
                if (!ventasNew.contains(ventasOldVenta)) {
                    ventasOldVenta.setAdmin(null);
                    ventasOldVenta = em.merge(ventasOldVenta);
                }
            }
            for (Venta ventasNewVenta : ventasNew) {
                if (!ventasOld.contains(ventasNewVenta)) {
                    Administrador oldAdminOfVentasNewVenta = ventasNewVenta.getAdmin();
                    ventasNewVenta.setAdmin(administrador);
                    ventasNewVenta = em.merge(ventasNewVenta);
                    if (oldAdminOfVentasNewVenta != null && !oldAdminOfVentasNewVenta.equals(administrador)) {
                        oldAdminOfVentasNewVenta.getVentas().remove(ventasNewVenta);
                        oldAdminOfVentasNewVenta = em.merge(oldAdminOfVentasNewVenta);
                    }
                }
            }
            for (Cliente clientesOldCliente : clientesOld) {
                if (!clientesNew.contains(clientesOldCliente)) {
                    clientesOldCliente.setAdmin(null);
                    clientesOldCliente = em.merge(clientesOldCliente);
                }
            }
            for (Cliente clientesNewCliente : clientesNew) {
                if (!clientesOld.contains(clientesNewCliente)) {
                    Administrador oldAdminOfClientesNewCliente = clientesNewCliente.getAdmin();
                    clientesNewCliente.setAdmin(administrador);
                    clientesNewCliente = em.merge(clientesNewCliente);
                    if (oldAdminOfClientesNewCliente != null && !oldAdminOfClientesNewCliente.equals(administrador)) {
                        oldAdminOfClientesNewCliente.getClientes().remove(clientesNewCliente);
                        oldAdminOfClientesNewCliente = em.merge(oldAdminOfClientesNewCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = administrador.getIdA();
                if (findAdministrador(id) == null) {
                    throw new NonexistentEntityException("The administrador with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrador administrador;
            try {
                administrador = em.getReference(Administrador.class, id);
                administrador.getIdA();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The administrador with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = administrador.getUsuario();
            if (usuario != null) {
                usuario.setAdmin(null);
                usuario = em.merge(usuario);
            }
            List<Empleado> empleados = administrador.getEmpleados();
            for (Empleado empleadosEmpleado : empleados) {
                empleadosEmpleado.setAdmin(null);
                empleadosEmpleado = em.merge(empleadosEmpleado);
            }
            List<Venta> ventas = administrador.getVentas();
            for (Venta ventasVenta : ventas) {
                ventasVenta.setAdmin(null);
                ventasVenta = em.merge(ventasVenta);
            }
            List<Cliente> clientes = administrador.getClientes();
            for (Cliente clientesCliente : clientes) {
                clientesCliente.setAdmin(null);
                clientesCliente = em.merge(clientesCliente);
            }
            em.remove(administrador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Administrador> findAdministradorEntities() {
        return findAdministradorEntities(true, -1, -1);
    }

    public List<Administrador> findAdministradorEntities(int maxResults, int firstResult) {
        return findAdministradorEntities(false, maxResults, firstResult);
    }

    private List<Administrador> findAdministradorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Administrador.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Administrador findAdministrador(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Administrador.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdministradorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Administrador> rt = cq.from(Administrador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
