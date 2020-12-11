import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string = '';
  password: string = '';

  constructor(private service: AuthService) {
  }
  loginUser(): void {
    let loginDto = {
      "email": this.email,
      "password": this.password
    };

    this.service.login(JSON.stringify(loginDto));

  }

  ngOnInit(): void {
  }
}
