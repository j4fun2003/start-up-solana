package com.startuptank.wbteam.entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "investment_details")
public class InvestmentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investment_id")
    private Integer investmentId;

    @ManyToOne
    @JoinColumn(name = "invested_project_id")
    private InvestedProject investedProject;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private User investor;

    @Column(name = "investment_amount")
    private Double investmentAmount;

    @Column(name = "investment_date")
    private Date investmentDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "additional_notes")
    private String additionalNotes;

}

