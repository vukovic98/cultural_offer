import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import Swal from 'sweetalert2';
import {Router} from "@angular/router";
import {Role, TokenModel} from '../model/token.model';

@Injectable()
export class AuthService {
  private readonly loginPath = "auth/log-in";
  private  readonly signupPath = "auth/sign-up";
  private  readonly verificationCodePath = "auth/verify"

  constructor(private http: HttpClient, private route: Router) {
  }

  login(loginDto: string):any {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    let response = this.http.post<loginResponse>(environment.apiUrl + this.loginPath, loginDto, {headers: headers})
      .pipe(map(response => response.authenticationToken))
      .subscribe(token => {
        localStorage.setItem("accessToken", token);
        this.route.navigate(['/']);
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
  verifyCode(verifyDto: string):any{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
      let response = this.http.post<verifyResponse>(environment.apiUrl + this.verificationCodePath, verifyDto, {headers: headers})
        .pipe(map(response => response))
        .subscribe(response => {
          Swal.fire({
            title: 'Successful verification!',
            icon: 'success',
            confirmButtonColor: '#287507',
            confirmButtonText: 'Go to login page'
          }).then((result) => {
            if(result.isConfirmed){
              this.route.navigate(['/login']);
            }
          })

          return true;
        }, error => {
          Swal.fire({
            title: 'Error!',
            text: 'Wrong verification code.',
            icon: 'error',
            confirmButtonColor: '#DC143C',
            confirmButtonText: 'OK'
          })
        })


  }
  signUp(signupDto: string):any {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    let response = this.http.post<signupResponse>(environment.apiUrl + this.signupPath, signupDto, {headers: headers})
      .pipe(map(response => response))
      .subscribe(response => {
        this.route.navigate(['/verify'],{  queryParams: {  email: response.email } })

        return true;
      }, error => {
        Swal.fire({
          title: 'Error!',
          text: 'User with this email already exists!',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
        return false;
      })
  }

  logout(): void {
    localStorage.removeItem("accessToken");
    this.route.navigate(['/']);
  }

  getToken(): string{
    return <string> localStorage.getItem("accessToken");
  }

  isAdmin(): boolean {
    let authorities = this.getUserAuthorities();
    let admin_role = "ROLE_ADMIN";

    for(let a of authorities) {
      if(admin_role === a.name)
        return true;
    }

    return false;
  }

  isUser(): boolean {
    let authorities = this.getUserAuthorities();
    let admin_role = "ROLE_USER";

    for(let a of authorities) {
      if(admin_role === a.name)
        return true;
    }

    return false;
  }

  isLoggedIn(): boolean {
    let token = this.getToken();
    return !!token;
  }

  decodeToken(token: string): TokenModel | null {
    if (token) {
      let payload = token.split(".")[1];
      payload = window.atob(payload);
      return JSON.parse(payload);
    } else return null;
  }

  getUserAuthorities(): Array<Role> {
    let token = this.getToken();
    if(token) {
      let model = this.decodeToken(token);
      return model?.authority ? model.authority : [];
    } else {
      return [];
    }
  }

  getUserId(): string{
    let token = this.getToken();
    if(token) {
      let model = this.decodeToken(token);
      return model?.user_id ? model.user_id : "-1";
    } else {
      return "-1";
    }
  }
}


interface loginResponse {
  authenticationToken: string;
  expiresAt: number;
  email: string;
}
interface signupResponse {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
}
interface verifyResponse {
  code: string;
  userEmail: string;
}
