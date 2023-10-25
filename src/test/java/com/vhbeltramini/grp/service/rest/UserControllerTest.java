package com.vhbeltramini.grp.service.rest;

import com.vhbeltramini.grp.model.User;
import com.vhbeltramini.grp.model.enums.Role;
import com.vhbeltramini.grp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("criação usuário")
    void createuser()  {
        User user = new User();
        user.setId(1);
        user.setFirstName("angelina");
        user.setLastName("teste");
        user.setCpf("07267615325");
        user.setEmail("teste@agora.com");
        user.setPassword("aquiehsoteste");
        when(userRepository.save(any(User.class))).thenReturn(user);

    }

    @Test
    @DisplayName("verifica usuário")
    void getUsuario() {
        User user = new User();
        user.setId(1);
        user.setFirstName("angelina");
        user.setLastName("teste");
        user.setCpf("1111111111");
        user.setEmail("teste@agora.com");
        user.setPassword("aquiehsoteste");
        user.setRole(Role.valueOf("COORDENADOR"));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    }

    @Test
    @DisplayName("Teste de all users")
    public void testGetAllUsers() throws NoSuchAlgorithmException {
        List<User> userList = new ArrayList<>();
        userList.add(new User("carlos","souza","teste","1111111","teste@gmail.com",Role.valueOf("COORDENADOR"))); // Adicione quantos usuários forem necessários para o teste

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userController.getAll();

        assertEquals(userList.size(), result.size());
    }

    @Test
    @DisplayName("não encontrou o email e lançou a exception")
    void findByEmail() {
        String nonExistingEmail = "nonexistingemail@example.com";

        when(userRepository.findByEmail(nonExistingEmail)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> {
            userController.findByEmail(nonExistingEmail);
        });
    }
}
