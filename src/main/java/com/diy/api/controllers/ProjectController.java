package com.diy.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diy.api.entities.Project;
import com.diy.api.repositories.ProjectRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	ProjectRepository repository;

	@GetMapping("recent")
	public List<Project> getRecentProjects() {
		return repository.findAll();
	}

	@GetMapping
	public List<Project> getAllProjects() {
		return repository.findAll();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Project postMethodName(@RequestBody Project project) {
		return repository.save(project);
	}
}
