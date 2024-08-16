package pruebatecnica.example.registro_metricas_QA.infraestructure.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebatecnica.example.registro_metricas_QA.api.dto.mappers.ApplicationMapper;
import pruebatecnica.example.registro_metricas_QA.api.dto.request.AplicationRequest;
import pruebatecnica.example.registro_metricas_QA.api.dto.response.AplicationResponse;
import pruebatecnica.example.registro_metricas_QA.domain.entities.NameAplication;
import pruebatecnica.example.registro_metricas_QA.domain.repositories.ApplicationRepository;
import pruebatecnica.example.registro_metricas_QA.infraestructure.abstrac_service.IApplicationService;

@Service
@AllArgsConstructor
public class ApplicationService  implements IApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private final ApplicationMapper applicationMapper;

    @Override
    public AplicationResponse create(AplicationRequest request) {
        NameAplication application=applicationMapper.requestToApplication(request);
        NameAplication saved=applicationRepository.save(application);

        return applicationMapper.entityToResponse(saved);
    }

    @Override
    public AplicationResponse get(Long aLong) {
        return null;
    }

    @Override
    public AplicationResponse update(AplicationRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
