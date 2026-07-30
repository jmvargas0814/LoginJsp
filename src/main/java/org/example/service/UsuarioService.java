package org.example.service;

import org.example.exception.BusinessException;
import org.example.model.Usuario;
import org.example.repository.AuditoriaRepository;
import org.example.repository.UsuarioRepository;
import org.example.security.PasswordUtil;

public class UsuarioService {

    private UsuarioRepository usuarioRepository = new UsuarioRepository();
    private AuditoriaRepository auditoriaRepository = new AuditoriaRepository();

    /**
     * Guarda un usuario nuevo
     */
    public void guardar(Usuario usuario) {

        if (usuarioRepository.existeDocumento(usuario.getNumeroDocumento())) {
            throw new BusinessException("El documento ya existe");
        }

        // ✅ Aquí podrías encriptar password en futuro
        String hashedPassword = PasswordUtil.hashPassword(usuario.getPassword());
        usuario.setPassword(hashedPassword);
        auditoriaRepository.registrar(
                usuario.getUsername(),
                "CREACION_USUARIO"
        );

        usuarioRepository.guardar(usuario);
    }
}