package com.weg.oficina.solucao.repository;

import com.weg.oficina.solucao.model.OrdemServico;
import com.weg.oficina.solucao.model.StatusOS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    List<OrdemServico> findByStatus(StatusOS status);
}
