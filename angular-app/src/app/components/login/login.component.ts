import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

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

  constructor(private service: AuthService) {
  }
  loginUser(): void {
    let loginDto = {
      "email": this.loginForm.value.email,
      "password": this.loginForm.value.password
    };

    this.service.login(JSON.stringify(loginDto));
  }

  ngOnInit(): void {
  }
}
