import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import Swal from 'sweetalert2';
import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {map} from "rxjs/operators";
import {passwordDto} from "../model/password-model";
import {Observable} from 'rxjs';


@Injectable()
export class ChangePasswordService{

  private  readonly changePassPath = "users/changePassword";

  constructor(private http: HttpClient, private route: Router) {

  }

  changePassword(passDto: string):Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.put<passwordDto>(environment.apiUrl + this.changePassPath, passDto, {headers: headers})
  }

}

