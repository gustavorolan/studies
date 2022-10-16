import { TransferenciasService } from './../services/transferencias.service';
import { Component, Input, OnInit } from '@angular/core';
import { Transferencia } from '../models/transferencia.model';

@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.scss'],
})
export class ExtratoComponent implements OnInit {
  transferencias: Transferencia[];

  constructor(private service: TransferenciasService) {}

  ngOnInit() {
    this.transferencias = this.service.todas()
  }
}
