package com.example.dataservice.Repository;

import com.example.dataservice.Entity.Dasturchi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DasturchiRepository extends JpaRepository<Dasturchi, Integer> {
    boolean existsByEmail(String email);
}
