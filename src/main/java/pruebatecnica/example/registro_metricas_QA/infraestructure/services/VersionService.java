package pruebatecnica.example.registro_metricas_QA.infraestructure.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pruebatecnica.example.registro_metricas_QA.api.dto.request.VersionRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.VersionDTO;
import pruebatecnica.example.registro_metricas_QA.domain.entities.ApplicationEntity;
import pruebatecnica.example.registro_metricas_QA.domain.entities.VersionEntity;
import pruebatecnica.example.registro_metricas_QA.domain.repositories.ApplicationRepository;
import pruebatecnica.example.registro_metricas_QA.domain.repositories.VersionRepository;
import pruebatecnica.example.registro_metricas_QA.infraestructure.abstrac_service.IVersionService;

@Service
@AllArgsConstructor
public class VersionService implements IVersionService {
    @Autowired
    private final ApplicationRepository applicationRepository;
    @Autowired
    private final VersionRepository versionRepository;


    @Override
    public VersionDTO create(VersionRequest request) {
        ApplicationEntity application = applicationRepository.findByName(request.getName())
                .orElseThrow(() -> new EntityNotFoundException("Aplicación no encontrada"));
        VersionEntity versionEntity = new VersionEntity();
        versionEntity.setVersionNumber(request.getVersionName());
        versionEntity.setApplicationEntity(application);

        VersionEntity saved = versionRepository.save(versionEntity);
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public VersionDTO get(Long id) {
        VersionEntity versionEntity = versionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Versión no encontrada con id: " + id));
        return toResponse(versionEntity);
    }

    @Override
    public VersionDTO update(VersionRequest request, Long id) {
        VersionEntity versionEntity = versionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Versión no encontrada con id: " + id));

        versionEntity.setVersionNumber(request.getVersionName());
        VersionEntity updated = versionRepository.save(versionEntity);
        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        if (!versionRepository.existsById(id)) {
            throw new EntityNotFoundException("Versión no encontrada con id: " + id);
        }
        versionRepository.deleteById(id);
    }

    private VersionDTO toResponse(VersionEntity versionEntity) {
        VersionDTO response = new VersionDTO();
        response.setId(Math.toIntExact(versionEntity.getId()));
        response.setVersionNumber(versionEntity.getVersionNumber());
        response.setNameApp(versionEntity.getApplicationEntity().getName());
        return response;
    }
}
