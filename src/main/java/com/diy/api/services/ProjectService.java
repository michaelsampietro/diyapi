package com.diy.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.diy.api.entities.Project;
import com.diy.api.entities.ProjectInstruction;
import com.diy.api.repositories.ProjectInstructionRepository;
import com.diy.api.repositories.ProjectRepository;

@Service
public class ProjectService {

  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  ProjectInstructionRepository projectInstructionRepository;

  private List<Project> getAllSortedByDate() {
    return projectRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
  }

  public List<Project> getAll() {
    return this.getAllSortedByDate();
  }

  public List<Project> getRecentProjects() {
    return this.getAllSortedByDate().subList(0, 1);
  }

  public Project saveProject(Project project) {
    Project newProject = projectRepository.save(project);

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

    return newProject;
  }

}
