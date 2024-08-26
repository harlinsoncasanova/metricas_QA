package pruebatecnica.example.registro_metricas_QA.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pruebatecnica.example.registro_metricas_QA.domain.entities.Version;

import java.util.List;
import java.util.Optional;

@Repository
public interface VersionRepository extends JpaRepository<Version, Long> {
   Optional<Version> findById(Long id);
   List<Version> findByVersionName(String versionName);

}


