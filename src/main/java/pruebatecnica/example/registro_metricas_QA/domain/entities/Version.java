package pruebatecnica.example.registro_metricas_QA.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String versionName;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @OneToMany(mappedBy = "version", fetch = FetchType.LAZY)
    private Set<TestCycle> testCycles = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Set<TestCycle> getTestCycles() {
        return testCycles;
    }

    public void setTestCycles(Set<TestCycle> testCycles) {
        this.testCycles = testCycles;
    }
}
