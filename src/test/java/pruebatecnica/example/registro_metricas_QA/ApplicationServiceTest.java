package pruebatecnica.example.registro_metricas_QA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pruebatecnica.example.registro_metricas_QA.api.dto.request.AplicationRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.ApplicationDTO;
import pruebatecnica.example.registro_metricas_QA.domain.entities.Application;
import pruebatecnica.example.registro_metricas_QA.domain.repositories.ApplicationRepository;
import pruebatecnica.example.registro_metricas_QA.infraestructure.abstrac_service.IApplicationService;
import pruebatecnica.example.registro_metricas_QA.infraestructure.services.ApplicationService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ApplicationService applicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetApplicationById() {
        Application application = new Application();
        application.setId(1L);
        application.setName("Test App");

        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));

        ApplicationDTO result = applicationService.get(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test App", result.getName());
        verify(applicationRepository, times(1)).findById(1L);
    }
}
