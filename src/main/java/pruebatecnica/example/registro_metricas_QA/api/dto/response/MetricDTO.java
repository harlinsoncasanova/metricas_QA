package pruebatecnica.example.registro_metricas_QA.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetricDTO {

    private Long id;
    private String metricName;
    private Double metricValue;


}
