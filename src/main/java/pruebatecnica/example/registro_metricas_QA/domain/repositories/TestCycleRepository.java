package pruebatecnica.example.registro_metricas_QA.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pruebatecnica.example.registro_metricas_QA.domain.entities.TestCycle;

@Repository
public interface TestCycleRepository extends JpaRepository<TestCycle, Long> {
}
