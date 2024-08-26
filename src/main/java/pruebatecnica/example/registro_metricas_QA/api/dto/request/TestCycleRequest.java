package pruebatecnica.example.registro_metricas_QA.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestCycleRequest {
    @NotBlank(message = "el nombre del aplicacion es requerido")
    private String aplicacionEntity;
    @NotBlank(message = "el nombre del version es requerido")
    private String  versionName;
    @NotBlank(message = "el nombre del ciclo es requerido")
    private String cycleName;
    @NotBlank(message = "la descripcion  es requerida")
    private String cycleDescription;
    private List<MetricRequest> metrics;
}
