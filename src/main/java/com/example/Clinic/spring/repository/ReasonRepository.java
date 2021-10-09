package com.example.Clinic.spring.repository;
import com.example.Clinic.spring.model.Patient;
import com.example.Clinic.spring.model.Reason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReasonRepository  extends JpaRepository<Reason, Integer> {
    Reason findByReasonContaining(String reason);
}
