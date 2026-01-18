import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TareaServiceService } from '../../services/tarea-service.service';
import { FormBuilder, ReactiveFormsModule, Validators, FormGroup } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-formulario',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './formulario.component.html',
  styleUrl: './formulario.component.css'
})
export class FormularioComponent {

  /**
   * Definición de cada variable que se utiliza en el componente
   */
  tareaForm!: FormGroup;
  idExiste: boolean = false;

  /**
   * En el constructor se requiere de llamar al service, el router para el enrutamiento entre componentes y un formbuilder para creacion de 
   * formulario
   * 
   * @param fb 
   * @param tareaService 
   * @param router 
   */
  constructor(
    private fb: FormBuilder,
    private tareaService: TareaServiceService,
    public router: Router
  ) { }

  /**
   * Inciar la aplicación
   */
  ngOnInit() {
    this.tareaForm = this.fb.group({
      idTarea: ['', Validators.required],
      titulo: ['', Validators.required],
      descripcion: ['', Validators.required],
      fechaLimite: ['', Validators.required]
    });
  }

  /**
   * Metodo para guardar el formulario que se envia
   * @returns 
   */
  guardar(): void {
    if (this.tareaForm.invalid) {
      this.tareaForm.markAllAsTouched();
      return;
    }

    this.tareaService.guardarTarea(this.tareaForm.value).subscribe({
      next: () => {
        console.log("Tarea agregada exitosamente");
        alert('Tarea agregada correctamente');
        this.router.navigate(['/']);
      },
      error: (err) => {
        console.error('Error al guardar', err);
      }
    });
  }

  /**
   * Metodo para verficar si el id ya existe y no repetir
   * @param event 
   * @returns 
   */
  verificarId(event: Event) {
    const texto = (event.target as HTMLInputElement).value;
    const numero = Number(texto);
    console.log('Texto digitado:', texto);

    // Evita llamadas innecesarias
    if (isNaN(numero) || numero <= 0) {
      this.idExiste = false;
      return;
    }

    this.tareaService.verificarId(numero).subscribe({
      next: (existe: boolean) => {
        this.idExiste = existe;
      },
      error: err => {
        console.error(err);
        this.idExiste = false;
      }
    });
  }

}
