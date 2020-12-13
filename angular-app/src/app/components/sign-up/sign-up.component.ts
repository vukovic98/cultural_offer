import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  password: string = '';
  passwordConfirm: string = ''

  constructor(private service: AuthService) { }

  signUp(): void{
    let signUpDto = {
      "id": 0,
      "firstName": this.firstName,
      "lastName": this.lastName,
      "email": this.email,
      "password": this.password
    };
    this.service.signUp(JSON.stringify(signUpDto));
  }
  ngOnInit(): void {
  }

}
