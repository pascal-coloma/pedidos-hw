package com.pedidos.hw.repository;


import com.pedidos.hw.model.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT * FROM pedido WHERE fecha_pedido :=fecha_pedido", nativeQuery = true)
    List<Pedido> findByFecha_pedido(Date fecha_pedido);

}
