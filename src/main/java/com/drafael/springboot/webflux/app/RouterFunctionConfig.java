package com.drafael.springboot.webflux.app;

import com.drafael.springboot.webflux.app.models.documents.Producto;
import com.drafael.springboot.webflux.app.models.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterFunctionConfig {

    @Autowired
    private ProductoService service;

   @Bean
    public RouterFunction<ServerResponse> routes(){

       return RouterFunctions.route(RequestPredicates.GET("/api/v2/productos"), request -> {
           return ServerResponse.ok()
                   .contentType(MediaType.APPLICATION_JSON)
                   .body(service.findAll(), Producto.class);
       });
    }

}
