import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {HomeComponent} from './components/home/home.component';
import {SignUpComponent} from "./components/sign-up/sign-up.component";

import { AddOfferComponent } from './components/add-offer/add-offer.component';
import {UserVerificationComponent} from "./components/user-verification/user-verification.component";

const routes: Routes = [
  {path: '', redirectTo: 'home-page', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'home-page', component: HomeComponent},
  {path: 'sign-up', component: SignUpComponent},
  {path: 'add-offer', component: AddOfferComponent}
  {path: 'verify', component: UserVerificationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
