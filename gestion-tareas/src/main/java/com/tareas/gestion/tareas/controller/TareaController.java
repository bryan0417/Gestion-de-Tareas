package com.tareas.gestion.tareas.controller;

import com.tareas.gestion.tareas.dto.TareaDto;
import com.tareas.gestion.tareas.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tarea")
@CrossOrigin(origins = "*")
public class TareaController {




    @Autowired
    TareaService tareaService;

    /**
     * Metodo Post para crear una tarea
     * @param dto
     * @return
     */
    @PostMapping("/crear")
    public ResponseEntity<Void> crearTarea(@RequestBody TareaDto dto) {
        tareaService.crearTarea(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Metodo Get para  consultar todas las tareas
     * @return
     */
    @GetMapping
    public ResponseEntity<List<TareaDto>> listarTareas() {
        return ResponseEntity.ok(tareaService.listarTareas());
    }

    /**
     * Metodo Get para hacer consulta por el titulo de la tarea
     * @param titulo
     * @return
     */
    @GetMapping("/consulta")
    public ResponseEntity<List<TareaDto>> consultarTarea(@RequestParam String titulo) {
        return ResponseEntity.ok(tareaService.consultarTarea(titulo));
    }

    /**
     * Metodo Put para actualizar la tarea
     * @param idTarea
     * @param dto
     * @return
     */
    @PutMapping("/{idTarea}")
    public ResponseEntity<Map<String, String>> actualizar(@PathVariable int idTarea, @RequestBody TareaDto dto) {
        boolean actualiza = tareaService.actualizar(idTarea, dto);
        if (!actualiza) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "La tarea no fue encontrada"));
        }
        return ResponseEntity.ok(Map.of("mensaje", "Tarea actualizada correctamente"));
    }

    /**
     * Metodo Delete para eliminar una tarea
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable int id) {
        boolean elimina = tareaService.eliminar(id);
        if (!elimina) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "La tarea no fue eliminada exitosamente"));
        }
        return ResponseEntity.ok(Map.of("mensaje", "Tarea eliminada correctamente"));
    }

    /**
     * Metodo Get para consultar una tarea por Id
     * @param idTarea
     * @return
     */
    @GetMapping("/{idTarea}")
    public ResponseEntity<TareaDto> consultarPorId(@PathVariable int idTarea) {
        TareaDto dto = tareaService.consultarPorId(idTarea);

        if (dto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dto);
    }

    /**
     * Metodo Get que verifica si el id ya existe
     * @param id
     * @return
     */
    @GetMapping("/verificar")
    public ResponseEntity<Boolean> verificarTarea(@RequestParam int id) {
        boolean existe = tareaService.existePorId(id);
        return ResponseEntity.ok(existe);
    }



}
