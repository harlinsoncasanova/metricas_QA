package pruebatecnica.example.registro_metricas_QA.infraestructure.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebatecnica.example.registro_metricas_QA.api.dto.request.AplicationRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.ApplicationDTO;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.MetricDTO;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.TestCycleDTO;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.VersionDtoApp;
import pruebatecnica.example.registro_metricas_QA.domain.entities.Application;
import pruebatecnica.example.registro_metricas_QA.domain.entities.TestCycle;
import pruebatecnica.example.registro_metricas_QA.domain.entities.Version;
import pruebatecnica.example.registro_metricas_QA.domain.repositories.ApplicationRepository;
import pruebatecnica.example.registro_metricas_QA.exceptions.IdNotFoundException;
import pruebatecnica.example.registro_metricas_QA.infraestructure.abstrac_service.IApplicationService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicationService  implements IApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;


    @Override
    public ApplicationDTO create(AplicationRequest request) {
        Optional<Application> existingApplication = applicationRepository.findByName(request.getName());
        if (existingApplication.isPresent()) {
            throw new IllegalArgumentException("Application with name '" + request.getName() + "' already exists.");
        }

        Application application = new Application();
        application.setName(request.getName());

        Application saved = applicationRepository.save(application);

        return toResponse(saved);
    }

    @Override
    public ApplicationDTO get(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("paso1"));

        return toResponse(application);
    }

    @Override
    public ApplicationDTO update(AplicationRequest request, Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("paso2"));

        application.setName(request.getName());
        Application updated = applicationRepository.save(application);

        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        if (!applicationRepository.existsById(id)) {
            throw new RuntimeException("paso3");
        }
        applicationRepository.deleteById(id);
    }

    @Override
    public ApplicationDTO getById(Long id) {
        return this.toResponse(this.find(id));
    }

    private Application find(Long id) {
        return this.applicationRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("app"));
    }
    private ApplicationDTO toResponse(Application applicationEntity) {
        ApplicationDTO response = new ApplicationDTO();

        BeanUtils.copyProperties(applicationEntity, response);

        response.setVersions(applicationEntity.getVersions() != null
                ? applicationEntity.getVersions().stream()
                .map(this::versionDtoApp)
                .collect(Collectors.toList())
                : Collections.emptyList());

        return response;
    }
    private VersionDtoApp versionDtoApp(Version entity) {
        VersionDtoApp  response = new  VersionDtoApp ();
        BeanUtils.copyProperties(entity, response);

        return response;
    }

    @Override
    public List<TestCycleDTO> getTestCyclesByApplicationNameAndVersion(String name, String versionName) {
        Application application = applicationRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Application not found with name: " + name));

        application.getVersions().size();

        Version version = application.getVersions().stream()
                .filter(v -> v.getVersionName().equals(versionName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Version not found with name: " + versionName));

        version.getTestCycles().size();

        return version.getTestCycles().stream()
                .map(this::toTestCycleDTO)
                .collect(Collectors.toList());
    }

    private TestCycleDTO toTestCycleDTO(TestCycle testCycle) {
        TestCycleDTO response = new TestCycleDTO();
        response.setId(testCycle.getId());
        response.setAplicacionEntity(testCycle.getVersion().getApplication().getName());
        response.setVersionName(testCycle.getVersion().getVersionName());
        response.setCycleName(testCycle.getCycleName());
        response.setCycleDescription(testCycle.getCycleDescription());

        List<MetricDTO> metricDTOs = testCycle.getMetrics().stream()
                .map(metric -> {
                    MetricDTO dto = new MetricDTO();
                    BeanUtils.copyProperties(metric, dto);
                    return dto;
                })
                .collect(Collectors.toList());

        response.setMetrics(metricDTOs);
        return response;
    }


}
