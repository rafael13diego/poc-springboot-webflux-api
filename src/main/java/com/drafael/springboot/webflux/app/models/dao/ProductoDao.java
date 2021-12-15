package com.drafael.springboot.webflux.app.models.dao;

import com.drafael.springboot.webflux.app.models.documents.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoDao extends ReactiveMongoRepository<Producto, String>{

}
