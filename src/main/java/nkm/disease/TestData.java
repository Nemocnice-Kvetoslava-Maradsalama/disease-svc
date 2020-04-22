package nkm.disease;

import nkm.disease.model.Disease;
import nkm.disease.model.Symptom;
import nkm.disease.service.DiseaseService;
import nkm.disease.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TestData {
    @Autowired
    private DiseaseService diseaseService;
    @Autowired
    private SymptomService symptomService;

    @PostConstruct
    private void setupData(){
        Symptom symptom0 = new Symptom();
        Symptom symptom1 = new Symptom();
        Symptom symptom2 = new Symptom();
        Symptom symptom3 = new Symptom();
        Symptom symptom4 = new Symptom();
        Symptom symptom5 = new Symptom();
        Symptom symptom6 = new Symptom();
        Symptom symptom7 = new Symptom();
        Symptom symptom8 = new Symptom();
        Symptom symptom9 = new Symptom();

        Disease disease0 = new Disease();
        Disease disease1 = new Disease();
        Disease disease2 = new Disease();
        Disease disease3 = new Disease();
        Disease disease4 = new Disease();

        symptom0.setDescription("Fever, also referred to as pyrexia, is defined as having a temperature above the normal range due to an increase in the body's temperature set point. There is not a single agreed-upon upper limit for normal temperature with sources using values between 37.2 and 38.3 °C (99.0 and 100.9 °F) in humans. The increase in set point triggers increased muscle contractions and causes a feeling of cold. This results in greater heat production and efforts to conserve heat. When the set point temperature returns to normal, a person feels hot, becomes flushed, and may begin to sweat. Rarely a fever may trigger a febrile seizure, with this being more common in young children. Fevers do not typically go higher than 41 to 42 °C (105.8 to 107.6 °F).");
        symptom0.setName("Fever");

        symptom1.setDescription("A cough is a sudden expulsion of air through the large breathing passages that can help clear them of fluids, irritants, foreign particles and microbes. As a protective reflex, coughing can be repetitive with the cough reflex following three phases: an inhalation, a forced exhalation against a closed glottis, and a violent release of air from the lungs following opening of the glottis, usually accompanied by a distinctive sound.");
        symptom1.setName("Cough");

        symptom2.setDescription("Fatigue is a subjective feeling of tiredness. It may be sudden or gradual in onset. It is a normal phenomenon if it follows prolonged physical or mental activity, and resolves completely with rest. It may be a symptom of one of many medical conditions if it prolonged, severe, progressive, or occurs without provocation.");
        symptom2.setName("Fatigue");

        symptom3.setDescription("Nausea is a diffuse sensation of unease and discomfort, often perceived as an urge to vomit. While not painful, it can be a debilitating symptom if prolonged, and has been described as placing discomfort on the chest, upper abdomen, or back of the throat.");
        symptom3.setName("Nausea");

        symptom4.setDescription("Diarrhea, also spelled diarrhoea, is the condition of having at least three loose, liquid, or watery bowel movements each day. It often lasts for a few days and can result in dehydration due to fluid loss. Signs of dehydration often begin with loss of the normal stretchiness of the skin and irritable behaviour. This can progress to decreased urination, loss of skin color, a fast heart rate, and a decrease in responsiveness as it becomes more severe. Loose but non-watery stools in babies who are exclusively breastfed, however, are normal.");
        symptom4.setName("Diarrhea");

        symptom5.setDescription("Constipation refers to bowel movements that are infrequent or hard to pass. The stool is often hard and dry. Other symptoms may include abdominal pain, bloating, and feeling as if one has not completely passed the bowel movement. Complications from constipation may include hemorrhoids, anal fissure or fecal impaction. The normal frequency of bowel movements in adults is between three per day and three per week. Babies often have three to four bowel movements per day while young children typically have two to three per day.");
        symptom5.setName("Constipation");

        symptom6.setDescription("Dizziness is an impairment in spatial perception and stability. The term dizziness is imprecise: it can refer to vertigo, presyncope, disequilibrium, or a non-specific feeling such as giddiness or foolishness.");
        symptom6.setName("Dizziness");

        symptom7.setDescription("In medicine, confusion is the quality or state of being bewildered or unclear. The term \"acute mental confusion\" is often used interchangeably with delirium in the International Statistical Classification of Diseases and Related Health Problems and the Medical Subject Headings publications to describe the pathology. These refer to the loss of orientation, or the ability to place oneself correctly in the world by time, location and personal identity. Mental confusion is sometimes accompanied by disordered consciousness (the loss of linear thinking) and memory loss (the inability to correctly recall previous events or learn new material).");
        symptom7.setName("Mental confusion");

        symptom8.setDescription("A rash is a change of the human skin which affects its color, appearance, or texture. A rash may be localized in one part of the body, or affect all the skin. Rashes may cause the skin to change color, itch, become warm, bumpy, chapped, dry, cracked or blistered, swell, and may be painful. The causes, and therefore treatments for rashes, vary widely. Diagnosis must take into account such things as the appearance of the rash, other symptoms, what the patient may have been exposed to, occupation, and occurrence in family members. The diagnosis may confirm any number of conditions. The presence of a rash may aid diagnosis; associated signs and symptoms are diagnostic of certain diseases. For example, the rash in measles is an erythematous, morbilliform, maculopapular rash that begins a few days after the fever starts. It classically starts at the head, and spreads downwards.");
        symptom8.setName("Rash");

        symptom9.setDescription("Depression is a state of low mood and aversion to activity. It can affect a person's thoughts, behavior, motivation, feelings, and sense of well-being. It may feature sadness, difficulty in thinking and concentration and a significant increase or decrease in appetite and time spent sleeping. People experiencing depression may have feelings of dejection, hopelessness and, sometimes, suicidal thoughts. It can either be short term or long term.");
        symptom9.setName("Depression");

        symptomService.persist(symptom0);
        symptomService.persist(symptom1);
        symptomService.persist(symptom2);
        symptomService.persist(symptom3);
        symptomService.persist(symptom4);
        symptomService.persist(symptom5);
        symptomService.persist(symptom6);
        symptomService.persist(symptom7);
        symptomService.persist(symptom8);
        symptomService.persist(symptom9);

        disease0.setDescription("Major depressive disorder (MDD), also known simply as depression, is a mental disorder characterized by at least two weeks of low mood that is present across most situations.");
        disease0.setIcd10("F33");
        disease0.setName("Major depressive disorder");
        disease0.setSymptoms(new ArrayList<>(Arrays.asList(symptom9)));

        disease1.setDescription("Coronavirus disease 2019 (COVID-19) is an infectious disease caused by severe acute respiratory syndrome coronavirus 2 (SARS-CoV-2). The disease was first identified in December 2019 in Wuhan, the capital of China's Hubei province, and has since spread globally, resulting in the ongoing 2019–20 coronavirus pandemic. The first confirmed case of what was then an unknown coronavirus was traced back to November 2019 in Hubei province. Common symptoms include fever, cough, and shortness of breath.");
        disease1.setIcd10("A08");
        disease1.setName("COVID-19");
        disease1.setSymptoms(new ArrayList<>(Arrays.asList(symptom0, symptom1)));

        disease2.setDescription("Irritable bowel syndrome (IBS) is a group of symptoms—including abdominal pain and changes in the pattern of bowel movements without any evidence of underlying damage. These symptoms occur over a long time, often years. It has been classified into four main types depending on whether diarrhea is common, constipation is common, both are common, or neither occurs very often (IBS-D, IBS-C, IBS-M, or IBS-U respectively).");
        disease2.setIcd10("K58");
        disease2.setName("Irritable Bowel Syndrome");
        disease2.setSymptoms(new ArrayList<>(Arrays.asList(symptom4,symptom5)));

        disease3.setDescription("Sepsis is a life-threatening condition that arises when the body's response to infection causes injury to its tissues and organs. Common signs and symptoms include fever, increased heart rate, increased breathing rate, and confusion. There may also be symptoms related to a specific infection, such as a cough with pneumonia, or painful urination with a kidney infection.");
        disease3.setIcd10("A40");
        disease3.setName("Sepsis");
        disease3.setSymptoms(new ArrayList<>(Arrays.asList(symptom0,symptom6, symptom7)));

        disease4.setDescription("Measles is a highly contagious infectious disease caused by measles virus. Symptoms usually develop 10–12 days after exposure to an infected person and last 7–10 days. Initial symptoms typically include fever, often greater than 40 °C (104 °F), cough, runny nose, and inflamed eyes.");
        disease4.setIcd10("B05");
        disease4.setName("Measles");
        disease4.setSymptoms(new ArrayList<>(Arrays.asList(symptom8, symptom0, symptom1)));

        diseaseService.persist(disease0);
        diseaseService.persist(disease1);
        diseaseService.persist(disease2);
        diseaseService.persist(disease3);
        diseaseService.persist(disease4);
    }
}
