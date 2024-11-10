package com.example.usuario_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.usuario_service.model.dto.*;

@FeignClient(name = "monopatin-service")
public interface MonopatinClient {

    @GetMapping("monopatin/cercanos/{latitud}/{longitud}/{radio}")
    List<MonopatinesCercanosDTO> obtenerMonopatinesCercanos(@PathVariable Double latitud, @PathVariable Double longitud, @PathVariable double radio);
}
