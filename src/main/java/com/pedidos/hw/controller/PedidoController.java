package com.pedidos.hw.controller;

import java.util.List;
import java.util.Date;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pedidos.hw.dto.UsuarioDto;
import com.pedidos.hw.model.Pedido;
import com.pedidos.hw.service.PedidoService;
import com.pedidos.hw.service.UsuarioClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/hoppyware/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    private final UsuarioClient usuarioClient;

    public PedidoController(UsuarioClient usuarioClient){
        this.usuarioClient = usuarioClient;
    }

    @GetMapping("/usuarios/buscarRun")
    public ResponseEntity<UsuarioDto> getUsuario(@RequestParam String run){
        UsuarioDto usuario = usuarioClient.obtenerUsuarioPorRun(run);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDto>> getTodosUsuarios(){
        List<UsuarioDto> listaUsuarioDtos = usuarioClient.obtenerTodosUsuarios();
        return ResponseEntity.ok(listaUsuarioDtos);
    }
    

    @GetMapping
    public ResponseEntity<List<Pedido>> listar(){
        List<Pedido> pedidos = pedidoService.findAll();
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Pedido> guardar(@RequestBody Pedido pedido){
        Pedido nuevoPedido = pedidoService.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    @GetMapping("/buscarId/{id}")
    public ResponseEntity<Pedido> buscarId(@PathVariable Long id){
        try {
            Pedido pedido = pedidoService.findById(id);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/buscarFecha")
    public ResponseEntity<List<Pedido>> buscarFecha(Date fechaPedido){
        List<Pedido> pedidos = pedidoService.buscarFecha(fechaPedido);
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }
    
    @PutMapping("/actualizarPedido/{id}")
    public ResponseEntity<Pedido> actualizar(@PathVariable Long id, @RequestBody Pedido pedido){
        try {
            Pedido ped = pedidoService.findById(id);
            ped.setId(pedido.getId());
            ped.setEstado(pedido.getEstado());
            ped.setFecha_pedido(pedido.getFecha_pedido());
            ped.setId_producto(pedido.getId_producto());
            ped.setId_usuario(pedido.getId_usuario());

            pedidoService.save(ped);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    
    }

    @DeleteMapping("/eliminarPedido/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            pedidoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
