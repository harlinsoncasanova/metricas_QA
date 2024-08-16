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
public class VersionRequest {
    @NotBlank(message = "la version  es requerida")
    private String VersionName;

}
