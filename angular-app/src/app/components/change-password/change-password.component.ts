import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {MustMatch} from "../../helper/must-match-validator";
import {ChangePasswordService} from "../../services/change-password.service";
import Swal from "sweetalert2";
import {Router} from '@angular/router';

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

  constructor(
    private formBuilder: FormBuilder,
    private service: ChangePasswordService,
    private route: Router
    ) { }

  changePassword(){
    let passDto = {
        "oldPassword": this.changePasswordForm.value.oldPassword,
      "newPassword":this.changePasswordForm.value.newPassword
    }
    this.service.changePassword(JSON.stringify(passDto))
      .subscribe(token => {
        Swal.fire({
          position: 'center',
          title: 'Password successfully changed',
          icon: 'success',
          showConfirmButton: false,
          timer: 1500
        }).then(() => this.route.navigate(['/home-page']))

      }, error => {
        console.log(error);
        Swal.fire({
          title: 'Error!',
          text: 'Old password is wrong.',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        })
      })
  }

  ngOnInit(): void {
  }

  cancel() {
    this.route.navigate(["/home-page"]);
  }
}
