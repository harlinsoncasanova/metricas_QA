package pruebatecnica.example.registro_metricas_QA.api.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationVersionRequest {
    private String name;
    private String versionName;
}
