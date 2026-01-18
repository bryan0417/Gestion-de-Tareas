import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Tarea } from '../../models/tarea';
import { TareaServiceService } from '../../services/tarea-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-principal',
  imports: [CommonModule],
  templateUrl: './principal.component.html',
  styleUrl: './principal.component.css'
})
export class PrincipalComponent {

  
  /**
   * Definición de cada variable que se utiliza en el componente
   */
  tareas: Tarea[] = [];

  
  /**
   * En el constructor se requiere de llamar al service y el router para el enrutamiento entre componentes
   * @param router 
   * @param tareaService 
   */
  constructor(
    private router: Router,
    private tareaService: TareaServiceService
  ) { }

  /**
   * Inciar la aplicación
   */
  ngOnInit(): void {
    this.cargarTareas();
  }

  
  /**
   * Metodo para cargar todas las tareas
   */
  cargarTareas() {
    this.tareaService.obtenerTareas().subscribe({
      next: (data) => {
        this.tareas = data;
        console.log("Tareas consultadas exitosamente", data);
      },
      error: (err) => {
        console.error("Error cargando las tareas", err);
      }
    })
  }

  /**
   * Metodo recibe un texto, que busca por el titulo de la tarea
   * @param event 
   */
  buscarTarea(event: Event) {
    const texto = (event.target as HTMLInputElement).value;
    console.log('Texto digitado:', texto);

    this.tareaService.buscarTarea(texto).subscribe({
      next: (data) => {
        this.tareas = data;
        console.log("Tareas consultada(s) exitosamente", data);
      },
      error: (err) => {
        console.error("Error cargando las tareas", err);
      }
    });
  }

  /**
   * Metodo para eliminar una tarea segun el id de la tarea
   * @param id 
   */
  eliminarTarea(id: number) {
    if (confirm('¿Estás seguro de eliminar esta tarea?')) {
      this.tareaService.eliminarTarea(id).subscribe({
        next: () => {
          alert('Tarea eliminada correctamente');
          this.cargarTareas();
        },
        error: (err) => {
          console.error('Error al eliminar', err);
          alert('Error al eliminar la tarea');
        }
      })
    }
  }


  /**
   * Metodos para enrutar entre componentes
   */
  
  irFormulario(): void {
    this.router.navigate(['/agregar']);
  }

  irActualizar(id: number): void {
    this.router.navigate(['/actualizar', id]);
  }

}
