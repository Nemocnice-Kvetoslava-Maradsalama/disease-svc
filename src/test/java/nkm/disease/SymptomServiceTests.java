package nkm.disease;

import nkm.disease.model.Symptom;
import nkm.disease.service.SymptomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SymptomServiceTests {

    @Autowired
    private SymptomService symptomService;

    @Test
    void symptomServiceCreate(){
        Symptom symptom = new Symptom();
        symptom.setName("Test symptom");
        symptom.setDescription("Description");
        symptomService.persist(symptom);
        Assertions.assertTrue(symptomService.findAll().size() > 0);
    }

    @Test
    void symptomServiceGet(){
        Symptom symptom = new Symptom();
        symptom.setName("Test symptom");
        symptom.setDescription("Description");
        symptomService.persist(symptom);
        Symptom actual = symptomService.find(symptom.getId());
        Assertions.assertEquals(actual.getDescription(), symptom.getDescription());
        Assertions.assertEquals(actual.getName(), symptom.getName());
    }

    @Test
    void symptomServiceUpdate(){
        Symptom symptom = new Symptom();
        symptom.setName("Test symptom");
        symptom.setDescription("Description");
        symptomService.persist(symptom);
        Symptom update = new Symptom();
        update.setName("Update symptom");
        update.setDescription("Update description");
        update.setId(symptom.getId());
        symptomService.update(update);
        Symptom actual = symptomService.find(update.getId());
        Assertions.assertEquals(actual.getDescription(), update.getDescription());
        Assertions.assertEquals(actual.getName(), update.getName());
    }

    @Test
    void symptomServiceDelete(){
        Symptom symptom = new Symptom();
        symptom.setName("Test symptom");
        symptom.setDescription("Description");
        symptomService.persist(symptom);
        Symptom actual = symptomService.find(symptom.getId());
        symptomService.remove(actual);
        Assertions.assertNull(symptomService.find(actual.getId()));
    }
}
