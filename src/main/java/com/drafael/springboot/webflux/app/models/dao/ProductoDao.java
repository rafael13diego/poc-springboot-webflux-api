package com.drafael.springboot.webflux.app.models.dao;

import com.drafael.springboot.webflux.app.models.documents.Producto;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductoDao extends ReactiveMongoRepository<Producto, String> {

    public Mono<Producto> findByNombre(String nombre);

    @Query("{ '_nombre': ?0 }")
    public Mono<Producto> obtenerPorNombre(String nombre);

}
