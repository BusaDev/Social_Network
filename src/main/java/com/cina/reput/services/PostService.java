package com.cina.reput.services;

import com.cina.reput.Exceptions.MyException;
import com.cina.reput.dtos.PostDTO;
import com.cina.reput.dtos.UserDto;
import com.cina.reput.entities.PostEntity;
import com.cina.reput.entities.UserEntity;
import com.cina.reput.repositories.PostRepository;
import com.cina.reput.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    public PostDTO create(PostDTO post) throws MyException {
        PostEntity postEntity = modelMapper.map(post, PostEntity.class);
        Optional<UserEntity> newPost = userRepository.findById(post.getUser().getId());
        if(newPost.isPresent()) {
            postEntity.setUser(newPost.get());
        }else{
            throw new MyException("User not found");
        }
        PostEntity saved = postRepository.saveAndFlush(postEntity);
        modelMapper.map(saved, post);
        return post;

    }

    public Optional<PostEntity> getById(Long id){
        return postRepository.findById(id);
    }

    public List<PostEntity> getAllById(Long id) throws MyException {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            return postRepository.findByUser(user.get());
        } else {
            throw new MyException("User not found");
        }
    }
}
