package com.weg.oficina.solucao.controller;

import com.weg.oficina.solucao.model.Perfil;
import com.weg.oficina.solucao.model.Usuario;
import com.weg.oficina.solucao.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.criarUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> listar(@RequestParam(required = false)Perfil perfil) {
        if (perfil != null) {
            return usuarioService.listarPorPerfil(perfil);
        }
        return usuarioService.listarTodos();
    }
}
