package pruebatecnica.example.registro_metricas_QA.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "metric")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetricEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String metricName;
    @Column(nullable = false)
    private double metricValue;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_cycle_id")
    private TestCycle testCycle;

}
