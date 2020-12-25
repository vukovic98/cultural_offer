import {Component, Input, OnInit} from '@angular/core';
import {CommentModel} from '../../model/comment-model';

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.css']
})
export class CommentListComponent implements OnInit {

  @Input() comments: Array<CommentModel> | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
