import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { TareaServiceService } from '../../services/tarea-service.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-actualizar',
  imports: [ReactiveFormsModule],
  templateUrl: './actualizar.component.html',
  styleUrl: './actualizar.component.css'
})
export class ActualizarComponent {

  /**
   * Definición de cada variable que se utiliza en el componente
   */
  tareaForm!: FormGroup;

  /**
   * * En el constructor se requiere de llamar al service, el router para el enrutamiento entre componentes, un formbuilder para creacion de 
   * formulario y route para transferir variables entre componentes
   * @param fb 
   * @param tareaService 
   * @param router 
   * @param route 
   */
  constructor(
    private fb: FormBuilder,
    private tareaService: TareaServiceService,
    public router: Router,
    private route: ActivatedRoute
  ) { }

  /**
   * Inciar la aplicación
   */
  ngOnInit() {
    this.tareaForm = this.fb.group({
      idTarea: ['', Validators.required],
      titulo: ['', Validators.required],
      descripcion: ['', Validators.required],
      fechaLimite: ['', Validators.required],
      estado: ['', Validators.required],
      fechaRegistro: [{ value: '', disabled: true }]
    });

    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.cargarTarea(+idParam);
    }
  }

  /**
   * Metodo para traer los datos de la tarea segun el id, segun la tarea seleccionada
   * @param id 
   */
  cargarTarea(id: number) {
    this.tareaService.obtenerTareaPorId(id).subscribe(tarea => {
      console.log(tarea);
      this.tareaForm.patchValue({
        idTarea: tarea.idTarea,
        titulo: tarea.titulo,
        descripcion: tarea.descripcion,
        fechaRegistro: tarea.fechaRegistro,
        estado: tarea.estado,
        fechaLimite: tarea.fechaLimite
      });
    });
  }

  /**
   * Metodo para actualizar la tarea
   * @returns 
   */
  actualizar() {
    if (this.tareaForm.invalid) return;

    const tarea = this.tareaForm.getRawValue();
    console.log(tarea.fechaRegistro)

    this.tareaService.actualizarTarea(tarea.idTarea, tarea).subscribe({
      next: (data) => {
        console.log("Tarea actualizada con exito", data);
        this.router.navigate(["/"]);
      },
      error: (err) => {
        console.log("Error al actualizar la tarea", err)
      }
    });
  }


}
