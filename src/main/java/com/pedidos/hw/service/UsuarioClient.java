package com.pedidos.hw.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;


import com.pedidos.hw.dto.UsuarioDto;
import com.pedidos.hw.model.Pedido;
import java.util.List;

@FeignClient(name = "usuario-service", url = "http://localhost:8081/hoppyware/v1/usuario")
public interface UsuarioClient {


    @GetMapping("/usuario/buscarRun")
    UsuarioDto getUsuarioDto(@PathVariable String run);

    @GetMapping("/{id}")
    UsuarioDto getUsrPorId(@PathVariable("id") Long id);

   
}   
