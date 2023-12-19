package com.startuptank.wbteam.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
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
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date deadline;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "project_status")
    private String projectStatus;

    @Column(name = "investment_type")
    private String investmentType;

    @OneToMany(mappedBy = "project")
    private List<InvestedProject> investedProjects;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "address_nft")
    private String addressNft;
    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", projectImageUrl='" + projectImageUrl + '\'' +
                ", projectVideoUrl='" + projectVideoUrl + '\'' +
                ", fundingGoal=" + fundingGoal +
                ", deadline=" + deadline +
                ", contactEmail='" + contactEmail + '\'' +
                ", projectStatus='" + projectStatus + '\'' +
                ", investmentType='" + investmentType + '\'' +
                ", addressNft='" + addressNft + '\'' +
                '}';
    }

    // Constructors, getters, and setters
}

