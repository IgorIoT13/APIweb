package com.example.labapi2.lab.servise;

import com.example.labapi2.lab.entity.UserEntity;
import com.example.labapi2.lab.exeption.ObjectAlreadyExistExeption;
import com.example.labapi2.lab.exeption.ObjectNotFoundExeption;
import com.example.labapi2.lab.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServise {
    @Autowired
    private UserRepo userRepo;

    public List<UserEntity> getAll(){
        return (List<UserEntity>) userRepo.findAll();
    }
    public UserEntity registration(UserEntity user) throws ObjectAlreadyExistExeption {
        if(userRepo.findByTitle(user.getTitle()) != null){
            throw new ObjectAlreadyExistExeption("Object already exist");
        }
        return userRepo.save(user);
    }

    public List<UserEntity> getOneObj(String title) {
        return userRepo.findByTitleLikeIgnoreCase(title + "%");
    }

    public void updataObj(UserEntity upUser) throws ObjectNotFoundExeption {
        Optional<UserEntity> optionObj = userRepo.findById(upUser.getId());

        try {
            UserEntity obj = optionObj.get();
            obj.setId(obj.getId());
            obj.setTitle(upUser.getTitle());
            obj.setDescription(upUser.getDescription());
            obj.setFuel(upUser.getFuel());
            userRepo.save(obj);
        }catch (Exception e){
            throw  new ObjectNotFoundExeption("Object not found");
        }
    }

    public void deleteObj(Long id) throws ObjectNotFoundExeption {
        Optional<UserEntity> optionObj = userRepo.findById(id);
        try {
            UserEntity obj = optionObj.get();
            userRepo.delete(obj);
        }catch (Exception e){
            throw  new ObjectNotFoundExeption("Object not found");
        }
    }

    public UserEntity getOneObjId(Long id)throws ObjectNotFoundExeption {
        Optional<UserEntity> objList = userRepo.findById(id);
        try {
            UserEntity obj = objList.get();
            return obj;
        }catch (Exception e){
            throw new ObjectNotFoundExeption("Object not found");
        }


    }

}
