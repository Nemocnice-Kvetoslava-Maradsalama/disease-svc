package nkm.disease.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @Autowired
    private SymptomService symptomService;

    @ApiOperation(value = "Returns a single disease based on an ID of the disease.")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Disease getDisease(@PathVariable @ApiParam(required = true, value = "Disease ID of type Long") Long id){
        return diseaseService.find(id);
    }

    @ApiOperation(value = "Adds a disease into the database.")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Long addDisease(@RequestBody @ApiParam(value = "Disease JPA entity to be added", required = true) Disease entity){
        diseaseService.persist(entity);
        return entity.getId();
    }

    @ApiOperation(value = "Adds a cure of a certain disease to the database.")
    @RequestMapping(value = "/addCure/", method = RequestMethod.POST)
    public void addCureToDisease(@RequestBody @ApiParam(required = true, value = "Disease ID of type Long") Long disease, @RequestBody @ApiParam(required = true, value = "Cure ID of type Long") Long cure){
        Disease d = diseaseService.find(disease);
        d.getCures().add(cure);
        diseaseService.update(d);
    }

    @ApiOperation(value = "Returns an array of at most ten diseases, ordered in descending order by number of symptoms of a given disease that match the list of symptoms given in the parameter.")
    @RequestMapping(value = "/diagnose/", method = RequestMethod.POST)
    public List<Disease> diagnose(@RequestBody @ApiParam(required = true, value="Array of JPA symptom entities") Symptoms symptoms){
        List<Disease> ds = diseaseService.findAll();
        HashMap<Disease, Integer> match = new HashMap<>();
        List<Symptom> ss = symptoms.getSymptoms();
        for (Disease d : ds){
            Integer matches = 0;
            for (Symptom s : ss){
                List<Symptom> sms = d.getSymptoms();
                if (sms.contains(s)) matches++;
            }
            match.put(d, matches);
        }
        List<Disease> result = new ArrayList<>();
        List<Map.Entry<Disease, Integer>> list = new ArrayList<>(match.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        Iterator<Map.Entry<Disease, Integer>> res = list.iterator();
        for (int i = 0; i < (Math.min(match.entrySet().size(), 10)); i++){
            Map.Entry<Disease, Integer> entry = res.next();
            if (entry.getValue() == 0) break;
            else result.add(entry.getKey());
        }
        return result;
    }

    @ApiOperation(value = "Updates a disease in the database. The parameter disease is matched with a disease in the database based on the IDs.")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void updateDisease(@RequestBody @ApiParam(value = "Disease JPA entity", required = true) Disease entity){
        diseaseService.update(entity);
    }

    @ApiOperation(value = "Deletes a single disease based on an ID of the disease.")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteDisease(@PathVariable @ApiParam(required = true, value = "Disease ID of type Long") Long id){
        diseaseService.remove(diseaseService.find(id));
    }
}
