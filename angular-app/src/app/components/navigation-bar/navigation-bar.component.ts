import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  private userLoggedIn: boolean = false;

  constructor(private service: AuthService) { }

  ngOnInit(): void {
    let token: string | null = localStorage.getItem("accessToken");

    this.userLoggedIn = token !== null;
  }

  getUser(): boolean {
    return this.userLoggedIn;
  }

  isUser() {
    return this.service.isUser();
  }

  isUserAdmin(): boolean {
    return this.service.isAdmin();
  }

  getUserName(): any {
    let token = localStorage.getItem("accessToken");
    let payload: string = '';
    if (token) {
      payload = token.split(".")[1];
      payload = window.atob(payload);
      return JSON.parse(payload).user_firstName;
    }
  }

  logout(): void {
    this.userLoggedIn = false;
    this.service.logout();
  }

}
