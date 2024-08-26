package pruebatecnica.example.registro_metricas_QA.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebatecnica.example.registro_metricas_QA.api.dto.request.TestCycleRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.TestCycleDTO;

import pruebatecnica.example.registro_metricas_QA.domain.repositories.ApplicationRepository;
import pruebatecnica.example.registro_metricas_QA.domain.repositories.VersionRepository;
import pruebatecnica.example.registro_metricas_QA.infraestructure.abstrac_service.ITestService;

@RestController
@RequestMapping("/testCycle")
@RequiredArgsConstructor
public class TestController {
    private final ITestService testCycleService;
    private final VersionRepository versionRepository;
    private final ApplicationRepository applicationRepository;

    @PostMapping
    public ResponseEntity<TestCycleDTO> createTestCycle(@RequestBody TestCycleRequest request) {
        TestCycleDTO response = testCycleService.create(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
