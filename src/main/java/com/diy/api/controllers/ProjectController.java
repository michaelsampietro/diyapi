package com.diy.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diy.api.entities.Project;
import com.diy.api.entities.ProjectInstruction;
import com.diy.api.repositories.ProjectInstructionRepository;
import com.diy.api.repositories.ProjectRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	ProjectRepository repository;

	@Autowired
	ProjectInstructionRepository projectInstructionRepository;

	@GetMapping("recent")
	public ResponseEntity<List<Project>> getRecentProjects() {
		return new ResponseEntity(repository.findAll(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Project>> getAllProjects() {
		return new ResponseEntity(repository.findAll(), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Project> postMethodName(@RequestBody Project project) {
		Project newProject = repository.save(project);

		int index = 0;
		List<ProjectInstruction> instructions = new ArrayList<ProjectInstruction>();
		for (ProjectInstruction projectInstruction : project.getProjectInstructions()) {
			projectInstruction.setInstructionOrder(index);
			projectInstruction.setProject(newProject);
			instructions.add(projectInstruction);
			index++;
		}

		var savedList = projectInstructionRepository.saveAll(instructions);

		newProject.setProjectInstructions(savedList);

		return new ResponseEntity<Project>(newProject, HttpStatus.CREATED) ;
	}
}
