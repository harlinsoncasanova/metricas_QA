package pruebatecnica.example.registro_metricas_QA.api.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetricRequest {

    private String metricName;
    private Double metricValue;

}
