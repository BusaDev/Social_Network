package com.cina.reput.repositories;

import com.cina.reput.entities.PostEntity;
import com.cina.reput.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByUser(UserEntity user);
}
