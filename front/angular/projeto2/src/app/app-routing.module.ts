import { ExtratoComponent } from './extrato/extrato.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NovaTransferenciaComponent } from './nova-transferencia/nova-transferencia.component';

const routes: Routes = [
  { path: '', redirectTo: 'extrato', pathMatch: 'full' },
  { path: 'extrato', component: ExtratoComponent },
  { path: 'nova', component: NovaTransferenciaComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
