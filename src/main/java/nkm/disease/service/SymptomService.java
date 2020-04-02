package nkm.disease.service;

import nkm.disease.dao.SymptomDao;
import nkm.disease.model.Symptom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SymptomService {
    @Autowired
    private SymptomDao d;

    @Transactional(readOnly = true)
    public Symptom find(Long id) {
        return d.find(id);
    }

    @Transactional(readOnly = true)
    public List<Symptom> findAll() {
        return d.findAll();
    }

    @Transactional
    public void persist(Symptom entity) {
        d.persist(entity);
    }

    @Transactional
    public void update(Symptom entity) {
        d.update(entity);
    }

    @Transactional
    public void remove(Symptom entity) {
        d.remove(entity);
    }

    @Transactional(readOnly = true)
    public boolean exists(Long id) {
        return d.exists(id);
    }
}
