import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {map} from 'rxjs/operators';
import Swal from "sweetalert2";
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    "email": new FormControl('', [Validators.required, Validators.email]),
    "password": new FormControl('', Validators.required)
  });

  constructor(
    private service: AuthService,
    private route: Router
  ) {}

  loginUser(): void {
    const loginDto = {
      "email": this.loginForm.value.email,
      "password": this.loginForm.value.password
    };

    this.service.login(JSON.stringify(loginDto))
      .subscribe(response => {
        if (response.verified){
          localStorage.setItem("accessToken", response.authenticationToken);
          this.route.navigate(['/']);
        } else {
          this.route.navigate(['/auth/verify'], {  queryParams: {  email: response.email } });
        }
      }, error => {
        Swal.fire({
          title: 'Error!',
          text: 'There is no user with these credentials!',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
      });
  }

  ngOnInit(): void {
  }
}
