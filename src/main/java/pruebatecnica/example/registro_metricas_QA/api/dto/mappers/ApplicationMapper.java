package pruebatecnica.example.registro_metricas_QA.api.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pruebatecnica.example.registro_metricas_QA.api.dto.request.AplicationRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.AplicationResponse;
import pruebatecnica.example.registro_metricas_QA.domain.entities.NameAplication;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApplicationMapper {
    NameAplication requestToApplication(AplicationRequest applicationRequest);

    AplicationResponse entityToResponse(NameAplication application);

}
