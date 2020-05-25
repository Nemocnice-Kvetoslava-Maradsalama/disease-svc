package nkm.disease;

import nkm.disease.model.Disease;
import nkm.disease.model.Symptom;
import nkm.disease.service.DiseaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DiseaseServiceTests {
    @Autowired
    private DiseaseService diseaseService;

    @Test
    void diseaseServiceCreate(){
        Disease disease = new Disease();
        disease.setName("Test disease");
        disease.setIcd10("F64");
        disease.setDescription("Description");
        diseaseService.persist(disease);
        Assertions.assertTrue(diseaseService.findAll().size() > 0);
    }

    @Test
    void diseaseServiceGet(){
        Disease disease = new Disease();
        disease.setName("Test disease");
        disease.setIcd10("F64");
        disease.setDescription("Description");
        diseaseService.persist(disease);
        Disease actual = diseaseService.find(disease.getId());
        Assertions.assertEquals(actual.getDescription(), disease.getDescription());
        Assertions.assertEquals(actual.getName(), disease.getName());
    }

    @Test
    void diseaseServiceUpdate(){
        Disease disease = new Disease();
        disease.setName("Test disease");
        disease.setIcd10("F64");
        disease.setDescription("Description");
        diseaseService.persist(disease);
        Disease update = new Disease();
        update.setName("Update disease");
        update.setDescription("Update description");
        update.setIcd10("F64.0");
        update.setId(disease.getId());
        diseaseService.update(update);
        Disease actual = diseaseService.find(update.getId());
        Assertions.assertEquals(actual.getDescription(), update.getDescription());
        Assertions.assertEquals(actual.getName(), update.getName());
    }

    @Test
    void diseaseServiceDelete(){
        Disease disease = new Disease();
        disease.setName("Test disease");
        disease.setIcd10("F64");
        disease.setDescription("Description");
        diseaseService.persist(disease);
        Disease actual = diseaseService.find(disease.getId());
        diseaseService.remove(actual);
        Assertions.assertNull(diseaseService.find(actual.getId()));
    }
}
