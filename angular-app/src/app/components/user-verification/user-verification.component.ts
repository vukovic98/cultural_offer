import {Component, Input, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {ActivatedRoute} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-user-verification',
  templateUrl: './user-verification.component.html',
  styleUrls: ['./user-verification.component.css']
})
export class UserVerificationComponent implements OnInit {
  email: string = '';

  verifyForm = new FormGroup({
    "code": new FormControl('',Validators.required)
  });

  constructor(private service: AuthService, private route: ActivatedRoute) { }

  verifyUser(){
    let verifyDto = {
      "code": this.verifyForm.value.code,
      "userEmail": this.email,

    };

    this.service.verifyCode(JSON.stringify(verifyDto));
  }
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.email = params['email'] || '';
    });
  }

}
