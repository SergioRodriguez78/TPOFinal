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
import logica.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Paquete;
import logica.Servicio;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Sergio Rodriguez
 */
public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
     public ServicioJpaController() {
        emf=Persistence.createEntityManagerFactory("TPOFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        if (servicio.getVentas() == null) {
            servicio.setVentas(new ArrayList<Venta>());
        }
        if (servicio.getPaquetes() == null) {
            servicio.setPaquetes(new ArrayList<Paquete>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Venta> attachedVentas = new ArrayList<Venta>();
            for (Venta ventasVentaToAttach : servicio.getVentas()) {
                ventasVentaToAttach = em.getReference(ventasVentaToAttach.getClass(), ventasVentaToAttach.getIdV());
                attachedVentas.add(ventasVentaToAttach);
            }
            servicio.setVentas(attachedVentas);
            List<Paquete> attachedPaquetes = new ArrayList<Paquete>();
            for (Paquete paquetesPaqueteToAttach : servicio.getPaquetes()) {
                paquetesPaqueteToAttach = em.getReference(paquetesPaqueteToAttach.getClass(), paquetesPaqueteToAttach.getCodigoP());
                attachedPaquetes.add(paquetesPaqueteToAttach);
            }
            servicio.setPaquetes(attachedPaquetes);
            em.persist(servicio);
            for (Venta ventasVenta : servicio.getVentas()) {
                Servicio oldServicioOfVentasVenta = ventasVenta.getServicio();
                ventasVenta.setServicio(servicio);
                ventasVenta = em.merge(ventasVenta);
                if (oldServicioOfVentasVenta != null) {
                    oldServicioOfVentasVenta.getVentas().remove(ventasVenta);
                    oldServicioOfVentasVenta = em.merge(oldServicioOfVentasVenta);
                }
            }
            for (Paquete paquetesPaquete : servicio.getPaquetes()) {
                paquetesPaquete.getServicios().add(servicio);
                paquetesPaquete = em.merge(paquetesPaquete);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicio = em.find(Servicio.class, servicio.getCodigoS());
            List<Venta> ventasOld = persistentServicio.getVentas();
            List<Venta> ventasNew = servicio.getVentas();
            List<Paquete> paquetesOld = persistentServicio.getPaquetes();
            List<Paquete> paquetesNew = servicio.getPaquetes();
            List<Venta> attachedVentasNew = new ArrayList<Venta>();
            for (Venta ventasNewVentaToAttach : ventasNew) {
                ventasNewVentaToAttach = em.getReference(ventasNewVentaToAttach.getClass(), ventasNewVentaToAttach.getIdV());
                attachedVentasNew.add(ventasNewVentaToAttach);
            }
            ventasNew = attachedVentasNew;
            servicio.setVentas(ventasNew);
            List<Paquete> attachedPaquetesNew = new ArrayList<Paquete>();
            for (Paquete paquetesNewPaqueteToAttach : paquetesNew) {
                paquetesNewPaqueteToAttach = em.getReference(paquetesNewPaqueteToAttach.getClass(), paquetesNewPaqueteToAttach.getCodigoP());
                attachedPaquetesNew.add(paquetesNewPaqueteToAttach);
            }
            paquetesNew = attachedPaquetesNew;
            servicio.setPaquetes(paquetesNew);
            servicio = em.merge(servicio);
            for (Venta ventasOldVenta : ventasOld) {
                if (!ventasNew.contains(ventasOldVenta)) {
                    ventasOldVenta.setServicio(null);
                    ventasOldVenta = em.merge(ventasOldVenta);
                }
            }
            for (Venta ventasNewVenta : ventasNew) {
                if (!ventasOld.contains(ventasNewVenta)) {
                    Servicio oldServicioOfVentasNewVenta = ventasNewVenta.getServicio();
                    ventasNewVenta.setServicio(servicio);
                    ventasNewVenta = em.merge(ventasNewVenta);
                    if (oldServicioOfVentasNewVenta != null && !oldServicioOfVentasNewVenta.equals(servicio)) {
                        oldServicioOfVentasNewVenta.getVentas().remove(ventasNewVenta);
                        oldServicioOfVentasNewVenta = em.merge(oldServicioOfVentasNewVenta);
                    }
                }
            }
            for (Paquete paquetesOldPaquete : paquetesOld) {
                if (!paquetesNew.contains(paquetesOldPaquete)) {
                    paquetesOldPaquete.getServicios().remove(servicio);
                    paquetesOldPaquete = em.merge(paquetesOldPaquete);
                }
            }
            for (Paquete paquetesNewPaquete : paquetesNew) {
                if (!paquetesOld.contains(paquetesNewPaquete)) {
                    paquetesNewPaquete.getServicios().add(servicio);
                    paquetesNewPaquete = em.merge(paquetesNewPaquete);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = servicio.getCodigoS();
                if (findServicio(id) == null) {
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
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
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getCodigoS();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            List<Venta> ventas = servicio.getVentas();
            for (Venta ventasVenta : ventas) {
                ventasVenta.setServicio(null);
                ventasVenta = em.merge(ventasVenta);
            }
            List<Paquete> paquetes = servicio.getPaquetes();
            for (Paquete paquetesPaquete : paquetes) {
                paquetesPaquete.getServicios().remove(servicio);
                paquetesPaquete = em.merge(paquetesPaquete);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
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

    public Servicio findServicio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
