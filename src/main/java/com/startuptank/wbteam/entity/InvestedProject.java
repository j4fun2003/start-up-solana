package com.startuptank.wbteam.entity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invested_projects")
public class InvestedProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invested_project_id")
    private Integer investedProjectId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private User investor;

    @Column(name = "investment_amount")
    private Double investmentAmount;

    @Column(name = "investment_date")
    private Date investmentDate;

    @OneToMany(mappedBy = "investedProject")
    private List<InvestmentDetail> investmentDetails;

}

