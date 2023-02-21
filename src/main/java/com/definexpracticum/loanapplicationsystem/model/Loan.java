package com.definexpracticum.loanapplicationsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "loans")
public class Loan{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ELoanStatus status;

    @Column(name = "loan_limit")
    private Integer loanLimit;

    @CreationTimestamp
    @Column(name = "application_date")
    private Date applicationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
