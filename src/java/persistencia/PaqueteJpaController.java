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
import logica.Servicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Paquete;
import logica.Venta;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Sergio Rodriguez
 */
public class PaqueteJpaController implements Serializable {

    public PaqueteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public PaqueteJpaController() {
        emf=Persistence.createEntityManagerFactory("TPOFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paquete paquete) {
        if (paquete.getServicios() == null) {
            paquete.setServicios(new ArrayList<Servicio>());
        }
        if (paquete.getVentasP() == null) {
            paquete.setVentasP(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Servicio> attachedServicios = new ArrayList<Servicio>();
            for (Servicio serviciosServicioToAttach : paquete.getServicios()) {
                serviciosServicioToAttach = em.getReference(serviciosServicioToAttach.getClass(), serviciosServicioToAttach.getCodigoS());
                attachedServicios.add(serviciosServicioToAttach);
            }
            paquete.setServicios(attachedServicios);
            List<Venta> attachedVentasP = new ArrayList<Venta>();
            for (Venta ventasPVentaToAttach : paquete.getVentasP()) {
                ventasPVentaToAttach = em.getReference(ventasPVentaToAttach.getClass(), ventasPVentaToAttach.getIdV());
                attachedVentasP.add(ventasPVentaToAttach);
            }
            paquete.setVentasP(attachedVentasP);
            em.persist(paquete);
            for (Servicio serviciosServicio : paquete.getServicios()) {
                serviciosServicio.getPaquetes().add(paquete);
                serviciosServicio = em.merge(serviciosServicio);
            }
            for (Venta ventasPVenta : paquete.getVentasP()) {
                Paquete oldPaqueteOfVentasPVenta = ventasPVenta.getPaquete();
                ventasPVenta.setPaquete(paquete);
                ventasPVenta = em.merge(ventasPVenta);
                if (oldPaqueteOfVentasPVenta != null) {
                    oldPaqueteOfVentasPVenta.getVentasP().remove(ventasPVenta);
                    oldPaqueteOfVentasPVenta = em.merge(oldPaqueteOfVentasPVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paquete paquete) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paquete persistentPaquete = em.find(Paquete.class, paquete.getCodigoP());
            List<Servicio> serviciosOld = persistentPaquete.getServicios();
            List<Servicio> serviciosNew = paquete.getServicios();
            List<Venta> ventasPOld = persistentPaquete.getVentasP();
            List<Venta> ventasPNew = paquete.getVentasP();
            List<Servicio> attachedServiciosNew = new ArrayList<Servicio>();
            for (Servicio serviciosNewServicioToAttach : serviciosNew) {
                serviciosNewServicioToAttach = em.getReference(serviciosNewServicioToAttach.getClass(), serviciosNewServicioToAttach.getCodigoS());
                attachedServiciosNew.add(serviciosNewServicioToAttach);
            }
            serviciosNew = attachedServiciosNew;
            paquete.setServicios(serviciosNew);
            List<Venta> attachedVentasPNew = new ArrayList<Venta>();
            for (Venta ventasPNewVentaToAttach : ventasPNew) {
                ventasPNewVentaToAttach = em.getReference(ventasPNewVentaToAttach.getClass(), ventasPNewVentaToAttach.getIdV());
                attachedVentasPNew.add(ventasPNewVentaToAttach);
            }
            ventasPNew = attachedVentasPNew;
            paquete.setVentasP(ventasPNew);
            paquete = em.merge(paquete);
            for (Servicio serviciosOldServicio : serviciosOld) {
                if (!serviciosNew.contains(serviciosOldServicio)) {
                    serviciosOldServicio.getPaquetes().remove(paquete);
                    serviciosOldServicio = em.merge(serviciosOldServicio);
                }
            }
            for (Servicio serviciosNewServicio : serviciosNew) {
                if (!serviciosOld.contains(serviciosNewServicio)) {
                    serviciosNewServicio.getPaquetes().add(paquete);
                    serviciosNewServicio = em.merge(serviciosNewServicio);
                }
            }
            for (Venta ventasPOldVenta : ventasPOld) {
                if (!ventasPNew.contains(ventasPOldVenta)) {
                    ventasPOldVenta.setPaquete(null);
                    ventasPOldVenta = em.merge(ventasPOldVenta);
                }
            }
            for (Venta ventasPNewVenta : ventasPNew) {
                if (!ventasPOld.contains(ventasPNewVenta)) {
                    Paquete oldPaqueteOfVentasPNewVenta = ventasPNewVenta.getPaquete();
                    ventasPNewVenta.setPaquete(paquete);
                    ventasPNewVenta = em.merge(ventasPNewVenta);
                    if (oldPaqueteOfVentasPNewVenta != null && !oldPaqueteOfVentasPNewVenta.equals(paquete)) {
                        oldPaqueteOfVentasPNewVenta.getVentasP().remove(ventasPNewVenta);
                        oldPaqueteOfVentasPNewVenta = em.merge(oldPaqueteOfVentasPNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = paquete.getCodigoP();
                if (findPaquete(id) == null) {
                    throw new NonexistentEntityException("The paquete with id " + id + " no longer exists.");
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
            Paquete paquete;
            try {
                paquete = em.getReference(Paquete.class, id);
                paquete.getCodigoP();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paquete with id " + id + " no longer exists.", enfe);
            }
            List<Servicio> servicios = paquete.getServicios();
            for (Servicio serviciosServicio : servicios) {
                serviciosServicio.getPaquetes().remove(paquete);
                serviciosServicio = em.merge(serviciosServicio);
            }
            List<Venta> ventasP = paquete.getVentasP();
            for (Venta ventasPVenta : ventasP) {
                ventasPVenta.setPaquete(null);
                ventasPVenta = em.merge(ventasPVenta);
            }
            em.remove(paquete);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paquete> findPaqueteEntities() {
        return findPaqueteEntities(true, -1, -1);
    }

    public List<Paquete> findPaqueteEntities(int maxResults, int firstResult) {
        return findPaqueteEntities(false, maxResults, firstResult);
    }

    private List<Paquete> findPaqueteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paquete.class));
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

    public Paquete findPaquete(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paquete.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaqueteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paquete> rt = cq.from(Paquete.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
