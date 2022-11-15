import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Empleado } from 'src/app/models/empleado';
import { Recibo } from 'src/app/models/recibo';
import { EmpleadoService } from 'src/app/services/empleado.service';
import { ReciboService } from 'src/app/services/recibo.service';


@Component({
  selector: 'app-alta-recibo',
  templateUrl: './alta-recibo.component.html',
  styleUrls: ['./alta-recibo.component.css']
})
export class AltaReciboComponent implements OnInit,OnDestroy {
  b:boolean=true;
  formulario:FormGroup;
  legajo:number;
  empleados: Empleado[];
  recibo:Recibo;

  private subscription=new Subscription();

  constructor(   private formBuilder: FormBuilder,
    private serviceRecibo: ReciboService,
    private serviceEmpleado: EmpleadoService,
    private router: Router,
    private activatedRoute: ActivatedRoute) {       
    }

  ngOnInit(): void {
    this.cargarCombo();    
    this.formulario = this.formBuilder.group({
      legajo: [this.legajo, Validators.required],
      mes: [, Validators.required],
      anio: [, Validators.required],
      antiguedad: [, Validators.required],
      jubilacion: [, Validators.required],
      obraSocial: [, Validators.required],
      fondoComplejidad: [, Validators.required]
    });
    this.formulario.controls['legajo'].disable()
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  cargarCombo() {
    this.subscription.add(
      this.activatedRoute.params.subscribe({
        next: (params) => {
          this.legajo = params['empleado'];
        }
      })
    );
    this.subscription.add(
      this.serviceEmpleado.obtenerListado().subscribe({
        next: (respuesta: Empleado[]) => {
          this.empleados = respuesta;
        },
        error: () => {
          alert('Error al obtener los empleados');
        },
      })
    );
  }

  guardarRecibo() {
    this.recibo = this.formulario.value;
    this.recibo.legajo=this.legajo
    console.log(this.recibo)

    if (this.formulario.valid) {
      this.subscription.add(
        this.serviceRecibo.agregarRecibo(this.recibo).subscribe({
          next: () => {
            alert('Recibo guardado!');
            this.router.navigate(['recibo']);
          },
          error: () => {            
            alert('No se pudo cargar el recibo');
          },
        })
      );
    }
    else{
      alert('Campos faltantes')
    }
  }

}
