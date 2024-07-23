package com.proyecto.ivan.service;

import com.proyecto.ivan.service.InterfacesService.IEmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.proyecto.ivan.repository.EmpresaRepository;
import com.proyecto.ivan.entities.Empresa;

@Service
@RequiredArgsConstructor
public class EmpresaService implements IEmpresaService {

    private final EmpresaRepository empresaRepository;

    @Override
    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    @Override
    public Optional<Empresa> findById(Long id) {
        return empresaRepository.findById(id);
    }

    @Override
    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public void deleteById(Long id) {
        empresaRepository.deleteById(id);
    }
}
