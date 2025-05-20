package com.pedidos.hw.controller;

import java.util.List;
import java.util.Date;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pedidos.hw.dto.PedidoUsuarioDTO;
import com.pedidos.hw.dto.UsuarioDto;
import com.pedidos.hw.model.DetallePedido;
import com.pedidos.hw.model.Pedido;
import com.pedidos.hw.service.PedidoService;

import org.springframework.web.bind.annotation.GetMapping;


// Controlador REST para definir los distintos endpoints del microservicio de Pedidos. Se migro de un resttemplate a Feign Client
@RestController
@RequestMapping("/hoppyware/v1/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Mapeo de datos de contacto segun ID de pedido llamando un DTO para recibir datos del MS de usuarios
    
    @GetMapping("/{idPedido}/usuario-contacto")
    public ResponseEntity<UsuarioDto> getContactoUsuario(@PathVariable Long idPedido){
        UsuarioDto contacto = pedidoService.getContactoPorIdPedido(idPedido);
        return ResponseEntity.ok(contacto);
    }
    
    // Listado de pedidos usando el metodo findAll del Repository
    
    @GetMapping
    public ResponseEntity<List<PedidoUsuarioDTO>> listar(){
        List<PedidoUsuarioDTO> pedidos = pedidoService.listarPedidosDTO();
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    // Guardado de datos de un Pedido
    @PostMapping("/guardar")
    public ResponseEntity<Pedido> guardar(@RequestBody Pedido pedido){
        for (DetallePedido detalle : pedido.getDetalles()) {
            detalle.setPedido(pedido);
        }
        Pedido nuevoPedido = pedidoService.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    // Busqueda de pedidos por ID del pedido aceptando una variable dentro de la URL
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarId(@PathVariable Long id){
        try {
            Pedido pedido = pedidoService.findById(id);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Busqueda de pedidos por fecha del pedido
    @GetMapping("/buscarFecha")
    public ResponseEntity<List<Pedido>> buscarFecha(Date fechaPedido){
        List<Pedido> pedidos = pedidoService.buscarFecha(fechaPedido);
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }
    
    // Solicitud POST para actualizar un pedido cambiando todo sus atributos 
    @PutMapping("/actualizarPedido/{id}")
    public ResponseEntity<Pedido> actualizar(@PathVariable Long id, @RequestBody Pedido pedido){
        try {
            Pedido ped = pedidoService.findById(id);
            ped.setId(pedido.getId());
            ped.setEstado(pedido.getEstado());
            ped.setFecha_pedido(pedido.getFecha_pedido());
            ped.setId(pedido.getId());
            ped.setId_usuario(pedido.getId_usuario());

            pedidoService.save(ped);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminacion de pedidos por su ID de pedido usando una solicitud DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            pedidoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Busqueda de pedidos por la ID de usuario comunicandose con el microservicio de usuarios 
    @GetMapping("/{id}/pedidos-cliente")
    public ResponseEntity<List<Pedido>> getPedidosUsuario(@PathVariable Long id){
        try {
            List<Pedido> pedidosPorUser = pedidoService.getPedsPorUsr(id);
            return ResponseEntity.ok(pedidosPorUser);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

}
