package nkm.disease;

import nkm.disease.model.Disease;
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
    void diseaseServiceTest(){
        Disease disease = new Disease();
        disease.setName("Test disease");
        disease.setIcd10("F64");
        disease.setDescription("Description");
        diseaseService.persist(disease);
        Assertions.assertTrue(diseaseService.exists(disease.getId()));
        Disease foundDisease = diseaseService.find(disease.getId());
        Assertions.assertEquals(foundDisease.getId(), disease.getId());
        foundDisease.setName("Test disease update");
        diseaseService.update(foundDisease);
        List<Disease> diseases = diseaseService.findAll();
        diseaseService.remove(diseases.get(foundDisease.getId().intValue()));
        Assertions.assertNull(diseaseService.find(foundDisease.getId()));
    }
}
