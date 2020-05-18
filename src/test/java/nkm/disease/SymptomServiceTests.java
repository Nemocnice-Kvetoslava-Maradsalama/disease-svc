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
    void symptomServiceTest(){
        Symptom symptom = new Symptom();
        symptom.setName("Test symptom");
        symptom.setDescription("Description");
        symptomService.persist(symptom);
        Assertions.assertTrue(symptomService.exists(symptom.getId()));
        Symptom foundSymptom = symptomService.find(symptom.getId());
        Assertions.assertEquals(foundSymptom.getId(), symptom.getId());
        foundSymptom.setName("Test symptom update");
        symptomService.update(foundSymptom);
        List<Symptom> diseases = symptomService.findAll();
        symptomService.remove(diseases.get(foundSymptom.getId().intValue()));
        Assertions.assertNull(symptomService.find(foundSymptom.getId()));
    }
}
