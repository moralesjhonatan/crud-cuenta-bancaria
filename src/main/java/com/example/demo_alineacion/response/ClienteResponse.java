package com.example.demo_alineacion.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {
    private String nombres;
    private String direccion;
    private String telefono;
    private String contrasena;
    private String estado;
}
