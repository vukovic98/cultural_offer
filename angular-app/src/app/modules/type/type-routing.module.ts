import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from '../../components/login/login.component';
import {SignUpComponent} from '../../components/sign-up/sign-up.component';
import {UserVerificationComponent} from '../../components/user-verification/user-verification.component';
import {AddTypeComponent} from '../../components/add-type/add-type.component';
import {RoleGuard} from '../../guards/role.guard';


const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: AddTypeComponent,
    canActivate: [RoleGuard],
    data: {acceptRoles: 'ROLE_ADMIN'}
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TypeRoutingModule { }
