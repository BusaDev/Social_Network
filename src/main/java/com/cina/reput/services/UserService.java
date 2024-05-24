package com.cina.reput.services;

import com.cina.reput.dtos.UserDto;
import com.cina.reput.entities.UserEntity;
import com.cina.reput.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UserDto create(UserDto user){
        logger.debug("input create: {}", user);
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        logger.debug("post-mapping: {}", userEntity);
        UserEntity saved = userRepository.save(userEntity);
        modelMapper.map(saved, user);
        logger.debug("returned_user: {}", user);
        return user;
    }

    public UserDto findById(Long id){
        UserEntity user = userRepository.findById(id).orElse(null);
        user.getPost().stream().forEach(postEntity -> logger.debug(postEntity.toString()));
        UserDto u = modelMapper.map(user, UserDto.class);
        return u;
    }
}
