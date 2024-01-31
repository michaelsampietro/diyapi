package com.diy.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

    List<ProjectInstruction> savedList = projectInstructionRepository.saveAll(instructions);
    newProject.setProjectInstructions(savedList);

    return newProject;
  }

  public Project updateProject(Project project) throws Exception {
    Long id = project.getId();
    if (id == null) {
      throw new NotFoundException();
    }

    Project projectToUpdate = projectRepository.findById(id).orElseThrow(() -> new NotFoundException());

    for (ProjectInstruction pi : project.getProjectInstructions()) {
      Long projectInstructionID = pi.getId();
      if (projectInstructionID == null) {
        pi.setInstructionOrder(0);
        pi.setProject(projectToUpdate);
        projectInstructionRepository.save(pi);
      }

      if (projectInstructionID != null) {
        ProjectInstruction projectInstructionToUpdate = projectInstructionRepository.getReferenceById(projectInstructionID);
        projectInstructionToUpdate.setDescription(pi.getDescription());
        projectInstructionToUpdate.setImageUrl(pi.getImageUrl());
        projectInstructionToUpdate.setVideoUrl(pi.getVideoUrl());
        projectInstructionRepository.save(projectInstructionToUpdate);
      }
    }

    projectToUpdate.setProjectInstructions(project.getProjectInstructions());
    return projectToUpdate;
  }

}
