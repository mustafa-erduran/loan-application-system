package com.definexpracticum.loanapplicationsystem.repository;

import com.definexpracticum.loanapplicationsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByCitizenId(String citizenId);

    Optional<User> findByCitizenIdAndBirthDate(String citizenId, String birthDate);


}
