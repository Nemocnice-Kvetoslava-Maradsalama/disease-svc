package nkm.disease.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nkm.disease.authorization.AuthorizationService;
import nkm.disease.model.Disease;
import nkm.disease.model.DiseaseCure;
import nkm.disease.model.Symptom;
import nkm.disease.model.Symptoms;
import nkm.disease.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/disease")
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    @Autowired
    private AuthorizationService authorizationService;

    private final static Logger LOGGER = Logger.getLogger(DiseaseController.class.getName());

    @ApiOperation(value = "Returns all diseases.")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Disease> getAllDiseases(@RequestHeader(value="Authorization") String token){
        if (authorizationService.validateUser(token)){
            LOGGER.info("Returning all diseases.");
            return diseaseService.findAll();
        } else {
            throw new RuntimeException("Unauthorized request.");
        }
    }

    @ApiOperation(value = "Returns a single disease based on an ID of the disease.")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Disease getDisease(@PathVariable @ApiParam(required = true, value = "Disease ID of type Long") Long id, @RequestHeader(value="Authorization") String token){

        if (authorizationService.validateUser(token)){
            LOGGER.info("Returning disease (id = "+id+").");
            return diseaseService.find(id);
        } else {
            throw new RuntimeException("Unauthorized request.");
        }
    }

    @ApiOperation(value = "Adds a disease into the database.")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Long addDisease(@RequestBody @ApiParam(value = "Disease JPA entity to be added", required = true) Disease entity, @RequestHeader(value="Authorization") String token){

        if (authorizationService.validateUser(token)){
            LOGGER.info("Persisting disease into database.");
            diseaseService.persist(entity);
            return entity.getId();
        } else {
            throw new RuntimeException("Unauthorized request.");
        }
    }

    @ApiOperation(value = "Adds a cure of a certain disease to the database.")
    @RequestMapping(value = "/addCure/", method = RequestMethod.POST)
    public void addCureToDisease(@RequestBody @ApiParam(required = true, value = "Disease ID of type Long with cure ID of type Long") DiseaseCure diseaseCure, @RequestHeader(value="Authorization") String token){

        if (authorizationService.validateUser(token)){
            LOGGER.info("Persisting cure (id = "+diseaseCure.getCure()+") into disease (id = "+diseaseCure.getDisease()+").");
            Disease d = diseaseService.find(diseaseCure.getDisease());
            d.getCures().add(diseaseCure.getCure());
            diseaseService.update(d);
        } else {
            throw new RuntimeException("Unauthorized request.");
        }
    }

    @ApiOperation(value = "Returns an array of at most ten diseases, ordered in descending order by number of symptoms of a given disease that match the list of symptoms given in the parameter.")
    @RequestMapping(value = "/diagnose/", method = RequestMethod.POST)
    public List<Disease> diagnose(@RequestBody @ApiParam(required = true, value="Array of JPA symptom entities") Symptoms symptoms, @RequestHeader(value="Authorization") String token){

        if (authorizationService.validateUser(token)){
            String stream = symptoms.getSymptoms().stream().map(x -> x.getId().toString()).reduce("", (a,b) -> (a + " " + b));
            LOGGER.info("Diagnosing symptoms (id(s) = "+ stream +").");
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
        } else {
            throw new RuntimeException("Unauthorized request.");
        }
    }

    @ApiOperation(value = "Updates a disease in the database. The parameter disease is matched with a disease in the database based on the IDs.")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void updateDisease(@RequestBody @ApiParam(value = "Disease JPA entity", required = true) Disease entity, @RequestHeader(value="Authorization") String token){

        if (authorizationService.validateUser(token)){
            LOGGER.info("Updating disease (id = "+entity.getId()+").");
            diseaseService.update(entity);
        } else {
            throw new RuntimeException("Unauthorized request.");
        }
    }

    @ApiOperation(value = "Deletes a single disease based on an ID of the disease.")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteDisease(@PathVariable @ApiParam(required = true, value = "Disease ID of type Long") Long id, @RequestHeader(value="Authorization") String token){

        if (authorizationService.validateUser(token)){
            LOGGER.info("Deleting disease (id = "+id+").");
            diseaseService.remove(diseaseService.find(id));
        } else {
            throw new RuntimeException("Unauthorized request.");
        }
    }
}
