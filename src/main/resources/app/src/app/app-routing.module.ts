import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { LoginComponent } from './component/login/login.component';
import {AuthGuard} from "./component/login/auth.guard";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {
    path: 'home',
    canActivate: [AuthGuard],
    component: HomeComponent
  },
  {path: '', redirectTo: 'home', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
