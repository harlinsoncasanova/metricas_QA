package pruebatecnica.example.registro_metricas_QA.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationVersionRequest {
    private String name;
    private String versionName;
}
