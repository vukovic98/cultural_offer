import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SignupRequestPayload } from './signup-request.payload';
import { AuthService } from '../shared/auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  signupRequestPayload!: SignupRequestPayload;
  signupForm!: FormGroup;

  constructor(private authService: AuthService) {
    this.signupRequestPayload = {
      email: '',
      firstName: '',
      lastName: '',
      password: ''
    };
   }

  ngOnInit() {
    console.log("init");
    this.signupForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  signup() {
    this.signupRequestPayload.email = this.signupForm.get('email')!.value;
    this.signupRequestPayload.firstName = this.signupForm.get('firstName')!.value;
    this.signupRequestPayload.lastName = this.signupForm.get('lastName')!.value;
    this.signupRequestPayload.password = this.signupForm.get('password')!.value;  

    /*this.authService.signup(this.signupRequestPayload).subscribe(() => {
      console.log('Signup Successful');
    }, () => {
      console.log('Signup Failed');
    });*/
    
    this.authService.signup(this.signupRequestPayload)
      .subscribe(data => {
        console.log('Signup Successful');
        console.log(data);
      });
    
  }
}