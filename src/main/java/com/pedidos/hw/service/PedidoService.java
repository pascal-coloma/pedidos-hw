package com.pedidos.hw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos.hw.repository.PedidoRepository;
import com.pedidos.hw.dto.UsuarioDto;
//import com.pedidos.hw.dto.UsuarioDto;
import com.pedidos.hw.model.Pedido;

import java.util.Date;
import java.util.List;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    private final UsuarioClient usuarioClient;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioClient usuarioClient){
        this.pedidoRepository = pedidoRepository;
        this.usuarioClient = usuarioClient;
    }

    public UsuarioDto getContactoPorIdPedido(Long idPedido){
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        Long idUsuario = pedido.getId_usuario();
        return usuarioClient.getUsrPorId(idUsuario);
    }

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

    public List<Pedido> buscarFecha(Date fecha_pedido){
        return pedidoRepository.findByFecha_pedido(fecha_pedido);
    }

    public void delete(Long id){
        pedidoRepository.deleteById(id);
    }

    public List<Pedido> getPedsPorUsr(Long id_usr){
        return pedidoRepository.findById_usuario(id_usr);
    }
}
