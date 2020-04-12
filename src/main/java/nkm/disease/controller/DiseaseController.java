package nkm.disease.controller;


import nkm.disease.model.Disease;
import nkm.disease.model.Symptom;
import nkm.disease.service.DiseaseService;
import nkm.disease.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void updateDisease(@RequestBody Disease entity){
        diseaseService.update(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteDisease(@PathVariable Long id){
        diseaseService.remove(diseaseService.find(id));
    }
}
