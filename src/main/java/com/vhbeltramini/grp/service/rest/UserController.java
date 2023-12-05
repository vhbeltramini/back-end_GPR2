package com.vhbeltramini.grp.service.rest;

import com.vhbeltramini.grp.model.User;
import com.vhbeltramini.grp.model.enums.Role;
import com.vhbeltramini.grp.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {
    private UserRepository repository;

    public UserController(UserRepository repository) {
        super();
        this.repository = repository;
    }

    @PostMapping("/login/users")
    public ResponseEntity<User> create(@RequestBody User user) throws NoSuchAlgorithmException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setFullName(user.getFirstName(), user.getLastName());
        user.setPasswordHash(encodedPassword);
        user.setRole(Role.COORDENADOR);
        User sevedUser = repository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sevedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users")
    public List<User> getAll(){
        return repository.findAll();
    }

    @GetMapping(path= "/users/{id}")
    public User get(@PathVariable Long id) throws Exception {
        Optional<User> user = repository.findById(Math.toIntExact(id));
        return user.orElse(null);
    }

    @GetMapping(path= "/users/email/{email}")
    public User findByEmail(@PathVariable String email) throws Exception {
        return repository.findByEmail(email)
                .orElseThrow(() -> new Exception("User not found for this email :: " + email));
    }

    @DeleteMapping(path= "/users/{id}")
    public Map<String, Boolean> delete(@PathVariable Long id) throws Exception {
        User user = repository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new Exception("User not found for this id :: " + id));

        repository.delete(user);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateEmployee(@PathVariable(value = "id") Long id, @Valid @RequestBody User user) throws Exception {

        user.setId(Math.toIntExact(id));
        user.setFullName(user.getFirstName(), user.getLastName());

        final User updatedUser = repository.save(user);

        return ResponseEntity.ok(updatedUser);
    }

}
