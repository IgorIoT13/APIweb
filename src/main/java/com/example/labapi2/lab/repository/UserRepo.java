package com.example.labapi2.lab.repository;


import com.example.labapi2.lab.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByTitle(String title);
    List<UserEntity> findByTitleLikeIgnoreCase(String title);
}
