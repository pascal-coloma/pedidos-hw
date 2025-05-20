package com.pedidos.hw.model;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "pedido", schema = "PEDIDOMS")
@NoArgsConstructor
@AllArgsConstructor
@Data
// Clase base de Pedidos la cual funciona como tabla en Oracle SQL para el poblado de datos.
public class Pedido {
    
    // ID principal de pedidos, funciona como autogenerativo utilizando una secuencia  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Formateo de los datos para recibir una fecha 
    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date fecha_pedido;

    @Column(nullable = false)
    private Integer estado;

    // Esta columna genera la relacion con la tabla de usuarios
    @Column(name="id_usuario", nullable = false)
    private Long id_usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetallePedido> detalles = new ArrayList<>();

}
