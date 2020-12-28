import {Component, Input, OnInit} from '@angular/core';
import {PostModel} from '../../model/post-model';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  @Input() post: PostModel = {} as PostModel;

  constructor(private service: AuthService) { }

  ngOnInit(): void {
  }

  isAdmin() {
    return this.service.isAdmin();
  }

}
