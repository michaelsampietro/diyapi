package com.diy.api.repositories;

import org.springframework.stereotype.Repository;

import com.diy.api.entities.ProjectInstruction;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProjectInstructionRepository extends JpaRepository<ProjectInstruction, Long> {
}
