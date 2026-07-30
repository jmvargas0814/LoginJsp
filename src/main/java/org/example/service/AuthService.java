package org.example.service;

import org.example.model.Usuario;
import org.example.model.UsuarioDTO;
import org.example.repository.UsuarioRepository;
import org.example.security.PasswordUtil;

import java.util.Objects;

public class AuthService {

    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public UsuarioDTO autenticar(String username, String password) {

        Usuario usuario = usuarioRepository.login(username);

        if (Objects.isNull(usuario)) {
            return null;
        }

        if (!PasswordUtil.checkPassword(password, usuario.getPassword())) {
            return null;
        }

        return new UsuarioDTO(
                usuario.getUsername(),
                usuario.getRol(),
                usuario.isCambiarPassword()
        );
    }

}