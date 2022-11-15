import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recibo } from '../models/recibo';
import { Reporte } from '../models/reporte';

@Injectable({
  providedIn: 'root'
})
export class ReciboService {

  private API_URL: string = 'http://localhost:8090/recibo/';

  constructor(private http: HttpClient) { }

  obtenerRecibosPorEmpleado(legajo: string): Observable<Recibo[]> {
    return this.http.get<Recibo[]>(this.API_URL + legajo);
  }

  agregarRecibo(recibo: Recibo): Observable<Recibo> {
    return this.http.post<Recibo>(this.API_URL + 'nuevo', recibo);
  }

  obtenerReporte(anio: number, mes: number): Observable<Reporte[]> {
    return this.http.get<Reporte[]>(this.API_URL +  anio + '/' + mes);
  }
}
