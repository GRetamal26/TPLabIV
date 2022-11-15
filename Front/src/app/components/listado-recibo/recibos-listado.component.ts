import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Empleado } from 'src/app/models/empleado';
import { Recibo } from 'src/app/models/recibo';
import { EmpleadoService } from 'src/app/services/empleado.service';
import { ReciboService } from 'src/app/services/recibo.service';

@Component({
  selector: 'app-recibos-listado',
  templateUrl: './recibos-listado.component.html',
  styleUrls: ['./recibos-listado.component.css'],
})
export class RecibosListadoComponent implements OnInit, OnDestroy {
  recibos: Recibo[];
  empleados: Empleado[];

  formulario: FormGroup;

  private suscription = new Subscription();

  constructor(private serviceEmpleado: EmpleadoService, private serviceRecibo: ReciboService, private formBuilder: FormBuilder, private router: Router) {}
  ngOnDestroy(): void {
    this.suscription.unsubscribe();
  }

  ngOnInit(): void {
    this.formulario = this.formBuilder.group({
      listaEmpleados: ['', Validators.required],
    });
    this.suscription.add(
      this.serviceEmpleado.obtenerListado().subscribe({
        next: (respuesta: Empleado[]) => {
          this.empleados = respuesta;
        },
        error: () => {
          alert('Error en cbo empleados');
        },
      })
    );
    this.cambioCombo();
  }

  cambioCombo() {
    this.formulario.controls['listaEmpleados'].valueChanges.subscribe((x) => {
      this.suscription.add(
        this.serviceRecibo.obtenerRecibosPorEmpleado(x).subscribe({
          next: (respuesta: Recibo[]) => {
            this.recibos = respuesta;
          },
          error: () => {
            alert('Error en listado recibos');
          },
        })
      );
    });
  }

  nuevoRecibo(){
    if(this.formulario.controls['listaEmpleados'].value == ''){
      alert('Debe seleccionar un empleado!');
    }
    else{
      this.router.navigate(['alta-recibo', this.formulario.controls['listaEmpleados'].value])
    }
  }
}
