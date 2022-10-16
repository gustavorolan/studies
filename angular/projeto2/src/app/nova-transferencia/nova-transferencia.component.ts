import { ExtratoComponent } from './../extrato/extrato.component';
import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Transferencia } from '../models/transferencia.model';
import { TransferenciasService } from '../services/transferencias.service';
import {Router} from '@angular/router'

@Component({
  selector: 'app-nova-transferencia',
  templateUrl: './nova-transferencia.component.html',
  styleUrls: ['./nova-transferencia.component.scss'],
})
export class NovaTransferenciaComponent implements OnInit {
  ngOnInit(): void {}

  @Output() aoTransferir = new EventEmitter<any>();

  valor: number;
  destino: string;

  constructor(
    private service: TransferenciasService,
    private router:Router
    ) {}

  transferir() {
    const transferencia: Transferencia = {
      ...{
        valor: this.valor,
        destino: this.destino,
      },
    };
    this.service.adicionar(transferencia,this.limparCampos);
    this.router.navigateByUrl('extrato')
  }
  limparCampos() {
    this.valor = null;
    this.destino = null;
  }
}
