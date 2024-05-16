package com.cina.reput.entities;

import jakarta.persistence.ManyToOne;

public class Post {


    
    @ManyToOne
    public UserEntity user;
}
