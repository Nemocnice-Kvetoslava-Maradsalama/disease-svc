package nkm.disease.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Disease {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "icd10", nullable = false)
    private String icd10;
    @Column(name = "description", nullable = false, length = 10485760)
    private String description;
    @ManyToMany
    @JoinTable(
            name = "disease_symptom",
            joinColumns = @JoinColumn(name = "disease_id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id"))
    List<Symptom> symptoms;
    @ElementCollection
    @CollectionTable(name="Cures", joinColumns = @JoinColumn(name="disease_id"))
    @Column(name="cure")
    private List<String> cures;

    public List<String> getCures() {
        return cures;
    }

    public void setCures(List<String> cures) {
        this.cures = cures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcd10() {
        return icd10;
    }

    public void setIcd10(String icd10) {
        this.icd10 = icd10;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Disease){
            Disease d = (Disease) o;
            return d.id.equals(this.id);
        } else return false;
    }
}
