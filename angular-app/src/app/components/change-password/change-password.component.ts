import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {MustMatch} from "../../helper/must-match-validator";
import {ChangePasswordService} from "../../services/change-password.service";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  changePasswordForm = this.formBuilder.group({
    oldPassword: ['', [Validators.required]],
    newPassword: ['', [Validators.required, Validators.pattern("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")]],
    confirmPassword: ['', [Validators.required]]
  },{
      validators:MustMatch('newPassword','confirmPassword')
  });
  constructor(private formBuilder: FormBuilder, private service: ChangePasswordService) { }
  changePassword(){
    let passDto = {
        "oldPassword": this.changePasswordForm.value.oldPassword,
      "newPassword":this.changePasswordForm.value.newPassword
    }
    this.service.changePassword(JSON.stringify(passDto));
  }
  ngOnInit(): void {
  }

}
