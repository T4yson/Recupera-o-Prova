package com.weg.oficina.controller;

import com.weg.oficina.model.OrdemServico;
import com.weg.oficina.service.OrdemServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/os")
@RequiredArgsConstructor
public class OrdemServicoController {

    private final OrdemServicoService service;

    @PostMapping("/abrir")
    public ResponseEntity<OrdemServico> abrir(@RequestParam String equipamento, @RequestParam String defeito, @RequestParam Long professorId, @RequestBody List<Long> aludoId) {

        return ResponseEntity.ok(service.abrirOS(equipamento, defeito, professorId, aludoId));
    }

    @PutMapping("{id}/encerrar")
    public ResponseEntity<Void> encerrar(@PathVariable Long id, @RequestParam Long professorId) {
        service.encerrarOS(id, professorId);
        return ResponseEntity.noContent().build();
    }
}
