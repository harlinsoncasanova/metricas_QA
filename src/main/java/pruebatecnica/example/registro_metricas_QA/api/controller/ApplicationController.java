package pruebatecnica.example.registro_metricas_QA.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pruebatecnica.example.registro_metricas_QA.api.dto.request.AplicationRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.AplicationResponse;
import pruebatecnica.example.registro_metricas_QA.infraestructure.abstrac_service.IApplicationService;

@RestController
@RequestMapping("/application")
@AllArgsConstructor
public class ApplicationController {

    @Autowired
    private final IApplicationService applicationService;

    @PostMapping
    public ResponseEntity<AplicationResponse>create(
            @Validated @RequestBody AplicationRequest applicationRequest){
        return ResponseEntity.ok(this.applicationService.create(applicationRequest));

    }

}
