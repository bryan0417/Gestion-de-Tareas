import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tarea } from '../models/tarea';

@Injectable({
  providedIn: 'root'
})
export class TareaServiceService {

  constructor(private http: HttpClient) { }

  private api = 'http://localhost:1497/tarea'

  /**
   * Este servicio contiene los métodos encargados de comunicarse con las APIs
   * desarrolladas en Spring Boot, permitiendo realizar las distintas operaciones
   * y funcionalidades relacionadas con la gestión de tareas. 
   */

  obtenerTareas(): Observable<Tarea[]> {
    return this.http.get<Tarea[]>(this.api);
  }

  guardarTarea(tarea: Tarea): Observable<any> {
    return this.http.post(this.api + "/crear", tarea);
  }

  actualizarTarea(idTarea: number, tarea: Tarea): Observable<string> {
    return this.http.put<string>(this.api + "/" + idTarea, tarea);
  }

  obtenerTareaPorId(id: number): Observable<Tarea> {
    return this.http.get<Tarea>(this.api + '/' + id);
  }

  buscarTarea(titulo: string): Observable<Tarea[]> {
    return this.http.get<Tarea[]>(this.api + '/consulta', {
      params: {
        titulo: titulo
      }
    });
  }

  eliminarTarea(id: number): Observable<string> {
    return this.http.delete<string>(this.api + "/" + id);
  }

  verificarId(id: number): Observable<boolean> {
    return this.http.get<boolean>(this.api + "/verificar", {
      params: {
        id: id
      }
    }
    );
  }

}
