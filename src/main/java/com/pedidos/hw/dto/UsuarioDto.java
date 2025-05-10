package com.pedidos.hw.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private Long id;
    private String run;
    private String dvrun;
    private String pnombre;
    private String appaterno;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date fechaNacto;
    private String correo;
    private String numTelefono;
}
