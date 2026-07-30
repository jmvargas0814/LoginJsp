package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

        private int id;
        private String username;
        private String password;

        private String numeroDocumento;
        private String nombre;
        private String apellido;
        private String email;
        private String telefono;

        private String genero;
        private String rol;
        private boolean activo;

        private int departamentoId;
        private int ciudadId;
        private boolean isCambiarPassword;

}
