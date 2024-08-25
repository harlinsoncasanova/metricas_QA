package pruebatecnica.example.registro_metricas_QA.infraestructure.abstrac_service;

import pruebatecnica.example.registro_metricas_QA.api.dto.request.TestCycleRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.TestCycleDTO;

public interface ITestService extends  CrudGeneral<TestCycleRequest, TestCycleDTO,Long>{
}
