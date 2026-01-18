package com.tareas.gestion.tareas.repository;

import com.tareas.gestion.tareas.dto.TareaDto;
import com.tareas.gestion.tareas.model.Tarea;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class TareaRepositoryImp implements TareaRepository{

    private static final String FILE = "data/tareas.txt";

    @Override
    public void crearTarea(Tarea tarea) {
        try {
            crarArchivo();

            try(BufferedWriter escritura = new BufferedWriter(

                    new FileWriter(FILE, true))) {
                escritura.write(formatearLinea(tarea));
                escritura.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al gurdar tarea", e);
        }
    }

    @Override
    public List<Tarea> obtenerTareas() {
        List<Tarea> tareas = new ArrayList<>();

        try {
            crarArchivo();
            try (BufferedReader lectura = new BufferedReader(
                    new FileReader(FILE))) {
                String linea;

                while ((linea = lectura.readLine()) != null) {
                    if (!linea.isBlank()) {
                        tareas.add(parsearLinea(linea));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer las tareas", e);
        }

        return tareas;
    }

    @Override
    public List<Tarea> consultarTarea(String titulo) {
        List<Tarea> resultado = new ArrayList<>();

        for (Tarea t: obtenerTareas()) {
            if (t.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    @Override
    public boolean actualizar(int id, TareaDto dto) {
        List<Tarea> tareas = obtenerTareas();
        boolean encontrada = false;

        for (Tarea t: tareas) {
            if (id == t.getIdTarea()) {
                t.setDescripcion(dto.getDescripcion());
                t.setFechaRegistro(dto.getFechaRegistro());
                t.setTitulo(dto.getTitulo());
                t.setEstado(dto.getEstado());
                t.setFechaLimite(dto.getFechaLimite());

                encontrada = true;
                break;
            }
        }

        if (encontrada) {
            try(BufferedWriter escritura = new BufferedWriter(
                    new FileWriter(FILE))) {

                for (Tarea x: tareas) {
                    escritura.write(formatearLinea(x));
                    escritura.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException("Error al gurdar tarea", e);
            }
        }

        return encontrada;
    }

    @Override
    public boolean eliminar(int id) {
        List<Tarea> tareas = obtenerTareas();
        boolean encontrada = false;

        Iterator<Tarea> it = tareas.iterator();

        while (it.hasNext()) {
            Tarea t = it.next();
            if (t.getIdTarea() == id) {
                it.remove();
                encontrada = true;
                break;
            }
        }

        if (encontrada) {
            try(BufferedWriter escritura = new BufferedWriter(
                    new FileWriter(FILE))) {

                for (Tarea x: tareas) {
                    escritura.write(formatearLinea(x));
                    escritura.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException("Error al eliminar tarea", e);
            }
        }

        return encontrada;
    }

    @Override
    public Tarea consultarPorId(int idTarea) {
        for (Tarea t : obtenerTareas()) {
            if (t.getIdTarea() == idTarea) {
                return t;
            }
        }
        return null;
    }

    @Override
    public boolean existePorId(int id) {
        for (Tarea t : obtenerTareas()) {
            if (t.getIdTarea() == id) {
                return true;
            }
        }
        return false;
    }

    private String formatearLinea(Tarea tarea) {
        return tarea.getIdTarea() + ";" +
                tarea.getDescripcion() + ";" +
                tarea.getFechaRegistro() + ";" +
                tarea.getTitulo() + ";" +
                tarea.getEstado() + ";" +
                tarea.getFechaLimite();
    }

    private Tarea parsearLinea(String linea) {
        String[] partes = linea.split(";");

        Tarea tarea = new Tarea();
        tarea.setIdTarea(Integer.parseInt(partes[0]));
        tarea.setDescripcion(partes[1]);
        tarea.setFechaRegistro(LocalDate.parse(partes[2]));
        tarea.setTitulo(partes[3]);
        tarea.setEstado(partes[4]);
        tarea.setFechaLimite(LocalDate.parse(partes[5]));

        return tarea;
    }

    private void crarArchivo() throws IOException {
        File file = new File(FILE);

        if (!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
    }
}
