package org.example.utils;

import org.example.model.Usuario;

public interface UsuarioDAO {
    void guardar(Usuario usuario);
    Usuario login(String username);
}