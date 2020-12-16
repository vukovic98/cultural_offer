import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import Swal from 'sweetalert2';
import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {map} from "rxjs/operators";
import {passwordDto} from "../model/password-model";


@Injectable()
export class ChangePasswordService{

  private  readonly changePassPath = "users/changePassword";

  constructor(private http: HttpClient, private route: Router) {

  }

  changePassword(passDto: string):any{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    console.log(passDto);
    return this.http.put<passwordDto>(environment.apiUrl + this.changePassPath, passDto, {headers: headers})
      .pipe(map(response => response))
      .subscribe(token => {
        Swal.fire({
          position: 'center',
          title: 'Password successfully changed',
          icon: 'success',
          showConfirmButton: false,
          timer: 1500
        }).then(() => this.route.navigate(['/home-page']))

        return true;
      }, error => {
        console.log(error);
        Swal.fire({
          title: 'Error!',
          text: 'Old password is wrong.',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        })
      })
  }

}

