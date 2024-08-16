package pruebatecnica.example.registro_metricas_QA.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestCycleRequest {
    @NotBlank(message = "el nombre del ciclo es requerido")
    private String cycleName;
    @NotBlank(message = "la descripcion  es requerida")
    private String cycleDescription;
}
