package com.pedidos.hw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// Clase que consume datos desde el endpoint de usuarios. Los cuales son mostrados como datos de contacto para el servicio de pedidos
public class UsuarioDto {

    private Long id;
    private String pnombre;
    private String appaterno;
    private String correo;
    private String num_telefono;
}
