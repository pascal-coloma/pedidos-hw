package com.pedidos.hw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pedidos.hw.model.*;

import com.pedidos.hw.repository.DetallePedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DetallePedidoService {

    @Autowired
    DetallePedidoRepository pedidoDetalleRepository;

    public DetallePedido save(DetallePedido detalles){
        return pedidoDetalleRepository.save(detalles);
    }
}
