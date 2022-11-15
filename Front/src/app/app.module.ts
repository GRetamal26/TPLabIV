import { registerLocaleData } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { LOCALE_ID, NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListadoComponent } from './components/listado-empleado/listado.component';
import { RecibosListadoComponent } from './components/listado-recibo/recibos-listado.component';
import { MainComponent } from './main/main.component';
import { EmpleadoService } from './services/empleado.service';
import localeEs from '@angular/common/locales/es-AR';
import { AltaEmpleadoComponent } from './components/alta-empleado/alta-empleado.component';
import { AltaReciboComponent } from './components/alta-recibo/alta-recibo.component';
import { ReporteComponent } from './components/reporte/reporte.component';
import { NgChartsModule } from 'ng2-charts';
import { ReciboService } from './services/recibo.service';
registerLocaleData(localeEs, 'es-AR');

@NgModule({
  declarations: [
    AppComponent,
    ListadoComponent,
    RecibosListadoComponent,
    MainComponent,
    AltaEmpleadoComponent,
    AltaReciboComponent,
    ReporteComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgChartsModule
  ],
  providers: [{ provide: LOCALE_ID, useValue: 'es-AR' }, EmpleadoService, ReciboService],
  bootstrap: [AppComponent],
})
export class AppModule {}
