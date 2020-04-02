package nkm.disease.dao;

import nkm.disease.model.Disease;
import nkm.disease.model.Symptom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Objects;

@Repository
public class SymptomDao {

    @PersistenceContext
    EntityManager em;

    public Symptom find(Long id) {
        Objects.requireNonNull(id, "Parameter must be non-null");
        return em.find(Symptom.class, id);
    }

    public List<Symptom> findAll() {
        return em.createQuery("SELECT e FROM Symptom e", Symptom.class).getResultList();
    }

    public List<Symptom> findByName(String name){
        Objects.requireNonNull(name);
        return em.createQuery("Select t FROM Symptom t WHERE t.name = :us", Symptom.class).setParameter("us", name).getResultList();
    }

    public void persist(Symptom entity) {
        Objects.requireNonNull(entity, "Parameter must be non-null");
        try {
            em.persist(entity);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public Symptom update(Symptom entity) {
        Objects.requireNonNull(entity);
        try {
            return em.merge(entity);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void remove(Symptom entity) {
        Objects.requireNonNull(entity);
        try {
            final Symptom toRemove = em.merge(entity);
            if (toRemove != null) {
                em.remove(toRemove);
            }
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public boolean exists(Long id) {
        Objects.requireNonNull(id);
        return em.find(Symptom.class, id) != null;
    }
}
