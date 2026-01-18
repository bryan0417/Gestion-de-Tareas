package com.tareas.gestion.tareas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaDto {

    /**
     * Patron DTO para la transferencia de datos entre back y front
     */

    private int idTarea;
    private String descripcion;
    private LocalDate fechaRegistro;
    private String titulo;
    private String estado;
    private LocalDate fechaLimite;
}
