package pruebatecnica.example.registro_metricas_QA.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "application")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "applicationEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<VersionEntity> versions=new ArrayList<>();


}
