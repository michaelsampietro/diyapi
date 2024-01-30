package com.diy.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diy.api.entities.Project;
import com.diy.api.services.ProjectService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@GetMapping
	public ResponseEntity<List<Project>> getAllProjects() {
		return new ResponseEntity<List<Project>>(projectService.getAll(), HttpStatus.OK);
	}

	@GetMapping("recent")
	public ResponseEntity<List<Project>> getRecentProjects() {
		return new ResponseEntity(projectService.getRecentProjects(), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Project> postMethodName(@RequestBody Project project) {
		return new ResponseEntity<Project>(projectService.saveProject(project), HttpStatus.CREATED);
	}
}
