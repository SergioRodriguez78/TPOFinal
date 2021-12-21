/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Empleado;
import logica.Cliente;
import logica.Paquete;
import logica.Servicio;
import logica.Administrador;
import logica.Venta;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Sergio Rodriguez
 */
public class VentaJpaController implements Serializable {

    public VentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public VentaJpaController() {
        emf=Persistence.createEntityManagerFactory("TPOFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venta venta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado = venta.getEmpleado();
            if (empleado != null) {
                empleado = em.getReference(empleado.getClass(), empleado.getIdEmpleado());
                venta.setEmpleado(empleado);
            }
            Cliente cliente = venta.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getIdCliente());
                venta.setCliente(cliente);
            }
            Paquete paquete = venta.getPaquete();
            if (paquete != null) {
                paquete = em.getReference(paquete.getClass(), paquete.getCodigoP());
                venta.setPaquete(paquete);
            }
            Servicio servicio = venta.getServicio();
            if (servicio != null) {
                servicio = em.getReference(servicio.getClass(), servicio.getCodigoS());
                venta.setServicio(servicio);
            }
            Administrador admin = venta.getAdmin();
            if (admin != null) {
                admin = em.getReference(admin.getClass(), admin.getIdA());
                venta.setAdmin(admin);
            }
            em.persist(venta);
            if (empleado != null) {
                empleado.getVentas().add(venta);
                empleado = em.merge(empleado);
            }
            if (cliente != null) {
                cliente.getCompras().add(venta);
                cliente = em.merge(cliente);
            }
            if (paquete != null) {
                paquete.getVentasP().add(venta);
                paquete = em.merge(paquete);
            }
            if (servicio != null) {
                servicio.getVentas().add(venta);
                servicio = em.merge(servicio);
            }
            if (admin != null) {
                admin.getVentas().add(venta);
                admin = em.merge(admin);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getIdV());
            Empleado empleadoOld = persistentVenta.getEmpleado();
            Empleado empleadoNew = venta.getEmpleado();
            Cliente clienteOld = persistentVenta.getCliente();
            Cliente clienteNew = venta.getCliente();
            Paquete paqueteOld = persistentVenta.getPaquete();
            Paquete paqueteNew = venta.getPaquete();
            Servicio servicioOld = persistentVenta.getServicio();
            Servicio servicioNew = venta.getServicio();
            Administrador adminOld = persistentVenta.getAdmin();
            Administrador adminNew = venta.getAdmin();
            if (empleadoNew != null) {
                empleadoNew = em.getReference(empleadoNew.getClass(), empleadoNew.getIdEmpleado());
                venta.setEmpleado(empleadoNew);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getIdCliente());
                venta.setCliente(clienteNew);
            }
            if (paqueteNew != null) {
                paqueteNew = em.getReference(paqueteNew.getClass(), paqueteNew.getCodigoP());
                venta.setPaquete(paqueteNew);
            }
            if (servicioNew != null) {
                servicioNew = em.getReference(servicioNew.getClass(), servicioNew.getCodigoS());
                venta.setServicio(servicioNew);
            }
            if (adminNew != null) {
                adminNew = em.getReference(adminNew.getClass(), adminNew.getIdA());
                venta.setAdmin(adminNew);
            }
            venta = em.merge(venta);
            if (empleadoOld != null && !empleadoOld.equals(empleadoNew)) {
                empleadoOld.getVentas().remove(venta);
                empleadoOld = em.merge(empleadoOld);
            }
            if (empleadoNew != null && !empleadoNew.equals(empleadoOld)) {
                empleadoNew.getVentas().add(venta);
                empleadoNew = em.merge(empleadoNew);
            }
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getCompras().remove(venta);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getCompras().add(venta);
                clienteNew = em.merge(clienteNew);
            }
            if (paqueteOld != null && !paqueteOld.equals(paqueteNew)) {
                paqueteOld.getVentasP().remove(venta);
                paqueteOld = em.merge(paqueteOld);
            }
            if (paqueteNew != null && !paqueteNew.equals(paqueteOld)) {
                paqueteNew.getVentasP().add(venta);
                paqueteNew = em.merge(paqueteNew);
            }
            if (servicioOld != null && !servicioOld.equals(servicioNew)) {
                servicioOld.getVentas().remove(venta);
                servicioOld = em.merge(servicioOld);
            }
            if (servicioNew != null && !servicioNew.equals(servicioOld)) {
                servicioNew.getVentas().add(venta);
                servicioNew = em.merge(servicioNew);
            }
            if (adminOld != null && !adminOld.equals(adminNew)) {
                adminOld.getVentas().remove(venta);
                adminOld = em.merge(adminOld);
            }
            if (adminNew != null && !adminNew.equals(adminOld)) {
                adminNew.getVentas().add(venta);
                adminNew = em.merge(adminNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = venta.getIdV();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
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
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getIdV();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            Empleado empleado = venta.getEmpleado();
            if (empleado != null) {
                empleado.getVentas().remove(venta);
                empleado = em.merge(empleado);
            }
            Cliente cliente = venta.getCliente();
            if (cliente != null) {
                cliente.getCompras().remove(venta);
                cliente = em.merge(cliente);
            }
            Paquete paquete = venta.getPaquete();
            if (paquete != null) {
                paquete.getVentasP().remove(venta);
                paquete = em.merge(paquete);
            }
            Servicio servicio = venta.getServicio();
            if (servicio != null) {
                servicio.getVentas().remove(venta);
                servicio = em.merge(servicio);
            }
            Administrador admin = venta.getAdmin();
            if (admin != null) {
                admin.getVentas().remove(venta);
                admin = em.merge(admin);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
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

    public Venta findVenta(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
