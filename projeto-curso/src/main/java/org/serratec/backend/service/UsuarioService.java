package org.serratec.backend.service;

import org.serratec.backend.entity.Usuario;
import org.serratec.backend.exception.UsuarioException;
import org.serratec.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> listar(){
        return repository.findAll();
    }

    public Usuario inserir(Usuario usuario){
        Optional<Usuario> u = repository.findByEmail(usuario.getEmail());
        if(u.isPresent()){
            throw new UsuarioException("Email já cadastrado!");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }


}
