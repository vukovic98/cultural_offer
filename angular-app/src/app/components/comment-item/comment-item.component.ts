import {Component, Input, OnInit} from '@angular/core';
import {CommentModel, ImageModel} from '../../model/comment-model';
import {CulturalOffer} from '../../model/offer-mode';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-comment-item',
  templateUrl: './comment-item.component.html',
  styleUrls: ['./comment-item.component.css']
})
export class CommentItemComponent implements OnInit {

  @Input() comment: CommentModel ={
    commenterEmail: "",
    commenterName: "",
    content: "",
    id: 0,
    image:  null,
    offer:  null
  };

  constructor() { }

  ngOnInit(): void {
  }

  imagePrefix() {
    return environment.picPrefix;
  }

}
