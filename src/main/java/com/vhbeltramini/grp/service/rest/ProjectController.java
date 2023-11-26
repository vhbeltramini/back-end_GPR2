package com.vhbeltramini.grp.service.rest;

import com.vhbeltramini.grp.model.Project;
import com.vhbeltramini.grp.model.ProjectItems;
import com.vhbeltramini.grp.model.Resource;
import com.vhbeltramini.grp.model.User;
import com.vhbeltramini.grp.model.enums.ProjectStatus;
import com.vhbeltramini.grp.repository.ProjectItemsRepository;
import com.vhbeltramini.grp.repository.ProjectRepository;
import com.vhbeltramini.grp.repository.ResourceRepository;
import com.vhbeltramini.grp.repository.UserRepository;
import com.vhbeltramini.grp.service.DTO.ProjectMetricsDTO;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.validation.Valid;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@RestController
@CrossOrigin
public class ProjectController {
    private ProjectRepository repository;
    private UserRepository userRepository;
    private ProjectItemsRepository projectItemsRepository;
    private ResourceRepository resourceRepository;

    public ProjectController(ProjectRepository repository, UserRepository userRepository, ProjectItemsRepository projectItemsRepository, ResourceRepository resourceRepository) {
        super();
        this.repository = repository;
        this.userRepository = userRepository;
        this.projectItemsRepository = projectItemsRepository;
        this.resourceRepository = resourceRepository;
    }

    @PostMapping("/projects")
    public ResponseEntity<Project> create(@RequestBody Project project) throws NoSuchAlgorithmException {
        List<ProjectItems> projectItems =  projectItemsRepository.saveAll(project.getItens());
        List<Resource> resources =  resourceRepository.saveAll(project.getResources());

        for (int i = 0; i < projectItems.size(); i++) {
            project.getItens().get(i).setId(projectItems.get(i).getId());
        }
        for (int i = 0; i < resources.size(); i++) {
            project.getResources().get(i).setId(resources.get(i).getId());
        }
        project.setSituacao(ProjectStatus.PENDENTE.toString());
        
        Project sevedProject = repository.save(project);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sevedProject.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getProjects(@RequestParam(value = "coordenador", required = false) String coordenador,
                                     @RequestParam(value = "projeto", required = false) String projeto,
                                     @RequestParam(value = "anoFimEmpenho", required = false) String anoFimEmpenho,
                                     @RequestParam(value = "anoInicioEmpenho", required = false) String anoInicioEmpenho,
                                     @RequestParam(value = "exercicio", required = false) Integer exercicio,
                                     @RequestParam(value = "situacao", required = false) String situacao){
        Specification<Project> specification = Specification.where(null);

        if (coordenador != null && !coordenador.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) -> {
                Join<Project, User> relatedEntityJoin = root.join("user", JoinType.INNER);
                return criteriaBuilder.like(relatedEntityJoin.get("firstName"), "%" + coordenador + "%");
            });
        }

        if (projeto != null && !projeto.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("projeto"), "%" + projeto + "%"));
        }

        if (exercicio != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("exercicio"), exercicio));
        }

        if (anoFimEmpenho != null && !anoFimEmpenho.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("anoFimEmpenho"), anoFimEmpenho));
        }

        if (anoInicioEmpenho != null && !anoInicioEmpenho.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("anoInicioEmpenho"), anoInicioEmpenho));
        }

        if (situacao != null && !situacao.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("situacao"), situacao));
        }


        return ResponseEntity.ok(repository.findAll(specification));
    }

    @GetMapping("/projects/metrics")
    public ResponseEntity<List<ProjectMetricsDTO>> getProjectsMetrics(@RequestParam(value = "coordenador", required = false) String coordenador,
                                     @RequestParam(value = "projeto", required = false) String projeto,
                                     @RequestParam(value = "anoFimEmpenho", required = false) String anoFimEmpenho,
                                     @RequestParam(value = "anoInicioEmpenho", required = false) String anoInicioEmpenho,
                                     @RequestParam(value = "exercicio", required = false) Integer exercicio,
                                     @RequestParam(value = "situacao", required = false) String situacao) throws NoSuchAlgorithmException {
        Specification<Project> specification = Specification.where(null);

        if (coordenador != null && !coordenador.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) -> {
                Join<Project, User> relatedEntityJoin = root.join("user", JoinType.INNER);
                return criteriaBuilder.like(relatedEntityJoin.get("firstName"), "%" + coordenador + "%");
            });
        }

        if (projeto != null && !projeto.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("projeto"), "%" + projeto + "%"));
        }

        if (exercicio != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("exercicio"), exercicio));
        }

        if (anoFimEmpenho != null && !anoFimEmpenho.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("anoFimEmpenho"), anoFimEmpenho));
        }

        if (anoInicioEmpenho != null && !anoInicioEmpenho.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("anoInicioEmpenho"), anoInicioEmpenho));
        }

        if (situacao != null && !situacao.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("situacao"), situacao));
        }

        List<Project> projects = repository.findAll(specification);

        List<ProjectMetricsDTO> projectMetricsDTOS = new ArrayList<>();
        for (Project project: projects) {
            ProjectMetricsDTO projectMetrics = new ProjectMetricsDTO(project.getCoordenador().getFullName(), project.getValorSaldo(), project.getValorExecutado());
            projectMetricsDTOS.add(projectMetrics);
        }

        return ResponseEntity.ok(projectMetricsDTOS);
    }

    @GetMapping(path= "/projects/{id}")
    public Project get(@PathVariable Long id) throws Exception {
        Optional<Project> user = repository.findById(Math.toIntExact(id));
        return user.orElse(null);
    }

    @GetMapping(path= "/projects/coordinator/{coordinator_id}")
    public ResponseEntity<List<Project>> findByIDCoordenador(@PathVariable Long idCoordenador) throws Exception {
        User coordenador = userRepository.findById(idCoordenador);

        return ResponseEntity.ok(repository.findByCoordenador(coordenador));
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

        projectItemsRepository.saveAll(project.getItens());
        resourceRepository.saveAll(project.getResources());

        final Project updatedUser = repository.save(project);

        return ResponseEntity.ok(updatedUser);
    }

}
