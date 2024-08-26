package pruebatecnica.example.registro_metricas_QA.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pruebatecnica.example.registro_metricas_QA.api.dto.request.VersionRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.VersionDTO;
import pruebatecnica.example.registro_metricas_QA.infraestructure.abstrac_service.IVersionService;

@RestController
@RequestMapping("/version")
@AllArgsConstructor
public class VersionController {
    @Autowired
    private final IVersionService versionService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid VersionRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        try {
            VersionDTO response = versionService.create(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VersionDTO> get(@PathVariable Long id) {
        try {
            VersionDTO response = versionService.get(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VersionDTO> update(@PathVariable Long id, @RequestBody VersionRequest request) {
        try {
            VersionDTO response = versionService.update(request, id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            versionService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
