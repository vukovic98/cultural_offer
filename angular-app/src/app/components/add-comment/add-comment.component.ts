import {Component, Input, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent implements OnInit {

  private userEmail: string = "";

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    let token = this.authService.getToken();
    let data = this.authService.decodeToken(token);

    this.userEmail = data?.sub || "";
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

}
