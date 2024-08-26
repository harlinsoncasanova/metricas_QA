package pruebatecnica.example.registro_metricas_QA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pruebatecnica.example.registro_metricas_QA.api.dto.request.TestCycleRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.TestCycleDTO;
import pruebatecnica.example.registro_metricas_QA.domain.entities.Application;
import pruebatecnica.example.registro_metricas_QA.domain.entities.MetricEntity;
import pruebatecnica.example.registro_metricas_QA.domain.entities.TestCycle;
import pruebatecnica.example.registro_metricas_QA.domain.entities.Version;
import pruebatecnica.example.registro_metricas_QA.domain.repositories.ApplicationRepository;
import pruebatecnica.example.registro_metricas_QA.domain.repositories.TestCycleRepository;
import pruebatecnica.example.registro_metricas_QA.domain.repositories.VersionRepository;
import pruebatecnica.example.registro_metricas_QA.infraestructure.services.TestService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestServiceTest {

    @Mock
    private TestCycleRepository testCycleRepository;

    @Mock
    private VersionRepository versionRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private TestService testService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTestCycle() {
        TestCycleRequest request = new TestCycleRequest();
        request.setAplicacionEntity("Test App");
        request.setVersionName("1.0");
        request.setCycleName("Cycle 1");
        request.setCycleDescription("Test Cycle Description");

        Application application = new Application();
        application.setName("Test App");

        Version version = new Version();
        version.setVersionName("1.0");
        version.setApplication(application);

        when(applicationRepository.findByName("Test App")).thenReturn(Optional.of(application));
        when(versionRepository.findByVersionName("1.0")).thenReturn(Arrays.asList(version));

        TestCycle testCycle = new TestCycle();
        testCycle.setId(1L);
        testCycle.setCycleName("Cycle 1");
        testCycle.setCycleDescription("Test Cycle Description");
        testCycle.setVersion(version);

        when(testCycleRepository.save(any(TestCycle.class))).thenReturn(testCycle);

        TestCycleDTO result = testService.create(request);

        assertNotNull(result);
        assertEquals("Cycle 1", result.getCycleName());
        verify(testCycleRepository, times(1)).save(any(TestCycle.class));
    }

    @Test
    void testCalculateTestFailureRate() {
        MetricEntity executedMetric = new MetricEntity();
        executedMetric.setMetricName("Executed Test Cases");
        executedMetric.setMetricValue(10.0);

        MetricEntity failedMetric = new MetricEntity();
        failedMetric.setMetricName("Failed Test Cases");
        failedMetric.setMetricValue(2.0);

        List<MetricEntity> metrics = Arrays.asList(executedMetric, failedMetric);

        double failureRate = testService.calculateTestFailureRate(metrics);

        assertEquals(20.0, failureRate);
    }
}
