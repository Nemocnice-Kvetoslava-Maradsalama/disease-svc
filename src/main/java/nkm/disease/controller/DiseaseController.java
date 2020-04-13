package nkm.disease.controller;


import nkm.disease.model.Disease;
import nkm.disease.model.Symptom;
import nkm.disease.model.Symptoms;
import nkm.disease.service.DiseaseService;
import nkm.disease.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/disease")
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Disease getDisease(@PathVariable Long id){
        return diseaseService.find(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Long addDisease(@RequestBody Disease entity){
        diseaseService.persist(entity);
        return entity.getId();
    }

    @RequestMapping(value = "/addCure/", method = RequestMethod.POST)
    public void addCureToDisease(@RequestBody Long disease, @RequestBody Long cure){
        Disease d = diseaseService.find(disease);
        d.getCures().add(cure);
        diseaseService.update(d);
    }

    @RequestMapping(value = "/diagnose/", method = RequestMethod.POST)
    public List<Disease> diagnose(@RequestBody Symptoms symptoms){
        List<Disease> ds = diseaseService.findAll();
        TreeMap<Disease, Integer> match = new TreeMap<>(Collections.reverseOrder());
        List<Symptom> ss = symptoms.getSymptoms();
        for (Disease d : ds){
            Integer matches = 0;
            for (Symptom s : ss){
                if (d.getSymptoms().contains(s)) matches++;
            }
            match.put(d, matches);
        }
        List<Disease> result = new ArrayList<>();
        Iterator<Map.Entry<Disease, Integer>> res = match.entrySet().iterator();
        for (int i = 0; i < (Math.min(match.entrySet().size(), 10)); i++){
            Map.Entry<Disease, Integer> entry = res.next();
            if (entry.getValue() == 0) break;
            else result.add(entry.getKey());
        }
        return result;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void updateDisease(@RequestBody Disease entity){
        diseaseService.update(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteDisease(@PathVariable Long id){
        diseaseService.remove(diseaseService.find(id));
    }
}
