package nkm.disease.dao;

import nkm.disease.model.Disease;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Objects;

@Repository
public class DiseaseDao {

    @PersistenceContext
    EntityManager em;

    public Disease find(Long id) {
        Objects.requireNonNull(id, "Parameter must be non-null");
        return em.find(Disease.class, id);
    }

    public List<Disease> findAll() {
        return em.createQuery("SELECT e FROM Disease e", Disease.class).getResultList();
    }

    public List<Disease> findByName(String name){
        Objects.requireNonNull(name);
        return em.createQuery("Select t FROM Disease t WHERE t.name = :us", Disease.class).setParameter("us", name).getResultList();
    }

    public void persist(Disease entity) {
        Objects.requireNonNull(entity, "Parameter must be non-null");
        try {
            em.persist(entity);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public Disease update(Disease entity) {
        Objects.requireNonNull(entity);
        try {
            return em.merge(entity);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void remove(Disease entity) {
        Objects.requireNonNull(entity);
        try {
            final Disease toRemove = em.merge(entity);
            if (toRemove != null) {
                em.remove(toRemove);
            }
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public boolean exists(Long id) {
        Objects.requireNonNull(id);
        return em.find(Disease.class, id) != null;
    }
}
