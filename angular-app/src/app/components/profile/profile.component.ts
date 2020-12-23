import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {EditProfileService} from "../../services/edit-profile.service";
import {userDto} from "../../model/userDto";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  firstName: string = '';
  lastName: string = '';
  email: string ='';
  password: string = '';
  id: number = 0;
  initials: string = '';
  circleColor: string = '';

  private colors = [
    '#B00020',
    '#018786',
    '#03DAC6',
    '#6200EE',
  ];

  editProfileForm = new FormGroup({
    "firstName": new FormControl('',Validators.required),
    "lastName": new FormControl('',Validators.required),
    "email": new FormControl({value:'', disabled: true},Validators.required)
  });

  constructor(private service: EditProfileService) { }

  editProfile():void{
    let userDto = {

      "firstName": this.editProfileForm.value.firstName,
      "lastName": this.editProfileForm.value.lastName,
      "email": this.editProfileForm.value.email,
      "id": this.id,
      "password":this.password

    }
    this.service.editUser(JSON.stringify(userDto),this.id);
  }

  getUser():void{
    this.service.getUser().subscribe((user: userDto) => {
     this.firstName = user.firstName;
     this.lastName = user.lastName;
     this.email = user.email;
     this.id = user.id;
     this.initials = this.firstName.charAt(0).toUpperCase() + this.lastName.charAt(0).toUpperCase();
     const randomIndex = Math.floor(Math.random() * Math.floor(this.colors.length));
     this.circleColor = this.colors[randomIndex];
     this.editProfileForm.controls["firstName"].setValue(user.firstName);
     this.editProfileForm.controls["lastName"].setValue(user.lastName);
     this.editProfileForm.controls["email"].setValue(user.email);
   });
  }

  ngOnInit(): void {
    this.getUser();
  }

}

