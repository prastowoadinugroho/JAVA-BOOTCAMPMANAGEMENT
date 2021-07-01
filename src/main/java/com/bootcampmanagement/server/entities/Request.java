package com.bootcampmanagement.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "request")
@XmlRootElement

public class Request implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "interview_date")
    @Temporal(TemporalType.DATE)
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date interviewDate;
    
    @Basic(optional = false)
    @Column(name = "candidate_needed")
    private int candidateNeeded;
    @Column(name = "status")
    private String status;
    @Column(name = "status_date")
    @Temporal(TemporalType.DATE)
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date statusDate;
    
//    @Column(name = "candidate_sent",columnDefinition = "int default 0")
    @Column(name = "candidate_sent")    
    private int candidateSent;
    
    @Column(name = "reject_note")    
    private String rejectNote;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @JoinColumn(name = "cust_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Customer customer;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Skill skill;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "request", fetch = FetchType.LAZY)
    private List<Interview> interviewList;

    public Request() {
        
    }
}
