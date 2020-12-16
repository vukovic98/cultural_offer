import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import Swal from 'sweetalert2';
import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {map} from "rxjs/operators";
import {userDto} from "../model/userDto";


@Injectable()
export class EditProfileService{
  private  readonly editUserPath = "users/editProfile/";
  private  readonly getUserPath = "users/loggedInUser";

  constructor(private http: HttpClient, private route: Router) {

  }
  getUser():any{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get<userDto>(environment.apiUrl + this.getUserPath, {headers: headers})
      .pipe(map(response => response))

  }
  editUser(userDto: string, id: number):any{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
  console.log(userDto);
    return this.http.put<userDto>(environment.apiUrl + this.editUserPath + id, userDto, {headers: headers})
      .pipe(map(response => response))
      .subscribe(token => {
        Swal.fire({
          position: 'center',
          title: 'Changes saved!',
          icon: 'success',
          showConfirmButton: false,
          timer: 1500
        }).then(() => this.route.navigate(['/home-page']))

        return true;
      }, error => {
        Swal.fire({
          title: 'Error!',
          text: 'Can not save changes.',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        })
      })
  }

}

