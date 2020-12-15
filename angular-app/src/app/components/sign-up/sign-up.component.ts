import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MustMatch} from "../../helper/must-match-validator";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {


  /*signupForm = new FormGroup(
    {
      "firstName": new FormControl('',[Validators.required,Validators.pattern("[A-Z][a-z]+")]),
      "lastName": new FormControl('',[Validators.required,Validators.pattern("[A-Z][a-z]+")]),
      "email": new FormControl('',[Validators.required,Validators.pattern("^[a-z0-9._%+-]+@maildrop.cc")]),
      "password": new FormControl('',[Validators.required,Validators.pattern("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")]),
      "passConfirm": new FormControl('',[Validators.required])
    }, {
      validator: MustMatch('password', 'passConfirm')
    });*/

  signupForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.pattern("")]],
      lastName: ['', [Validators.required, Validators.pattern("")]],
      email: ['', [Validators.required, Validators.pattern("")]],
      password: ['', [Validators.required, Validators.pattern("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")]],
      passConfirm: ['', Validators.required]
    },
    {
      validator: MustMatch('password', 'passConfirm')
    });


  constructor(private service: AuthService, private formBuilder: FormBuilder) { }


  signUp(): void{
    let signUpDto = {
      "id": 0,
      "firstName": this.signupForm.value.firstName,
      "lastName": this.signupForm.value.lastName,
      "email": this.signupForm.value.email,
      "password": this.signupForm.value.password
    };

    this.service.signUp(JSON.stringify(signUpDto));
  }
  ngOnInit(): void {
  }

}
