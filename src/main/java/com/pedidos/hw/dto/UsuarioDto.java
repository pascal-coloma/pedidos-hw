package com.pedidos.hw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private Long id;
    private String pnombre;
    private String appaterno;
    private String correo;
    private String num_telefono;
}
