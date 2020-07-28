package com.mincho.Restful.user;
//hibernate = JAVA object <=> DB Entity help to application API framework

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid; //Users Validation Check!
import java.net.URI;
import java.util.List;


//static method!
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service){
        this.service = service;
    }
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }


    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = service.findOne(id);
            if(user == null){
                throw new UserNotFoundException(String.format("ID[%s] not found",id));
            }
            //Insert Hateos work ( send data + detailed about Link tag)
        EntityModel<User> model = new EntityModel<>(user);
            //Link on and add method on Current class
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        model.add(linkTo.withRel("all-users"));

        return model;
    }
    @PostMapping("/users") //@RequestBody transfer from parameter.
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
       User savedUser =service.save(user);

       //user to request value change
        //Make URI for register new ID
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("{/id}")
            .buildAndExpand(savedUser.getId())
            .toUri();
        return ResponseEntity.created(location).build();
    }
   @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);
        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

    }

}
