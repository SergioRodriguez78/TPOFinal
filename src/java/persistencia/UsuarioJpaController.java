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
import logica.Administrador;
import logica.Usuario;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Sergio Rodriguez
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
     public UsuarioJpaController() {
       emf=Persistence.createEntityManagerFactory("TPOFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado = usuario.getEmpleado();
            if (empleado != null) {
                empleado = em.getReference(empleado.getClass(), empleado.getIdEmpleado());
                usuario.setEmpleado(empleado);
            }
            Administrador admin = usuario.getAdmin();
            if (admin != null) {
                admin = em.getReference(admin.getClass(), admin.getIdA());
                usuario.setAdmin(admin);
            }
            em.persist(usuario);
            if (empleado != null) {
                Usuario oldUsuarioOfEmpleado = empleado.getUsuario();
                if (oldUsuarioOfEmpleado != null) {
                    oldUsuarioOfEmpleado.setEmpleado(null);
                    oldUsuarioOfEmpleado = em.merge(oldUsuarioOfEmpleado);
                }
                empleado.setUsuario(usuario);
                empleado = em.merge(empleado);
            }
            if (admin != null) {
                Usuario oldUsuarioOfAdmin = admin.getUsuario();
                if (oldUsuarioOfAdmin != null) {
                    oldUsuarioOfAdmin.setAdmin(null);
                    oldUsuarioOfAdmin = em.merge(oldUsuarioOfAdmin);
                }
                admin.setUsuario(usuario);
                admin = em.merge(admin);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdU());
            Empleado empleadoOld = persistentUsuario.getEmpleado();
            Empleado empleadoNew = usuario.getEmpleado();
            Administrador adminOld = persistentUsuario.getAdmin();
            Administrador adminNew = usuario.getAdmin();
            if (empleadoNew != null) {
                empleadoNew = em.getReference(empleadoNew.getClass(), empleadoNew.getIdEmpleado());
                usuario.setEmpleado(empleadoNew);
            }
            if (adminNew != null) {
                adminNew = em.getReference(adminNew.getClass(), adminNew.getIdA());
                usuario.setAdmin(adminNew);
            }
            usuario = em.merge(usuario);
            if (empleadoOld != null && !empleadoOld.equals(empleadoNew)) {
                empleadoOld.setUsuario(null);
                empleadoOld = em.merge(empleadoOld);
            }
            if (empleadoNew != null && !empleadoNew.equals(empleadoOld)) {
                Usuario oldUsuarioOfEmpleado = empleadoNew.getUsuario();
                if (oldUsuarioOfEmpleado != null) {
                    oldUsuarioOfEmpleado.setEmpleado(null);
                    oldUsuarioOfEmpleado = em.merge(oldUsuarioOfEmpleado);
                }
                empleadoNew.setUsuario(usuario);
                empleadoNew = em.merge(empleadoNew);
            }
            if (adminOld != null && !adminOld.equals(adminNew)) {
                adminOld.setUsuario(null);
                adminOld = em.merge(adminOld);
            }
            if (adminNew != null && !adminNew.equals(adminOld)) {
                Usuario oldUsuarioOfAdmin = adminNew.getUsuario();
                if (oldUsuarioOfAdmin != null) {
                    oldUsuarioOfAdmin.setAdmin(null);
                    oldUsuarioOfAdmin = em.merge(oldUsuarioOfAdmin);
                }
                adminNew.setUsuario(usuario);
                adminNew = em.merge(adminNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuario.getIdU();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdU();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Empleado empleado = usuario.getEmpleado();
            if (empleado != null) {
                empleado.setUsuario(null);
                empleado = em.merge(empleado);
            }
            Administrador admin = usuario.getAdmin();
            if (admin != null) {
                admin.setUsuario(null);
                admin = em.merge(admin);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
