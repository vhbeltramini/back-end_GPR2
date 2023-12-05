package com.vhbeltramini.grp.service.rest;

import com.vhbeltramini.grp.model.Project;
import com.vhbeltramini.grp.model.Resource;
import com.vhbeltramini.grp.repository.ProjectItemsRepository;
import com.vhbeltramini.grp.repository.ResourceRepository;
import com.vhbeltramini.grp.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.data.jpa.domain.Specification;
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
@CrossOrigin
public class ResourceController {
    private ResourceRepository repository;
    public ResourceController(ResourceRepository repository, UserRepository userRepository, ProjectItemsRepository projectItemsRepository) {
        super();
        this.repository = repository;
    }

    @PostMapping("/recurso")
    public ResponseEntity<Resource> create(@RequestBody Resource project) throws NoSuchAlgorithmException {

        Resource sevedProject = repository.save(project);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sevedProject.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/recurso")
    public ResponseEntity<List<Resource>> getResource(@RequestParam(value = "nome", required = false) String nome,
                                                      @RequestParam(value = "descricao", required = false) String descricao){
        Specification<Resource> specification = Specification.where(null);

        if (nome != null && !nome.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
        }

        if (descricao != null && !descricao.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("descricao")), "%" + descricao.toLowerCase() + "%"));
        }

        return ResponseEntity.ok(repository.findAll(specification));
    }

    @GetMapping(path= "/recurso/{id}")
    public Object get(@PathVariable Long id) throws Exception {
        Optional<Resource> resource = repository.findById(Math.toIntExact(id));
        if (resource.isPresent()) {
            return ResponseEntity.ok(resource.get());
        }

        return ResponseEntity.notFound();
    }

    @DeleteMapping(path= "/recurso/{id}")
    public Map<String, Boolean> delete(@PathVariable Long id) throws Exception {
        Resource project = repository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new Exception("Resource not found for this id :: " + id));

        repository.delete(project);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/recurso/{id}")
    public ResponseEntity<Resource> updateProject(@PathVariable(value = "id") Long id, @Valid @RequestBody Resource resource) {
        resource.setId(Math.toIntExact(id));

        final Resource updatedUser = repository.save(resource);

        return ResponseEntity.ok(updatedUser);
    }

}
