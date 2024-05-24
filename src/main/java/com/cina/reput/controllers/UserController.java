package com.cina.reput.controllers;

import com.cina.reput.dtos.UserDto;
import com.cina.reput.entities.UserEntity;
import com.cina.reput.models.Response;
import com.cina.reput.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDto saveUser (@RequestBody UserDto user){
        logger.debug("input: {}", user);
        return userService.create(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> findUser(@PathVariable Long id){
        UserDto userfind = userService.findById(id);
        if(userfind != null){
            return ResponseEntity.ok().body(
                    new Response(200,
                            "User found: ",
                            userfind)
            );
        }else{
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            "User not found, Id invalid"
                    )
            );
        }
    }
}
