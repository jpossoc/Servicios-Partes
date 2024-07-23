package com.proyecto.ivan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.ivan.controller.EmpresaController;
import com.proyecto.ivan.entities.Empresa;
import com.proyecto.ivan.service.EmpresaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmpresaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmpresaService empresaService;

    @InjectMocks
    private EmpresaController empresaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(empresaController).build();
    }

    @Test
    void testGetAllEmpresas() throws Exception {
        Empresa empresa1 = new Empresa(1L, "Empresa1", "123", "456");
        Empresa empresa2 = new Empresa(2L, "Empresa2", "789", "012");
        when(empresaService.findAll()).thenReturn(Arrays.asList(empresa1, empresa2));

        mockMvc.perform(get("/api/empresas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Empresa1")))
                .andExpect(jsonPath("$[1].nombre", is("Empresa2")));
    }

    @Test
    void testGetEmpresaById() throws Exception {
        Empresa empresa = new Empresa(1L, "Empresa", "123", "456");
        when(empresaService.findById(1L)).thenReturn(Optional.of(empresa));

        mockMvc.perform(get("/api/empresas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Empresa")));
    }

    @Test
    void testCreateEmpresa() throws Exception {
        Empresa empresa = new Empresa(null, "Empresa", "123", "456");
        when(empresaService.save(any(Empresa.class))).thenReturn(new Empresa(1L, "Empresa", "123", "456"));

        mockMvc.perform(post("/api/empresas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(empresa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idEmpresa", is(1)));
    }

    @Test
    void testUpdateEmpresa() throws Exception {
        Empresa empresa = new Empresa(1L, "Empresa", "123", "456");
        when(empresaService.findById(1L)).thenReturn(Optional.of(empresa));
        when(empresaService.save(any(Empresa.class))).thenReturn(new Empresa(1L, "Updated Empresa", "123", "456"));

        mockMvc.perform(put("/api/empresas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(new Empresa(1L, "Updated Empresa", "123", "456"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Updated Empresa")));
    }

    @Test
    void testDeleteEmpresa() throws Exception {
        when(empresaService.findById(1L)).thenReturn(Optional.of(new Empresa(1L, "Empresa", "123", "456")));

        mockMvc.perform(delete("/api/empresas/1"))
                .andExpect(status().isOk());

        verify(empresaService, times(1)).deleteById(1L);
    }
}
