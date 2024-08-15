package pruebatecnica.example.registro_metricas_QA.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "version")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String VersionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_name_aplication")
    private NameAplication nameAplication;

    @OneToMany(mappedBy = "version",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<TestCycle> testCycles=new ArrayList<>();



}
