package pruebatecnica.example.registro_metricas_QA.infraestructure.abstrac_service;

import pruebatecnica.example.registro_metricas_QA.api.dto.request.AplicationRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.ApplicationDTO;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.TestCycleDTO;

import java.util.List;

public interface IApplicationService
extends CrudGeneral<AplicationRequest, ApplicationDTO,Long>{
    public ApplicationDTO getById(Long id);

    List<TestCycleDTO> getTestCyclesByApplicationNameAndVersion(String name, String versionName);
}
