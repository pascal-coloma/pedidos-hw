package com.pedidos.hw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos.hw.repository.PedidoRepository;
import com.pedidos.hw.dto.UsuarioDto;
import com.pedidos.hw.model.Pedido;

import java.util.Date;
import java.util.List;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    // Repositorio de metodos base para una clase
    private PedidoRepository pedidoRepository;

    // Constante definida para trabajar con el Cliente que se comunica con el MS de Usuarios
    private final UsuarioClient usuarioClient;

    // Constructor del pedido service donde se define el repositorio de metodos y la clase usuarioclient
    public PedidoService(PedidoRepository pedidoRepository, UsuarioClient usuarioClient){
        this.pedidoRepository = pedidoRepository;
        this.usuarioClient = usuarioClient;
    }
    
    // Metodo que trae los datos de contacto por id de pedido
    public UsuarioDto getContactoPorIdPedido(Long idPedido){
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        Long idUsuario = pedido.getId_usuario();
        return usuarioClient.getUsrPorId(idUsuario);
    }

    // Listado de todos los pedidos 
    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    // Encuentra pedidos por su ID
    public Pedido findById(Long id){
        return pedidoRepository.findById(id).get();
    }

    // Guardado de pedidos 
    public Pedido save(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    // Guardado de mas de un pedido
    public List<Pedido> saveLista(List<Pedido> listaPedidos){
        return pedidoRepository.saveAll(listaPedidos);
    }

    // Busqueda de pedidos por fecha
    public List<Pedido> buscarFecha(Date fecha_pedido){
        return pedidoRepository.findByFecha_pedido(fecha_pedido);
    }

    // Eliminacion de un pedido por ID de pedido
    public void delete(Long id){
        pedidoRepository.deleteById(id);
    }

    // Listado de pedidos segun ID de usuario
    public List<Pedido> getPedsPorUsr(Long id_usr){
        return pedidoRepository.findById_usuario(id_usr);
    }
}
