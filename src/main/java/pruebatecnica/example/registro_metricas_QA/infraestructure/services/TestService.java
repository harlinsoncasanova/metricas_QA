package pruebatecnica.example.registro_metricas_QA.infraestructure.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import pruebatecnica.example.registro_metricas_QA.api.dto.request.TestCycleRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.MetricDTO;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.TestCycleDTO;
import pruebatecnica.example.registro_metricas_QA.domain.entities.Application;
import pruebatecnica.example.registro_metricas_QA.domain.entities.MetricEntity;
import pruebatecnica.example.registro_metricas_QA.domain.entities.TestCycle;
import pruebatecnica.example.registro_metricas_QA.domain.entities.Version;
import pruebatecnica.example.registro_metricas_QA.domain.repositories.ApplicationRepository;
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
        if (request.getVersionName() == null) {
            throw new RuntimeException("paso2");
        }

        // Buscar la aplicación por nombre
        Application applicationEntity = applicationRepository.findByName(request.getAplicacionEntity())
                .orElseThrow(() -> new RuntimeException("Application not found"));

        // Buscar la versión por nombre
        List<Version> versions = versionRepository.findByVersionName(request.getVersionName());
        if (versions.isEmpty()) {
            throw new RuntimeException("Version not found");
        }

        // Buscar la versión que pertenece a la aplicación especificada
        Version version = versions.stream()
                .filter(v -> v.getApplication().equals(applicationEntity))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Version does not belong to the specified application"));

        // Crear un nuevo ciclo de prueba
        TestCycle testCycle = new TestCycle();
        testCycle.setCycleName(request.getCycleName());
        testCycle.setCycleDescription(request.getCycleDescription());
        testCycle.setVersion(version);

        // Configurar las métricas del ciclo de prueba
        if (request.getMetrics() != null) {
            List<MetricEntity> metrics = request.getMetrics().stream()
                    .map(metricRequest -> {
                        MetricEntity metric = new MetricEntity();
                        metric.setMetricName(metricRequest.getMetricName());
                        metric.setMetricValue(metricRequest.getMetricValue());
                        metric.setTestCycle(testCycle);
                        return metric;
                    })
                    .collect(Collectors.toList());
            testCycle.setMetrics(metrics);
        }

        // Guardar el ciclo de prueba y sus métricas en la base de datos
        TestCycle savedTestCycle = testCycleRepository.save(testCycle);

        // Crear y guardar las métricas calculadas
        double testFailureRate = calculateTestFailureRate(savedTestCycle.getMetrics());
        double averageTimePerTestCase = calculateAverageTimePerTestCase(savedTestCycle.getMetrics());

        MetricEntity failureRateMetric = new MetricEntity();
        failureRateMetric.setMetricName("Test Failure Rate");
        failureRateMetric.setMetricValue(testFailureRate);
        failureRateMetric.setTestCycle(savedTestCycle);

        MetricEntity averageTimeMetric = new MetricEntity();
        averageTimeMetric.setMetricName("Average Time per Test Case");
        averageTimeMetric.setMetricValue(averageTimePerTestCase);
        averageTimeMetric.setTestCycle(savedTestCycle);

        // Añadir las métricas calculadas al ciclo de prueba y guardar nuevamente
        savedTestCycle.getMetrics().add(failureRateMetric);
        savedTestCycle.getMetrics().add(averageTimeMetric);
        testCycleRepository.save(savedTestCycle);

        // Convertir el ciclo de prueba guardado en un DTO para la respuesta
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
        response.setAplicacionEntity(testCycle.getVersion().getApplication().getName());
        response.setVersionName(testCycle.getVersion().getVersionName());
        response.setCycleName(testCycle.getCycleName());
        response.setCycleDescription(testCycle.getCycleDescription());

        List<MetricDTO> metricDTOs = testCycle.getMetrics().stream()
                .map(metric -> {
                    MetricDTO dto = new MetricDTO();
                    dto.setId(metric.getId());
                    dto.setMetricName(metric.getMetricName());
                    dto.setMetricValue(metric.getMetricValue());
                    return dto;
                })
                .collect(Collectors.toList());

        response.setMetrics(metricDTOs);
        return response;
    }

    private double calculateTestFailureRate(List<MetricEntity> metrics) {
        double executedTests = metrics.stream()
                .filter(metric -> "Executed Test Cases".equals(metric.getMetricName()))
                .mapToDouble(MetricEntity::getMetricValue)
                .findFirst()
                .orElse(0.0);

        double failedTests = metrics.stream()
                .filter(metric -> "Failed Test Cases".equals(metric.getMetricName()))
                .mapToDouble(MetricEntity::getMetricValue)
                .findFirst()
                .orElse(0.0);

        return (executedTests > 0) ? (failedTests / executedTests) * 100 : 0.0;
    }

    private double calculateAverageTimePerTestCase(List<MetricEntity> metrics) {
        double executedTests = metrics.stream()
                .filter(metric -> "Executed Test Cases".equals(metric.getMetricName()))
                .mapToDouble(MetricEntity::getMetricValue)
                .findFirst()
                .orElse(0.0);

        double totalTime = metrics.stream()
                .filter(metric -> "Total Test Execution Time".equals(metric.getMetricName()))
                .mapToDouble(MetricEntity::getMetricValue)
                .findFirst()
                .orElse(0.0);

        return (executedTests > 0) ? totalTime / executedTests : 0.0;
    }
}
