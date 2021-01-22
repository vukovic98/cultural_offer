import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AuthRoutingModule} from './auth-routing.module';
import {LoginComponent} from '../../components/login/login.component';
import {SignUpComponent} from '../../components/sign-up/sign-up.component';
import {UserVerificationComponent} from '../../components/user-verification/user-verification.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatIconModule} from "@angular/material/icon";


@NgModule({
  declarations: [
    LoginComponent,
    SignUpComponent,
    UserVerificationComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule
  ]
})
export class AuthModule { }
