package pruebatecnica.example.registro_metricas_QA.infraestructure.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pruebatecnica.example.registro_metricas_QA.api.dto.request.TestCycleRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.MetricDTO;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.TestCycleDTO;
import pruebatecnica.example.registro_metricas_QA.domain.entities.ApplicationEntity;
import pruebatecnica.example.registro_metricas_QA.domain.entities.MetricEntity;
import pruebatecnica.example.registro_metricas_QA.domain.entities.TestCycle;
import pruebatecnica.example.registro_metricas_QA.domain.entities.VersionEntity;
import pruebatecnica.example.registro_metricas_QA.domain.repositories.ApplicationRepository;
import pruebatecnica.example.registro_metricas_QA.domain.repositories.MetricRepository;
import pruebatecnica.example.registro_metricas_QA.domain.repositories.TestCycleRepository;
import pruebatecnica.example.registro_metricas_QA.domain.repositories.VersionRepository;
import pruebatecnica.example.registro_metricas_QA.infraestructure.abstrac_service.ITestService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TestService  implements ITestService {

    private final TestCycleRepository testCycleRepository;
    private final VersionRepository versionRepository;

    private final ApplicationRepository applicationRepository;


    @Override
    public TestCycleDTO create(TestCycleRequest request) {
        if (request.getAplicacionEntity() == null || request.getAplicacionEntity().isEmpty()) {
            throw new RuntimeException("paso1");
        }
        if (request.getIdVersion() == null) {
            throw new RuntimeException("paso2");
        }
        ApplicationEntity applicationEntity = applicationRepository.findByName(request.getAplicacionEntity())
                .orElseThrow(() -> new RuntimeException("Application not found"));

        Optional<VersionEntity> versionOpt = versionRepository.findById(request.getIdVersion());
        VersionEntity version = versionOpt.orElseThrow(() -> new RuntimeException("Version not found"));

        if (!version.getApplicationEntity().equals(applicationEntity)) {
            throw new RuntimeException("Version does not belong to the specified application");
        }

        TestCycle testCycle = new TestCycle();
        testCycle.setCycleName(request.getCycleName());
        testCycle.setCycleDescription(request.getCycleDescription());
        testCycle.setVersionEntity(version);


        if (request.getMetrics() != null) {
            List<MetricEntity> metrics = request.getMetrics().stream()
                    .map(metricRequest -> {
                        MetricEntity metric = new MetricEntity();
                        metric.setMetricName(metricRequest.getMetricName());
                        metric.setMetricValue(metricRequest.getMetricValue());
                        metric.setTestCycle(testCycle); // Vincula la métrica con el ciclo de prueba
                        return metric;
                    })
                    .collect(Collectors.toList());
            testCycle.setMetrics(metrics);
        }

        TestCycle savedTestCycle = testCycleRepository.save(testCycle);

        return toResponse(savedTestCycle);
    }

    @Override
    public TestCycleDTO get(Long aLong) {
        return null;
    }

    @Override
    public TestCycleDTO update(TestCycleRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
    private TestCycleDTO toResponse(TestCycle testCycle) {
        TestCycleDTO response = new TestCycleDTO();
        response.setId(testCycle.getId());
        response.setAplicacionEntity(testCycle.getVersionEntity().getApplicationEntity().getName());
        response.setIdVersion(testCycle.getVersionEntity().getId());
        response.setCycleName(testCycle.getCycleName());
        response.setCycleDescription(testCycle.getCycleDescription());
        response.setMetrics(testCycle.getMetrics().stream()
                .map(metric -> {
                    MetricDTO dto = new MetricDTO();
                    dto.setId(metric.getId());
                    dto.setMetricName(metric.getMetricName());
                    dto.setMetricValue(metric.getMetricValue());
                    return dto;
                })
                .collect(Collectors.toList()));
        return response;
    }
}
