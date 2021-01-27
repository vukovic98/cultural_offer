import {Component, Input, OnInit} from '@angular/core';
import {CommentModel, ImageModel} from '../../model/comment-model';
import {CulturalOffer} from '../../model/offer-mode';
import {environment} from '../../../environments/environment';
import {AuthService} from '../../services/auth.service';
import { CulturalOfferService } from '../../services/culturalOffer.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-comment-item',
  templateUrl: './comment-item.component.html',
  styleUrls: ['./comment-item.component.css']
})
export class CommentItemComponent implements OnInit {

  @Input() comment: CommentModel = {
    commenterEmail: "",
    commenterName: "",
    content: "",
    id: 0,
    image:  null,
    offer:  null
  };

  constructor(
    private offerService: CulturalOfferService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
  }

  isAdmin(): boolean {
    return this.authService.isAdmin();
  }


  imagePrefix() {
    return environment.picPrefix;
  }

  onClickDeleteComment(){

    this.offerService.deleteComment(this.comment.id)
    .subscribe(response => {
      Swal.fire({
        title: 'Success!',
        text: 'Comment successfully deleted!',
        icon: 'success',
        confirmButtonText: 'OK'
      }).then(() => {
        location.reload();
      });
    }, error => {
      Swal.fire({
        title: 'Error!',
        text: 'Something went wrong while attempting to delete the comment.',
        icon: 'error',
        confirmButtonColor: '#DC143C',
        confirmButtonText: 'OK'
      });
    });

  }

}
