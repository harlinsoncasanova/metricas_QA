package pruebatecnica.example.registro_metricas_QA.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "version")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VersionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String versionNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private ApplicationEntity applicationEntity;

    @OneToMany(mappedBy = "versionEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<TestCycle> testCycles;



}
