package pruebatecnica.example.registro_metricas_QA.infraestructure.abstrac_service;

import pruebatecnica.example.registro_metricas_QA.api.dto.request.MetricRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.MetricDTO;

public interface IMetricService extends CrudGeneral<MetricRequest, MetricDTO,Long>{
}
