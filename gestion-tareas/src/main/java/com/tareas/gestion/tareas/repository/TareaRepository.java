package com.tareas.gestion.tareas.repository;

import com.tareas.gestion.tareas.dto.TareaDto;
import com.tareas.gestion.tareas.model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TareaRepository {

    void crearTarea(Tarea tarea);

    List<Tarea> obtenerTareas();

    List<Tarea> consultarTarea(String titulo);

    boolean actualizar(int id, TareaDto dto);

    boolean eliminar(int id);

    Tarea consultarPorId(int idTarea);

    boolean existePorId(int id);
}
