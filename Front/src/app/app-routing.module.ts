import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListadoComponent } from './components/listado-empleado/listado.component';
import { RecibosListadoComponent } from './components/listado-recibo/recibos-listado.component';
import { MainComponent } from './main/main.component';
import { AltaEmpleadoComponent } from './components/alta-empleado/alta-empleado.component';
import { AltaReciboComponent } from './components/alta-recibo/alta-recibo.component';
import { ReporteComponent } from './components/reporte/reporte.component';

const routes: Routes = [
  { path: '', redirectTo: 'main', pathMatch: 'full'},
  { path: 'main', component: MainComponent },
  { path: 'empleado', component: ListadoComponent },
  { path: 'recibo', component: RecibosListadoComponent },
  { path: 'alta-empleado', component: AltaEmpleadoComponent },
  { path: 'alta-recibo/:empleado', component: AltaReciboComponent },
  { path: 'reporte', component: ReporteComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
