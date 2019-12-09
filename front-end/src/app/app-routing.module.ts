import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientComponent } from './components/client/client.component';
import { AnalyseComponent } from './components/analyse/analyse.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  {path:"clients", component: ClientComponent},
  {path:"clients/:code/analyses", component : AnalyseComponent},
  {path:"login", component : LoginComponent},
  {path:"", redirectTo : "clients",pathMatch : 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
