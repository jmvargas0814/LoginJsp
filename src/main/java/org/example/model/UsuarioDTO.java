package org.example.model;

public class UsuarioDTO {

    private String username;
    private String rol;
    private boolean cambiarPassword;

    public UsuarioDTO(String username,
                      String rol,
                      boolean cambiarPassword) {
        this.username = username;
        this.rol = rol;
        this.cambiarPassword = cambiarPassword;
    }

    public String getUsername() { return username; }
    public String getRol() { return rol; }
    public boolean isCambiarPassword() {
        return cambiarPassword;
    }
}