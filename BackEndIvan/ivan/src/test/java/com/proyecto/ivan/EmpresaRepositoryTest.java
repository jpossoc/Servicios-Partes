package com.proyecto.ivan;

import com.proyecto.ivan.entities.Empresa;
import com.proyecto.ivan.repository.EmpresaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmpresaRepositoryTest {

    @Autowired
    private EmpresaRepository empresaRepository;

    @BeforeEach
    void setUp() {
        empresaRepository.deleteAll();
    }

    @Test
    void testSaveAndFindById() {
        Empresa empresa = new Empresa(null, "Test Empresa", "123456789", "123456789");
        empresa = empresaRepository.save(empresa);

        Optional<Empresa> foundEmpresa = empresaRepository.findById(empresa.getIdEmpresa());
        assertThat(foundEmpresa).isPresent();
        assertThat(foundEmpresa.get().getNombre()).isEqualTo("Test Empresa");
    }

    @Test
    void testDeleteById() {
        Empresa empresa = new Empresa(null, "Test Empresa", "123456789", "123456789");
        empresa = empresaRepository.save(empresa);

        empresaRepository.deleteById(empresa.getIdEmpresa());
        Optional<Empresa> foundEmpresa = empresaRepository.findById(empresa.getIdEmpresa());
        assertThat(foundEmpresa).isNotPresent();
    }
}
