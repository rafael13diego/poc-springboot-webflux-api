package com.drafael.springboot.webflux.app.models.dao;

import com.drafael.springboot.webflux.app.models.documents.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserDao extends ReactiveMongoRepository<User, String> {


}
