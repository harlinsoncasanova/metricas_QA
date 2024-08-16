package pruebatecnica.example.registro_metricas_QA.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestCycleResponse {
    private Long id;
    private String applicationName;
    private String versionName;
    private String testCycleName;
    private BigDecimal metric1;
    private BigDecimal metric2;
    private BigDecimal metric3;
    private BigDecimal calculatedMetric1;
    private BigDecimal calculatedMetric2;
}
