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
import logica.Administrador;
import logica.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Empleado;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Sergio Rodriguez
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
     public EmpleadoJpaController() {
       emf=Persistence.createEntityManagerFactory("TPOFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        if (empleado.getVentas() == null) {
            empleado.setVentas(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = empleado.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdU());
                empleado.setUsuario(usuario);
            }
            Administrador admin = empleado.getAdmin();
            if (admin != null) {
                admin = em.getReference(admin.getClass(), admin.getIdA());
                empleado.setAdmin(admin);
            }
            List<Venta> attachedVentas = new ArrayList<Venta>();
            for (Venta ventasVentaToAttach : empleado.getVentas()) {
                ventasVentaToAttach = em.getReference(ventasVentaToAttach.getClass(), ventasVentaToAttach.getIdV());
                attachedVentas.add(ventasVentaToAttach);
            }
            empleado.setVentas(attachedVentas);
            em.persist(empleado);
            if (usuario != null) {
                Empleado oldEmpleadoOfUsuario = usuario.getEmpleado();
                if (oldEmpleadoOfUsuario != null) {
                    oldEmpleadoOfUsuario.setUsuario(null);
                    oldEmpleadoOfUsuario = em.merge(oldEmpleadoOfUsuario);
                }
                usuario.setEmpleado(empleado);
                usuario = em.merge(usuario);
            }
            if (admin != null) {
                admin.getEmpleados().add(empleado);
                admin = em.merge(admin);
            }
            for (Venta ventasVenta : empleado.getVentas()) {
                Empleado oldEmpleadoOfVentasVenta = ventasVenta.getEmpleado();
                ventasVenta.setEmpleado(empleado);
                ventasVenta = em.merge(ventasVenta);
                if (oldEmpleadoOfVentasVenta != null) {
                    oldEmpleadoOfVentasVenta.getVentas().remove(ventasVenta);
                    oldEmpleadoOfVentasVenta = em.merge(oldEmpleadoOfVentasVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getIdEmpleado());
            Usuario usuarioOld = persistentEmpleado.getUsuario();
            Usuario usuarioNew = empleado.getUsuario();
            Administrador adminOld = persistentEmpleado.getAdmin();
            Administrador adminNew = empleado.getAdmin();
            List<Venta> ventasOld = persistentEmpleado.getVentas();
            List<Venta> ventasNew = empleado.getVentas();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdU());
                empleado.setUsuario(usuarioNew);
            }
            if (adminNew != null) {
                adminNew = em.getReference(adminNew.getClass(), adminNew.getIdA());
                empleado.setAdmin(adminNew);
            }
            List<Venta> attachedVentasNew = new ArrayList<Venta>();
            for (Venta ventasNewVentaToAttach : ventasNew) {
                ventasNewVentaToAttach = em.getReference(ventasNewVentaToAttach.getClass(), ventasNewVentaToAttach.getIdV());
                attachedVentasNew.add(ventasNewVentaToAttach);
            }
            ventasNew = attachedVentasNew;
            empleado.setVentas(ventasNew);
            empleado = em.merge(empleado);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.setEmpleado(null);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                Empleado oldEmpleadoOfUsuario = usuarioNew.getEmpleado();
                if (oldEmpleadoOfUsuario != null) {
                    oldEmpleadoOfUsuario.setUsuario(null);
                    oldEmpleadoOfUsuario = em.merge(oldEmpleadoOfUsuario);
                }
                usuarioNew.setEmpleado(empleado);
                usuarioNew = em.merge(usuarioNew);
            }
            if (adminOld != null && !adminOld.equals(adminNew)) {
                adminOld.getEmpleados().remove(empleado);
                adminOld = em.merge(adminOld);
            }
            if (adminNew != null && !adminNew.equals(adminOld)) {
                adminNew.getEmpleados().add(empleado);
                adminNew = em.merge(adminNew);
            }
            for (Venta ventasOldVenta : ventasOld) {
                if (!ventasNew.contains(ventasOldVenta)) {
                    ventasOldVenta.setEmpleado(null);
                    ventasOldVenta = em.merge(ventasOldVenta);
                }
            }
            for (Venta ventasNewVenta : ventasNew) {
                if (!ventasOld.contains(ventasNewVenta)) {
                    Empleado oldEmpleadoOfVentasNewVenta = ventasNewVenta.getEmpleado();
                    ventasNewVenta.setEmpleado(empleado);
                    ventasNewVenta = em.merge(ventasNewVenta);
                    if (oldEmpleadoOfVentasNewVenta != null && !oldEmpleadoOfVentasNewVenta.equals(empleado)) {
                        oldEmpleadoOfVentasNewVenta.getVentas().remove(ventasNewVenta);
                        oldEmpleadoOfVentasNewVenta = em.merge(oldEmpleadoOfVentasNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = empleado.getIdEmpleado();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getIdEmpleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = empleado.getUsuario();
            if (usuario != null) {
                usuario.setEmpleado(null);
                usuario = em.merge(usuario);
            }
            Administrador admin = empleado.getAdmin();
            if (admin != null) {
                admin.getEmpleados().remove(empleado);
                admin = em.merge(admin);
            }
            List<Venta> ventas = empleado.getVentas();
            for (Venta ventasVenta : ventas) {
                ventasVenta.setEmpleado(null);
                ventasVenta = em.merge(ventasVenta);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
