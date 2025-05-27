package org.serratec.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.serratec.backend.entity.Usuario;
import org.serratec.backend.entity.UsuarioPerfil;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioRequestDTO {
    @NotBlank(message = "O nome não pode ser vazio ou nulo!")
    private String nome;

    @Email(message = "Foi inserido uma formatação de email invalida!")
    private String email;

    @NotBlank(message = "A senha não pode ser vazia ou nula!")
    private String senha;

    private Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();

    public UsuarioRequestDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }
}
