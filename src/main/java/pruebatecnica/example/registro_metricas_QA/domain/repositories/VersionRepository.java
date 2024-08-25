package pruebatecnica.example.registro_metricas_QA.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pruebatecnica.example.registro_metricas_QA.domain.entities.ApplicationEntity;
import pruebatecnica.example.registro_metricas_QA.domain.entities.VersionEntity;

import java.util.Optional;

@Repository
public interface VersionRepository extends JpaRepository<VersionEntity, Long> {
   Optional<VersionEntity> findById(Long id);
   Optional<VersionEntity> findByVersionNumber(String versionNumber);

}
