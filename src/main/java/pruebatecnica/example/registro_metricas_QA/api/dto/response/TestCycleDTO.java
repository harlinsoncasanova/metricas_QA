package pruebatecnica.example.registro_metricas_QA.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestCycleDTO {
    private Long id;
    private String aplicacionEntity;
    private Long idVersion;
    private String cycleName;
    private String cycleDescription;
    private List<MetricDTO> metrics;
}
