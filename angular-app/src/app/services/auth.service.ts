import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import Swal from 'sweetalert2';

@Injectable()
export class AuthService {
  private readonly loginPath = "auth/log-in";

  constructor(private http: HttpClient) {
  }


  login(loginDto: string):any {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    let response = this.http.post<loginResponse>(environment.apiUrl + this.loginPath, loginDto, {headers: headers})
      .pipe(map(response => response.authenticationToken))
      .subscribe(token => {
        localStorage.setItem("accessToken", token);

        return true;
      }, error => {
        Swal.fire({
          title: 'Error!',
          text: 'There is no user with these credentials!',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        })
      })
  }

}

interface loginResponse {
  authenticationToken: string;
  expiresAt: number;
  email: string;
}
