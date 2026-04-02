package com.weg.oficina.solucao.repository;

import com.weg.oficina.solucao.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.weg.oficina.solucao.model.Usuario;


import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    List<Usuario> findByPerfil(Perfil perfil);
}
