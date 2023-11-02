package com.startuptank.wbteam.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "project_fields")
public class ProjectField {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "field_id")
    private Integer fieldId;

    @Column(name = "field_name")
    private String fieldName;

    @OneToMany(mappedBy = "projectField")
    private List<Project> projects;

    // Constructors, getters, and setters
}
