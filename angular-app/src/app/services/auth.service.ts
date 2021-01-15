import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import Swal from 'sweetalert2';
import {Router} from "@angular/router";
import {Role, TokenModel} from '../model/token.model';
import {Observable} from 'rxjs';
import {loginResponse, signupResponse, verifyResponse} from '../model/auth-model';

@Injectable()
export class AuthService {
  private readonly loginPath = "auth/log-in";
  private  readonly signupPath = "auth/sign-up";
  private  readonly verificationCodePath = "auth/verify";
  private  readonly sendCodeAgainPath = "auth/sendCodeAgain";

  constructor(private http: HttpClient, private route: Router) {
  }

  login(loginDto: string):Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.post<loginResponse>(environment.apiUrl + this.loginPath, loginDto, {headers: headers})
  }

  verifyCode(verifyDto: string):Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.post<verifyResponse>(environment.apiUrl + this.verificationCodePath, verifyDto, {headers: headers})
  }

  signUp(signupDto: string):Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.post<signupResponse>(environment.apiUrl + this.signupPath, signupDto, {headers: headers})
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

  sendCodeAgain(email: string): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.post<String>(environment.apiUrl + this.sendCodeAgainPath, email, {headers: headers})
  }
}
