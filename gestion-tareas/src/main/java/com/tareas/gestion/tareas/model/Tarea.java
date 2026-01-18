package com.tareas.gestion.tareas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {

    /**
     * Definicion de variables de la clase Tarea
     */

    private int idTarea;
    private String descripcion;
    private LocalDate fechaRegistro;
    private String titulo;
    private String estado;
    private LocalDate fechaLimite;
}
