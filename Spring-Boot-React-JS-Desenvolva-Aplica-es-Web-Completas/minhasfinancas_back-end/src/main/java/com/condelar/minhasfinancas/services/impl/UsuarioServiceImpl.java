package com.condelar.minhasfinancas.services.impl;

import com.condelar.minhasfinancas.exception.ErroAutenticacao;
import com.condelar.minhasfinancas.exception.RegraNegocioException;
import com.condelar.minhasfinancas.model.entity.Usuario;
import com.condelar.minhasfinancas.model.repository.UsuarioRepository;
import com.condelar.minhasfinancas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository repository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository) {
        super();
        this.repository = repository;

    }

    @Override
    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> usuarios = repository.findByEmail(email);
        if (!usuarios.isPresent()) {
            throw new ErroAutenticacao("Usuario não encontrado!");
        }
        if (!usuarios.get().getSenha().equals(senha)) {
            throw new ErroAutenticacao("Senha Invalida!");
        }
        return usuarios.get();
    }

    @Override
    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        validarEmail(usuario.getEmail());
        return repository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = repository.existsByEmail(email);

        if (existe) {
            throw new RegraNegocioException("Email já utilizado!");
        }
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }
}
