package com.pedidos.hw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pedidos.hw.repository.PedidoRepository;
import com.pedidos.hw.dto.DetallePedidoDTO;
import com.pedidos.hw.dto.PedidoUsuarioDTO;
import com.pedidos.hw.dto.ProductoDTO;
import com.pedidos.hw.dto.UsuarioDto;
import com.pedidos.hw.model.DetallePedido;
import com.pedidos.hw.model.Pedido;

import java.util.ArrayList;
import java.util.Date;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PedidoService {

    @Autowired
    // Repositorio de metodos base para una clase
    private PedidoRepository pedidoRepository;

    // Constante definida para trabajar con el Cliente que se comunica con el MS de
    // Usuarios
    private final UsuarioClient usuarioClient;
    private final ProductoClient productoClient;

    // Constructor del pedido service donde se define el repositorio de metodos y la
    // clase usuarioclient

    // Metodo que trae los datos de contacto por id de pedido
    public UsuarioDto getContactoPorIdPedido(Long idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        Long idUsuario = pedido.getId_usuario();
        return usuarioClient.getUsrPorId(idUsuario);
    }

    public List<PedidoUsuarioDTO> listarPedidosDTO() {
        List<Pedido> pedidos = pedidoRepository.findAll();

        return pedidos.stream().map(p -> {
            PedidoUsuarioDTO dto = new PedidoUsuarioDTO();
            dto.setId(p.getId());
            dto.setEstado(p.getEstado());
            dto.setFecha_pedido(p.getFecha_pedido());

            List<DetallePedidoDTO> detalleDTOs = new ArrayList<>();

            for (DetallePedido detalle : p.getDetalles()) {
                DetallePedidoDTO detalleDTO = new DetallePedidoDTO();
                detalleDTO.setCantidad(detalle.getCantidad());
                detalleDTO.setId_producto(detalle.getId_producto());

                try {
                    ProductoDTO producto = productoClient.getProductoDTO(detalle.getId_producto());
                    detalleDTO.setProducto(producto);
                } catch (Exception e) {
                    System.out.println("Error al obtener producto: " + e.getMessage());
                    detalleDTO.setProducto(null);
                }

                detalleDTOs.add(detalleDTO);
            }

            dto.setDetalles(detalleDTOs);

            try {
                UsuarioDto usuario = usuarioClient.getUsrPorId(p.getId_usuario());
                dto.setDetallesContacto(usuario);
            } catch (Exception e) {
                dto.setDetallesContacto(null);
            }

            return dto;
        }).collect(Collectors.toList());
    }

    // Listado de todos los pedidos
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    // Encuentra pedidos por su ID
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).get();
    }

    // Guardado de pedidos
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    // Guardado de mas de un pedido
    public List<Pedido> saveLista(List<Pedido> listaPedidos) {
        return pedidoRepository.saveAll(listaPedidos);
    }

    // Busqueda de pedidos por fecha
    public List<Pedido> buscarFecha(Date fecha_pedido) {
        return pedidoRepository.findByFecha_pedido(fecha_pedido);
    }

    // Eliminacion de un pedido por ID de pedido
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    // Listado de pedidos segun ID de usuario
    public List<Pedido> getPedsPorUsr(Long id_usr) {
        return pedidoRepository.findById_usuario(id_usr);
    }

    public List<PedidoUsuarioDTO> listarPedidosPorUsuario(Long idUsr) {
        List<Pedido> pedidos = pedidoRepository.findById_usuario(idUsr);
        return pedidos.stream().map(p -> {
            PedidoUsuarioDTO dto = new PedidoUsuarioDTO();
            dto.setId(p.getId());
            dto.setEstado(p.getEstado());
            dto.setFecha_pedido(p.getFecha_pedido());

            List<DetallePedidoDTO> detalleDTOs = new ArrayList<>();

            for (DetallePedido detalle : p.getDetalles()) {
                DetallePedidoDTO detalleDTO = new DetallePedidoDTO();
                detalleDTO.setCantidad(detalle.getCantidad());
                detalleDTO.setId_producto(detalle.getId_producto());

                try {
                    ProductoDTO producto = productoClient.getProductoDTO(detalle.getId_producto());
                    detalleDTO.setProducto(producto);
                } catch (Exception e) {
                    System.out.println("Error al obtener producto: " + e.getMessage());
                    detalleDTO.setProducto(null);
                }

                detalleDTOs.add(detalleDTO);
            }

            dto.setDetalles(detalleDTOs);

            // Opcional: incluir datos de usuario, pero puedes omitir si eso causa
            // recursividad
            try {
                UsuarioDto usuario = usuarioClient.getUsrPorId(p.getId_usuario());
                dto.setDetallesContacto(usuario);
            } catch (Exception e) {
                dto.setDetallesContacto(null);
            }

            return dto;
        }).collect(Collectors.toList());

    }

    public ProductoDTO getProductoPorId(Long id) {
        return productoClient.getProductoDTO(id);
    }
}
