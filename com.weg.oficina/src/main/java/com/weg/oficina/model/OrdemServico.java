package com.weg.oficina.model;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class OrdemServico {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String equipamento;
    private String defeitoRelatado;
    private String materiais;
    private String laudoTecnico;

    @Enumerated(EnumType.STRING)
    private StatusOS status = StatusOS.Executando;

    @ManyToOne
    private Usuario professorResponsavel;

    @ManyToMany
    private List<Usuario> alunosEscalados = new ArrayList<>();
}
