package com.cina.reput.controllers;

import com.cina.reput.Exceptions.MyException;
import com.cina.reput.dtos.PostDTO;
import com.cina.reput.dtos.UserDto;
import com.cina.reput.entities.PostEntity;
import com.cina.reput.entities.UserEntity;
import com.cina.reput.models.Response;
import com.cina.reput.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/post")
public class PostController {

    @Autowired
    private PostService postService;
    @PostMapping(path = "/add")
    public ResponseEntity<Response> newPost(@RequestBody PostDTO post){

        try {
            PostDTO newPost = postService.create(post);
            return ResponseEntity.ok().body(
                    new Response(200,
                            " added correctly",
                            newPost)
            );
        } catch (MyException e) {
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            e.getMessage()
                    )
            );

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id){
        Optional<PostEntity> p = postService.getById(id);
        if(p.isPresent()){
            return ResponseEntity.ok().body(
                    new Response(200,
                            "Post found: ",
                            p)
            );
        }else{
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            "post not found, Id invalid"
                    )
            );
        }
    }

    @GetMapping(path = "/findUser/{id}")
    public ResponseEntity<Response> findUser(@PathVariable Long id){
        Optional<PostEntity> p = postService.getById(id);

        if(p.isPresent()){
            UserEntity user = p.get().getUser();
            return ResponseEntity.ok().body(
                    new Response(200,
                            "User found: ",
                            user)
            );
        }else{
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            "post not found, Id invalid"
                    )
            );
        }

    }

    @GetMapping(path = "/findAllByUser/{id}")
    public ResponseEntity<Response> findAllPostOfUser(@PathVariable Long id){

        try{
            List<PostEntity> postList = postService.getAllById(id);
            return ResponseEntity.ok().body(
                    new Response(200,
                            "User found: ",
                            postList)
            );
        }catch (MyException e){
            return ResponseEntity.status(404).body(
                    new Response(
                            404,
                            "User not found, Id invalid"
                    )
            );
        }

    }
}
