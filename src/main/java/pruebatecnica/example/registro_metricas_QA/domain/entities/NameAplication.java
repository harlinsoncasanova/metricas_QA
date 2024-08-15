package pruebatecnica.example.registro_metricas_QA.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "aplication")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NameAplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "aplication",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Version> versions=new ArrayList<>();


}
