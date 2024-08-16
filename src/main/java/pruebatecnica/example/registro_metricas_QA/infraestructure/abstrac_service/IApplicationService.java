package pruebatecnica.example.registro_metricas_QA.infraestructure.abstrac_service;

import pruebatecnica.example.registro_metricas_QA.api.dto.request.AplicationRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.AplicationResponse;

public interface IApplicationService
extends CrudGeneral<AplicationRequest, AplicationResponse,Long>{
}
