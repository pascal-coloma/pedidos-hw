package com.pedidos.hw.dto;

import java.util.Date;
import java.util.List;
import com.pedidos.hw.model.*;

import lombok.Data;

@Data
public class PedidoUsuarioDTO {

    private Long id;
    private Integer estado;
    private Date fecha_pedido;
    private UsuarioDto detallesContacto;
    private List<DetallePedido> detalles;
}
