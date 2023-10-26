package com.startuptank.wbteam.entity;


import com.startuptank.wbteam.constant.InvestmentType;
import com.startuptank.wbteam.constant.ProjectStatus;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "project_image_url")
    private String projectImageUrl;

    @Column(name = "project_video_url")
    private String projectVideoUrl;

    @ManyToOne
    @JoinColumn(name = "project_field_id")
    private ProjectField projectField;

    @Column(name = "funding_goal")
    private Double fundingGoal;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "project_status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    @Column(name = "investment_type")
    @Enumerated(EnumType.STRING)
    private InvestmentType investmentType;

    @OneToMany(mappedBy = "project")
    private List<InvestedProject> investedProjects;

    // Constructors, getters, and setters
}

