package com.pedidos.hw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos.hw.repository.PedidoRepository;
import com.pedidos.hw.model.Pedido;
import java.util.List;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Pedido findById(Long id){
        return pedidoRepository.findById(id).get();
    }

    public Pedido save(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> saveLista(List<Pedido> listaPedidos){
        return pedidoRepository.saveAll(listaPedidos);
    }


}
