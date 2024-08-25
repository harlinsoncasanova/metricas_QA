package pruebatecnica.example.registro_metricas_QA.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pruebatecnica.example.registro_metricas_QA.domain.entities.MetricEntity;

@Repository
public interface MetricRepository  extends JpaRepository<MetricEntity, Long> {
}
