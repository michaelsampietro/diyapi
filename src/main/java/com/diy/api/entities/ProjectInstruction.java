package com.diy.api.entities;

import java.io.Serializable;

import org.hibernate.annotations.Cascade;

// import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "project_instruction")
public class ProjectInstruction implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "instruction_order", nullable = false, precision = 0)
  private int instructionOrder = 0;

  @Column(name = "description", nullable = false, length = 5000)
  private String description;

  @Column(name = "image_url", length = 2000)
  private String imageUrl;
  
  @Column(name = "video_url", length = 2000)
  private String videoUrl;

  @ManyToOne
  @JoinColumn(name = "project_id")
  @JsonIgnore
  private Project project;

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getInstructionOrder() {
    return instructionOrder;
  }

  public void setInstructionOrder(int instructionOrder) {
    this.instructionOrder = instructionOrder;
  }

  public Project getProject() {
  return project;
  }

  public void setProject(Project project) {
  this.project = project;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getVideoUrl() {
    return videoUrl;
  }

  public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
  }
}
