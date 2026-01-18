package com.tareas.gestion.tareas.service;

import com.tareas.gestion.tareas.dto.TareaDto;
import com.tareas.gestion.tareas.model.Tarea;
import com.tareas.gestion.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TareaService {

    @Autowired
    TareaRepository tareaRepository;

    public void crearTarea(TareaDto dto) {
        Tarea tarea = new Tarea();
        tarea.setIdTarea(dto.getIdTarea());
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setEstado("PENDIENTE");
        tarea.setFechaRegistro(LocalDate.now());
        tarea.setFechaLimite(dto.getFechaLimite());

        tareaRepository.crearTarea(tarea);
    }

    public List<TareaDto> listarTareas() {
        List<Tarea> tareas = tareaRepository.obtenerTareas();

        List<TareaDto> lista = new ArrayList<>();

        for (Tarea t: tareas) {
            TareaDto dto = convertirModelADto(t);

            lista.add(dto);
        }
        return lista;
    }

    public List<TareaDto> consultarTarea(String titulo) {
        List<Tarea> tareas = tareaRepository.consultarTarea(titulo);

        List<TareaDto> lista = new ArrayList<>();

        for (Tarea t: tareas) {
            TareaDto dto = convertirModelADto(t);

            lista.add(dto);
        }

        return lista;
    }

    public boolean actualizar(int id, TareaDto dto) {
        boolean actualizacion = tareaRepository.actualizar(id, dto);

        return actualizacion;
    }

    public boolean eliminar(int id) {
        boolean eliminarTarea = tareaRepository.eliminar(id);

        return eliminarTarea;
    }

    public TareaDto consultarPorId(int idTarea) {
        Tarea tarea = tareaRepository.consultarPorId(idTarea);
        if (tarea == null) {
            return null;
        }
        return convertirModelADto(tarea);
    }

    public boolean existePorId(int id) {
        boolean existe = tareaRepository.existePorId(id);
        return existe;
    }


    public TareaDto convertirModelADto(Tarea tarea) {
        TareaDto dto = new TareaDto();
        dto.setIdTarea(tarea.getIdTarea());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setFechaRegistro(tarea.getFechaRegistro());
        dto.setTitulo(tarea.getTitulo());
        dto.setEstado(tarea.getEstado());
        dto.setFechaLimite(tarea.getFechaLimite());

        return dto;
    }
}
