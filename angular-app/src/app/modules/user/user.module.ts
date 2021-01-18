import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {UserRoutingModule} from './user-routing.module';
import {ProfileComponent} from '../../components/profile/profile.component';
import {ChangePasswordComponent} from '../../components/change-password/change-password.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {SharedModule} from '../shared/shared.module';
import {MatButtonModule} from "@angular/material/button";



@NgModule({
  declarations: [
    ProfileComponent,
    ChangePasswordComponent,
  ],
    imports: [
        CommonModule,
        UserRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        SharedModule,
        MatButtonModule
    ]
})
export class UserModule { }
