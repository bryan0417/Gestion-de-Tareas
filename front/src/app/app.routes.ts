import { Routes } from '@angular/router';
import { PrincipalComponent } from './components/principal/principal.component';
import { FormularioComponent } from './components/formulario/formulario.component';
import { ActualizarComponent } from './components/actualizar/actualizar.component';

export const routes: Routes = [
    {path: "", component: PrincipalComponent},
    {path: "agregar", component: FormularioComponent},
    {path: "actualizar/:id", component: ActualizarComponent}
];
