package com.drafael.springboot.webflux.app.models.documents;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")

public class User {
    @Id
    private String userId;

    private String nameUser;

    private String pwdUser;

}
