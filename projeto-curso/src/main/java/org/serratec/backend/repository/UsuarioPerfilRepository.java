package org.serratec.backend.repository;

import org.serratec.backend.entity.UsuarioPerfil;
import org.serratec.backend.entity.pk.UsuarioPerfilPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, UsuarioPerfilPK> {
}
