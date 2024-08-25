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
public class AplicationRequest {
    @NotBlank(message = "El nombre de la aplicacion es requerido")
    private String name;


}
