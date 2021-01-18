import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {passwordDto} from "../model/password-model";
import {Observable} from 'rxjs';
import {userDto} from "../model/userDto";


@Injectable()
export class ChangePasswordService{

  private  readonly changePassPath = "users/changePassword";

  constructor(private http: HttpClient) {

  }

  changePassword(passDto: string):Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.put<userDto>(environment.apiUrl + this.changePassPath, passDto, {headers: headers})
  }

}

