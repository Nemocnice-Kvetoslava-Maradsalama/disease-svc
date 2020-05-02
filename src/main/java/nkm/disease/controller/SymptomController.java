package nkm.disease.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nkm.disease.model.Disease;
import nkm.disease.model.Symptom;
import nkm.disease.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/symptom")
public class SymptomController {
    @Autowired
    private SymptomService symptomService;

    private final static Logger LOGGER = Logger.getLogger(SymptomController.class.getName());

    @ApiOperation(value = "Returns all symptoms.")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Symptom> getAllSymptoms(){
        LOGGER.info("Returning all symptoms.");
        return symptomService.findAll();
    }

    @ApiOperation(value = "Returns a single symptom based on an ID of the symptom.")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Symptom getSymptom(@PathVariable @ApiParam(value = "Symptom ID of type Long", required = true) Long id){
        LOGGER.info("Returning symptom (id = "+id+")");
        return symptomService.find(id);
    }

    @ApiOperation(value = "Adds a symptom into the database.")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Long addSymptom(@RequestBody @ApiParam(value = "Symptom JPA entity", required = true) Symptom entity){
        LOGGER.info("Persisting symptom.");
        symptomService.persist(entity);
        return entity.getId();
    }

    @ApiOperation(value = "Updates a symptom in the database. The parameter disease is matched with a symptom in the database based on the IDs.")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void updateSymptom(@RequestBody @ApiParam(value = "Symptom JPA entity", required = true) Symptom entity){
        LOGGER.info("Updating symptom (id = "+entity.getId()+").");
        symptomService.update(entity);
    }

    @ApiOperation(value = "Deletes a single symptom based on an ID of the symptom.")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSymptom(@PathVariable @ApiParam(value = "Symptom ID of type Long", required = true) Long id){
        LOGGER.info("Deleting symptom (id = "+id+").");
        symptomService.remove(symptomService.find(id));
    }
}
