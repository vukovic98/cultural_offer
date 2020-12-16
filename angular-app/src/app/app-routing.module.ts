import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {HomeComponent} from './components/home/home.component';
import {SignUpComponent} from "./components/sign-up/sign-up.component";

import { AddOfferComponent } from './components/add-offer/add-offer.component';
import {UserVerificationComponent} from "./components/user-verification/user-verification.component";
import {SubscribedItemsComponent} from './components/subscribed-items/subscribed-items.component';
import {RoleGuard} from './guards/role.guard';
import {ProfileComponent} from "./components/profile/profile.component";
import {ChangePasswordComponent} from "./components/change-password/change-password.component";

const routes: Routes = [
  {path: '', redirectTo: 'home-page', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'home-page', component: HomeComponent},
  {path: 'sign-up', component: SignUpComponent},
  {path: 'verify', component: UserVerificationComponent},
  {
    path: 'add-offer',
    component: AddOfferComponent,
    canActivate: [RoleGuard],
    data: {acceptRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'subscribed-items',
    component: SubscribedItemsComponent,
    canActivate: [RoleGuard],
    data: {acceptRoles: 'ROLE_USER'}
  },
  {
    path: "profile",
    component:ProfileComponent,
    canActivate: [RoleGuard],
    data: {acceptRoles: 'ROLE_USER|ROLE_ADMIN'}
  },
  {
    path: "change-password",
    component:ChangePasswordComponent,
    canActivate:[RoleGuard],
    data: {acceptRoles: 'ROLE_USER|ROLE_ADMIN'}
  }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
