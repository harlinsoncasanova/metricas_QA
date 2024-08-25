package pruebatecnica.example.registro_metricas_QA.infraestructure.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebatecnica.example.registro_metricas_QA.api.dto.request.AplicationRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.ApplicationDTO;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.VersionDtoApp;
import pruebatecnica.example.registro_metricas_QA.domain.entities.ApplicationEntity;
import pruebatecnica.example.registro_metricas_QA.domain.entities.VersionEntity;
import pruebatecnica.example.registro_metricas_QA.domain.repositories.ApplicationRepository;
import pruebatecnica.example.registro_metricas_QA.exceptions.IdNotFoundException;
import pruebatecnica.example.registro_metricas_QA.infraestructure.abstrac_service.IApplicationService;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicationService  implements IApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;


    @Override
    public ApplicationDTO create(AplicationRequest request) {
        Optional<ApplicationEntity> existingApplication = applicationRepository.findByName(request.getName());
        if (existingApplication.isPresent()) {
            throw new IllegalArgumentException("Application with name '" + request.getName() + "' already exists.");
        }

        ApplicationEntity application = new ApplicationEntity();
        application.setName(request.getName());

        ApplicationEntity saved = applicationRepository.save(application);

        return toResponse(saved);
    }

    @Override
    public ApplicationDTO get(Long id) {
        ApplicationEntity application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        return toResponse(application);
    }

    @Override
    public ApplicationDTO update(AplicationRequest request, Long id) {
        ApplicationEntity application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setName(request.getName());
        ApplicationEntity updated = applicationRepository.save(application);

        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        if (!applicationRepository.existsById(id)) {
            throw new RuntimeException("Application not found");
        }
        applicationRepository.deleteById(id);
    }

    @Override
    public ApplicationDTO getById(Long id) {
        return this.toResponse(this.find(id));
    }
    private ApplicationEntity find(Long id) {
        return this.applicationRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("app"));
    }
    private ApplicationDTO toResponse(ApplicationEntity applicationEntity) {
        ApplicationDTO response = new ApplicationDTO();

        BeanUtils.copyProperties(applicationEntity, response);

        response.setVersions(applicationEntity.getVersions()
        .stream().map(version ->this.versionDtoApp(version))
                .collect(Collectors.toList()));

        return response;
    }
    private VersionDtoApp versionDtoApp(VersionEntity entity) {
        VersionDtoApp  response = new  VersionDtoApp ();
        BeanUtils.copyProperties(entity, response);

        return response;
    }


}
