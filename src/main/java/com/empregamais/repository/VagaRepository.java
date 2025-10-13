package com.empregamais.repository;

import com.empregamais.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;


    public interface VagaRepository extends JpaRepository<Vaga, Long> {
    }
