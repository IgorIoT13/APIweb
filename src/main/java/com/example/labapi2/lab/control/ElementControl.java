package com.example.labapi2.lab.control;


import com.example.labapi2.lab.entity.UserEntity;
import com.example.labapi2.lab.exeption.ObjectAlreadyExistExeption;
import com.example.labapi2.lab.exeption.ObjectNotFoundExeption;
import com.example.labapi2.lab.servise.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Element")
public class ElementControl {

    @Autowired
    private UserServise userServise;


    @PostMapping
    public ResponseEntity CreateObj(@RequestBody UserEntity user){
        try {
            userServise.registration(user);
            return ResponseEntity.ok("User is saved");
        }
        catch (ObjectAlreadyExistExeption e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }




    @RequestMapping("/all")
    @GetMapping
    public ResponseEntity getAll(){
        try {
            return ResponseEntity.ok().body(userServise.getAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Shto!?");
        }
    }

    @GetMapping()
    public ResponseEntity SearchObjId(@RequestParam Long id){
        try {
            return ResponseEntity.ok().body(userServise.getOneObjId(id));
        }
        catch (ObjectNotFoundExeption e){
            return ResponseEntity.badRequest().body("Not Found");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @RequestMapping("/find")
    @GetMapping()
    public ResponseEntity SearchObjTitle(@RequestParam String title){
        List<UserEntity> objs = userServise.getOneObj(title);

        if (objs.isEmpty()) {
            return ResponseEntity.badRequest().body("Empty list");
        } else {
            return ResponseEntity.ok().body(objs);
        }
    }


    @RequestMapping("/filter")
    @GetMapping
    public ResponseEntity FilterEl(@RequestParam String type, @RequestParam String title, @RequestParam int max) {
        try {
            List<UserEntity> listObj = userServise.getFilteredEntity(type, title, max);
            return ResponseEntity.ok().body(listObj);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }





    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PatchMapping
    public ResponseEntity UpdateObj(@RequestBody UserEntity obj){
        try {
            userServise.updataObj(obj);
            return ResponseEntity.ok("Object update");
        }catch (ObjectNotFoundExeption e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }






    @DeleteMapping("/{id}")
    public ResponseEntity DeleteObj(@PathVariable Long id){
        try {
            userServise.deleteObj(id);
            return ResponseEntity.ok().body("Ok");
        }catch (ObjectNotFoundExeption e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}
