package pruebatecnica.example.registro_metricas_QA.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pruebatecnica.example.registro_metricas_QA.api.dto.request.AplicationRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.ApplicationDTO;
import pruebatecnica.example.registro_metricas_QA.infraestructure.abstrac_service.IApplicationService;

@RestController
@RequestMapping("/application")
@AllArgsConstructor
public class ApplicationController {

    @Autowired
    private final IApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationDTO>create(
            @Validated @RequestBody AplicationRequest applicationRequest){
        return ResponseEntity.ok(this.applicationService.create(applicationRequest));

    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<ApplicationDTO>get(@PathVariable Long id){
        return ResponseEntity.ok(this.applicationService.get(id));
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.applicationService.delete(id);

        return ResponseEntity.ok("Eliminado exitosamente");
    }


}
