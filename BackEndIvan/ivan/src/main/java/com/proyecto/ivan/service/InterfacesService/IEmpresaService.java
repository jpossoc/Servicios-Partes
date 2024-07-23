package com.proyecto.ivan.service.InterfacesService;

import com.proyecto.ivan.entities.Empresa;

import java.util.List;
import java.util.Optional;

public interface IEmpresaService {

    List<Empresa> findAll();

    Optional<Empresa> findById(Long id);

    Empresa save(Empresa empresa);

    void deleteById(Long id);
}
