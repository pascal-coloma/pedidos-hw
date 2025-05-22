package com.pedidos.hw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoDTO {

    private Integer cantidad;
    private Long id_producto;
    private ProductoDTO producto;


}
