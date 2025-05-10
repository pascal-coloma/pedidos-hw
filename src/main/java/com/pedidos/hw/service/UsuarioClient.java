package com.pedidos.hw.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.pedidos.hw.dto.UsuarioDto;
import java.util.List;
@Service
public class UsuarioClient {

    private final RestTemplate restTemplate;

    public UsuarioClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public UsuarioDto obtenerUsuarioPorRun(String run){
        String url = "http://localhost:8081/hoppyware/v1/usuarios/buscarRun?run=" + run;
        return restTemplate.getForObject(url, UsuarioDto.class);
    }

    public List<UsuarioDto> obtenerTodosUsuarios(){
        String url = "http://localhost:8081/hoppyware/v1/usuarios";
        ResponseEntity<List<UsuarioDto>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<UsuarioDto>>(){});
        return response.getBody();
    }
}
