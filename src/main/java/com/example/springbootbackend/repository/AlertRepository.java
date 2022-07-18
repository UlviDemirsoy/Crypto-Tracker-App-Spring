package com.example.springbootbackend.repository;
import com.example.springbootbackend.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlertRepository extends JpaRepository<Alert, Long>{


}
