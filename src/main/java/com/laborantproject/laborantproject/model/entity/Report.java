package com.laborantproject.laborantproject.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileNo;
    private String diagnosisTitle;
    private String diagnosis;
    private Date date;
    private String attachmentId;
    @ManyToOne
    private Laboratorian laboratorian;
    @ManyToOne
    private Patient patient;

}

