package nkm.disease.controller;


import nkm.disease.model.Symptom;
import nkm.disease.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/symptom")
public class SymptomController {
    @Autowired
    private SymptomService symptomService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Symptom getSymptom(@PathVariable Long id){
        return symptomService.find(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Long addSymptom(@RequestBody Symptom entity){
        symptomService.persist(entity);
        return entity.getId();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void updateSymptom(@RequestBody Symptom entity){
        symptomService.update(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSymptom(@PathVariable Long id){
        symptomService.remove(symptomService.find(id));
    }
}
