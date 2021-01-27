import {Component, Input, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {map} from 'rxjs/operators';
import Swal from "sweetalert2";

@Component({
  selector: 'app-user-verification',
  templateUrl: './user-verification.component.html',
  styleUrls: ['./user-verification.component.css']
})
export class UserVerificationComponent implements OnInit {
  email: string = '';

  verifyForm = new FormGroup({
    "code": new FormControl('', Validators.required)
  });

  constructor(
    private service: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    ) { }

  verifyUser(){
    const verifyDto = {
      "code": this.verifyForm.value.code,
      "userEmail": this.email,

    };

    this.service.verifyCode(JSON.stringify(verifyDto))
      .subscribe(response => {
        Swal.fire({
          title: 'Successful verification!',
          icon: 'success',
          confirmButtonColor: '#287507',
          confirmButtonText: 'Go to login page'
        }).then((result) => {
          if(result.isConfirmed) {
            this.router.navigate(['/auth/login']);
          }
        });
      }, error => {
        Swal.fire({
          title: 'Error!',
          text: 'Wrong verification code.',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
      });
  }
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.email = params['email'] || '';
    });
  }

  sendCodeAgain(email: string) {
    this.service.sendCodeAgain(email)
      .subscribe(response => {
        Swal.fire({
          title: 'Code sent again. Please check your mail!',
          text: 'It may take a few minutes to get mail.',
          icon: 'success',
          showConfirmButton: false,
          timer: 2100
        });
      });
  }
}
