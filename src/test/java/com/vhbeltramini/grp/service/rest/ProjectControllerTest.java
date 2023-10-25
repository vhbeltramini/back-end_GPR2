package com.vhbeltramini.grp.service.rest;

import com.vhbeltramini.grp.model.Project;
import com.vhbeltramini.grp.model.ProjectItems;
import com.vhbeltramini.grp.model.User;
import com.vhbeltramini.grp.repository.ProjectItemsRepository;
import com.vhbeltramini.grp.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProjectControllerTest {
    @Mock
    private ProjectRepository repository;

    @Mock
    private ProjectItemsRepository projectItemsRepository;
    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }



    @Test
    @DisplayName("teste coordenador criação")
    void createProjeto() throws Exception{
       Integer coordenadorId = 1;
        User coordenador = new User();
        coordenador.setId(coordenadorId);
        Project project = new Project();
        project.setNome("Test Project");
        project.setCoordenador(coordenador);

        List<ProjectItems> projectItems =  projectItemsRepository.saveAll(project.getItens());


        when(repository.save(any(Project.class))).thenReturn(project);



    }
    @Test
    @DisplayName("teste coordenador não existe")
    void findByIDCoordenadorcase2() throws Exception{
        Integer coordenadorId =5 ;
        User coordenador = new User();
        coordenador.setId(coordenadorId);

        when(repository.findByCoordenador(coordenador)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> {
            projectController.findByIDCoordenador(Long.valueOf(coordenadorId));
        });

    }


}
