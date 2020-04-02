package nkm.disease.service;

import nkm.disease.dao.DiseaseDao;
import nkm.disease.dao.SymptomDao;
import nkm.disease.model.Disease;
import nkm.disease.model.Symptom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiseaseService {
    @Autowired
    private DiseaseDao d;

    @Transactional(readOnly = true)
    public Disease find(Long id) {
        return d.find(id);
    }

    @Transactional(readOnly = true)
    public List<Disease> findAll() {
        return d.findAll();
    }

    @Transactional
    public void persist(Disease entity) {
        d.persist(entity);
    }

    @Transactional
    public void update(Disease entity) {
        d.update(entity);
    }

    @Transactional
    public void remove(Disease entity) {
        d.remove(entity);
    }

    @Transactional(readOnly = true)
    public boolean exists(Long id) {
        return d.exists(id);
    }
}
