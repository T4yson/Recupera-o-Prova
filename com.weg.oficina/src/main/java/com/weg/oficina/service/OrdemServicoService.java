package com.weg.oficina.service;

import com.weg.oficina.model.OrdemServico;
import com.weg.oficina.model.Perfil;
import com.weg.oficina.model.Usuario;
import com.weg.oficina.repository.OrdemServicoRepository;
import com.weg.oficina.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdemServicoService {

    private final OrdemServicoRepository repository;
    private final UsuarioRepository usuariorepository;

    public OrdemServico abrirOS (String equipamento, String defeito, Long professorId, List<Long> alunosId) {
        Usuario professor = usuariorepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        if (!professor.getPerfil().equals(Perfil.Professor)) {
            throw new RuntimeException("Apenas professores podem abrir Ordens de Serviço.");
        }

        OrdemServico os = new OrdemServico();
        os.setEquipamento(equipamento);
        os.setDefeitoRelatado(defeito);
        os.setProfessorResponsavel(professor);

        os.setAlunosEscalados(usuariorepository.findAllById(alunosId));

        return repository.save(os);
    }

    public void encerrarOS (Long osId, Long professorId) {
        OrdemServico os = repository.findById(osId).get();

        if (!os.getProfessorResponsavel().getId().equals(professorId)) {
            throw new RuntimeException("Acesso negado: Você não é responsável por esta OS.");
        }
        os.setStatus(StatusOS.Concluida);
        repository.save(os);
    }
}
