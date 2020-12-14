import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import { Observable } from 'rxjs';
import {AuthService} from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(private service: AuthService, private router: Router) {
  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let authorities = this.service.getUserAuthorities();
    let data = route.data.acceptRoles;
    let roles = data.indexOf("|") !== -1 ? data.split("|") : data;

    for(let a of authorities) {
      if(roles.indexOf(a.name) !== -1)
        return true;
    }

    this.router.navigate(["/login"]);
    return false;
  }

}
