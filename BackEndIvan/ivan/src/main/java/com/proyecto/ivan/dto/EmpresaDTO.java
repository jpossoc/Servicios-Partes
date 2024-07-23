package com.proyecto.ivan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {

    private Long idEmpresa;
    private String nombre;
    private String nit;
    private String telefonoContacto;
}
