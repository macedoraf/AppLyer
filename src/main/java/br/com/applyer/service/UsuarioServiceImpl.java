package br.com.applyer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.applyer.domain.Usuario;
import br.com.applyer.repository.UsuarioRepository;

/**
 * UsuarioServiceImpl
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository repository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        repository.save(usuario);

    }

    @Override
    public Usuario buscarUsuario(long id) {
        return repository.findById(id).get();

    }

    @Override
    public Usuario autenticarUsuario(String email, String senha) {
        Usuario novoUsuario = new Usuario();
        repository.findAll().forEach(
            user -> {
                if(user.getEmail().equals(email)
                && user.getSenha().equals(senha))
                {
                    novoUsuario.setEmail(email);
                    novoUsuario.setSenha(senha);
                    novoUsuario.setId(user.getId());
                }
            }
        );
        return novoUsuario;
    }

    @Override
    public void deleteUsuario(long id) {
        repository.deleteById(id);

    }

    
}