package org.serratec.backend.service;

import jakarta.transaction.Transactional;
import org.serratec.backend.dto.UsuarioRequestDTO;
import org.serratec.backend.dto.UsuarioResponseDTO;
import org.serratec.backend.entity.Usuario;
import org.serratec.backend.entity.UsuarioPerfil;
import org.serratec.backend.exception.UsuarioException;
import org.serratec.backend.repository.UsuarioPerfilRepository;
import org.serratec.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private UsuarioPerfilRepository usuarioPerfilRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<UsuarioResponseDTO> listar(){
        List<Usuario> usuarios = repository.findAll();
        List<UsuarioResponseDTO> usuariosDTO = new ArrayList<>();

        for(Usuario usuario : usuarios){
            usuariosDTO.add(new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()));
        }

        return usuariosDTO;
    }

    @Transactional
    public UsuarioResponseDTO inserir(UsuarioRequestDTO usuarioRequestDTO){
        Optional<Usuario> u = repository.findByEmail(usuarioRequestDTO.getEmail());
        if(u.isPresent()){
            throw new UsuarioException("Email já cadastrado!");
        }
        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setNome(usuarioRequestDTO.getNome());
        usuarioEntity.setEmail(usuarioRequestDTO.getEmail());
        usuarioRequestDTO.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));
        usuarioEntity.setSenha(usuarioRequestDTO.getSenha());

        for(UsuarioPerfil up : usuarioRequestDTO.getUsuarioPerfis()){
            up.setPerfil(perfilService.buscar(up.getPerfil().getId()));
            up.setUsuario(usuarioEntity);
            up.setDataCriacao(LocalDate.now());
        }

        repository.save(usuarioEntity);
        usuarioPerfilRepository.saveAll(usuarioRequestDTO.getUsuarioPerfis());

        return new UsuarioResponseDTO(usuarioEntity.getId(), usuarioEntity.getNome(), usuarioEntity.getEmail());
    }
}
