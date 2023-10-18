package com.vhbeltramini.grp.service.rest;

import com.vhbeltramini.grp.model.Project;
import com.vhbeltramini.grp.model.ProjectItems;
import com.vhbeltramini.grp.model.User;
import com.vhbeltramini.grp.repository.ProjectItemsRepository;
import com.vhbeltramini.grp.repository.ProjectRepository;
import com.vhbeltramini.grp.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProjectController {
    private ProjectRepository repository;
    private UserRepository userRepository;
    private ProjectItemsRepository projectItemsRepository;

    public ProjectController(ProjectRepository repository, UserRepository userRepository, ProjectItemsRepository projectItemsRepository) {
        super();
        this.repository = repository;
        this.userRepository = userRepository;
        this.projectItemsRepository = projectItemsRepository;
    }

    @PostMapping("/projects")
    public ResponseEntity<Project> create(@RequestBody Project project) throws NoSuchAlgorithmException {
        List<ProjectItems> projectItems =  projectItemsRepository.saveAll(project.getItens());

        for (int i = 0; i < projectItems.size(); i++) {
            project.getItens().get(i).setId(projectItems.get(i).getId());
        }
        
        Project sevedProject = repository.save(project);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sevedProject.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/projects")
    public List<Project> getAll(){
        return repository.findAll();
    }

    @GetMapping(path= "/projects/{id}")
    public Project get(@PathVariable Long id) throws Exception {
        Optional<Project> user = repository.findById(Math.toIntExact(id));
        return user.orElse(null);
    }

    @GetMapping(path= "/projects/{coordenador_id}")
    public Project findByIDCoordenador(@PathVariable Long idCoordenador) throws Exception {
        User coordenador = userRepository.findById(idCoordenador);

        return repository.findByCoordenador(coordenador)
                .orElseThrow(() -> new Exception("Nenhum projeto encontrado para este coordenador :: " + coordenador));
    }

    @DeleteMapping(path= "/projects/{id}")
    public Map<String, Boolean> delete(@PathVariable Long id) throws Exception {
        Project project = repository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new Exception("User not found for this id :: " + id));

        repository.delete(project);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable(value = "id") Long id, @Valid @RequestBody Project project) throws Exception {
        project.setId(Math.toIntExact(id));

        final Project updatedUser = repository.save(project);

        return ResponseEntity.ok(updatedUser);
    }

}
