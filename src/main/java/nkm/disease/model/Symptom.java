package nkm.disease.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Symptom {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @ManyToMany(mappedBy = "symptoms")
    List<Disease> diseases;

    public Symptom(){

    }

    public Symptom(String name, String description) {
        this.name = name;
        this.description = description;
        this.diseases = new ArrayList<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Disease> getDiseaseSymptoms() {
        return diseases;
    }

    public void setDiseaseSymptoms(List<Disease> diseaseSymptoms) {
        this.diseases = diseaseSymptoms;
    }
}
