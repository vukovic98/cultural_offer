import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  private userLoggedIn: boolean = false;
  constructor() { }

  ngOnInit(): void {
    let token: string | null = localStorage.getItem("accessToken");

    this.userLoggedIn = token !== null;
  }

  getUser(): boolean {
    return this.userLoggedIn;
  }

}
