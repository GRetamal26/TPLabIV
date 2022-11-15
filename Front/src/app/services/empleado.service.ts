import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Empleado } from '../models/empleado';
import { Recibo } from '../models/recibo';
import { Reporte } from '../models/reporte';

@Injectable({
  providedIn: 'root',
})
export class EmpleadoService {
  private API_URL: string = 'http://localhost:8090/empleados/';

  constructor(private http: HttpClient) {}

  obtenerListado(): Observable<Empleado[]> {    
    return this.http.get<Empleado[]>(this.API_URL);
  }
 
  agregarEmpleado(empleado: Empleado): Observable<Empleado> {
    return this.http.post<Empleado>(this.API_URL+ 'nuevo', empleado);
  }

}
