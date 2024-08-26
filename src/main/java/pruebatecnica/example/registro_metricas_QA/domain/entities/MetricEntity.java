package pruebatecnica.example.registro_metricas_QA.domain.entities;

import jakarta.persistence.*;


@Entity(name = "metric")

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public Double getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(Double metricValue) {
        this.metricValue = metricValue;
    }

    public TestCycle getTestCycle() {
        return testCycle;
    }

    public void setTestCycle(TestCycle testCycle) {
        this.testCycle = testCycle;
    }

}
