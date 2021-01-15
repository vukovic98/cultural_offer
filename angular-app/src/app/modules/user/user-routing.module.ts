import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProfileComponent} from '../../components/profile/profile.component';
import {RoleGuard} from '../../guards/role.guard';
import {ChangePasswordComponent} from '../../components/change-password/change-password.component';


const routes: Routes = [
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
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
