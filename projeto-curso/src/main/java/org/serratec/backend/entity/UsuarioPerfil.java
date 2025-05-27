package org.serratec.backend.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.serratec.backend.entity.pk.UsuarioPerfilPK;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class UsuarioPerfil {
    @EmbeddedId
    private UsuarioPerfilPK id = new UsuarioPerfilPK();

    private LocalDate dataCriacao;

    public UsuarioPerfil(Usuario usuario, Perfil perfil, LocalDate dataCriacao) {
        id.setUsuario(usuario);
        id.setPerfil(perfil);
        this.dataCriacao = dataCriacao;
    }

    public void setUsuario(Usuario usuario){
        id.setUsuario(usuario);
    }

    public Usuario getUsuario(){
        return id.getUsuario();
    }

    public void setPerfil(Perfil perfil){
        id.setPerfil(perfil);
    }

    public Perfil getPerfil(){
        return id.getPerfil();
    }
}
