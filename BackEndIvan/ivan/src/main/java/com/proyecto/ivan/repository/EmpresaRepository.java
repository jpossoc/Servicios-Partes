package com.proyecto.ivan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.ivan.entities.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    // Aquí puedes definir consultas personalizadas si las necesitas
}