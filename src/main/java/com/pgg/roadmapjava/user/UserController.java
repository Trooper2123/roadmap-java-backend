package com.pgg.roadmapjava.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
        List<User> list = userService.findAllUsers();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/cpf")
    public ResponseEntity<List<User>> findUserByCPF(@RequestParam("cpf") String cpf){
        List<User> list = userService.findUserByCPF(cpf);
        System.out.println(list);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public  ResponseEntity<User> insertUser(User user){
       int user1 = userService.insertUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        System.out.println(user);
        return ResponseEntity.created(uri).body(user);

    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/cpf/{cpf}")
    public ResponseEntity<Void> deleteUserByCPF(@PathVariable String cpf){
        userService.deleteUserByCPF(cpf);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable int id, @RequestBody User user) {
        int user1 = userService.updateUserById(id,user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping(value = "/cpf/{cpf}")
    public ResponseEntity<User> updateUserByCPF(@PathVariable String cpf,@RequestBody User user){
        int user1 = userService.updateUserByCPF(cpf,user);
        return ResponseEntity.ok().body(user);
    }

}