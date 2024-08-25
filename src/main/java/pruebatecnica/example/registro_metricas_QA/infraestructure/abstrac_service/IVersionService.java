package pruebatecnica.example.registro_metricas_QA.infraestructure.abstrac_service;

import pruebatecnica.example.registro_metricas_QA.api.dto.request.VersionRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.VersionDTO;

public interface IVersionService extends
CrudGeneral<VersionRequest, VersionDTO,Long>{
}
