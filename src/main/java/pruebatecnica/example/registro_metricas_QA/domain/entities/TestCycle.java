package pruebatecnica.example.registro_metricas_QA.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "cycle")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String cycleName;
    @Column(nullable = false)
    private String cycleDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "version_id")
    private Version version;

    @OneToMany(mappedBy = "testCycle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MetricEntity> metrics = new ArrayList<>();
}
