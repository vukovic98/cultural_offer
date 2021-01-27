import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {EditProfileService} from "../../services/edit-profile.service";
import {userDto} from "../../model/userDto";
import Swal from "sweetalert2";

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
    "firstName": new FormControl('', Validators.required),
    "lastName": new FormControl('', Validators.required),
    "email": new FormControl({value: '', disabled: true}, Validators.required)
  });

  constructor(private service: EditProfileService) { }

  editProfile():void{
    const userDto = {
      "firstName": this.editProfileForm.value.firstName,
      "lastName": this.editProfileForm.value.lastName,
      "email": this.editProfileForm.value.email,
      "id": this.id,
      "password": this.password
    };

    this.service.editUser(JSON.stringify(userDto), this.id)
      .subscribe(token => {
        Swal.fire({
          position: 'center',
          title: 'Changes saved!',
          icon: 'success',
          showConfirmButton: false,
          timer: 1500
        });
      }, error => {
        Swal.fire({
          title: 'Error!',
          text: 'Can not save changes.',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
      });

    this.initials = userDto.firstName.charAt(0).toUpperCase() + userDto.lastName.charAt(0).toUpperCase();
    this.firstName = userDto.firstName;
    this.lastName = userDto.lastName;
  }

  getUser(): void {
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

