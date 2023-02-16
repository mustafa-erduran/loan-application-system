package com.definexpracticum.loanapplicationsystem.repository;

import com.definexpracticum.loanapplicationsystem.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<Loan,Long> {

}
