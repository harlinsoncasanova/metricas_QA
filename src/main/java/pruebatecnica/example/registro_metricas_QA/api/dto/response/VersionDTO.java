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
public class VersionDTO {
    private int id;
    private String VersionName;
    private String nameApp;

}
