import { Injectable } from '@angular/core';
import { Transferencia } from '../models/transferencia.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class TransferenciasService {
  private transferenciaList: Transferencia[];
  private url = 'http://localhost:3000/transferencias';

  constructor(private httpClient: HttpClient) {
    this.transferenciaList = [];
  }

  get transferencias() {
    return this.transferenciaList;
  }

  todas() {
    const transferenciaList: Transferencia[] = [];
    this.httpClient
      .get<Transferencia[]>(this.url)
      .subscribe((transferencias: Transferencia[]) => {
        transferenciaList.push(...transferencias);
      });
    return transferenciaList;
  }

  adicionar(transferencia: Transferencia, limparCampos:any) {
    this.httpClient
      .post<Transferencia>(this.url, {
        ...transferencia,
        data: new Date(),
      })
      .subscribe((transferencia) => {
        console.log(transferencia);
        limparCampos()
      },error=>{
        console.log(error)
      });
  }
}
