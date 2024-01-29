package com.diy.api.repositories;

import org.springframework.stereotype.Repository;

import com.diy.api.entities.Project;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> { }
