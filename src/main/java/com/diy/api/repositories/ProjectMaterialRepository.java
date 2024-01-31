package com.diy.api.repositories;

import org.springframework.stereotype.Repository;

import com.diy.api.entities.ProjectMaterial;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProjectMaterialRepository extends JpaRepository<ProjectMaterial, Long> { }
