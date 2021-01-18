import {Component, Input, OnInit} from '@angular/core';
import {PostModel} from '../../model/post-model';
import {AuthService} from '../../services/auth.service';
import { CulturalOfferService } from '../../services/culturalOffer.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  @Input() post: PostModel = {} as PostModel;

  constructor(private offerService: CulturalOfferService,
    private service: AuthService) { }

  ngOnInit(): void {
  }

  isAdmin() {
    return this.service.isAdmin();
  }

  onClickDeletePost(){

    this.offerService.deletePost(this.post.id)
    .subscribe(response => {
      Swal.fire({
        title: 'Success!',
        text: 'Post successfully deleted!',
        icon: 'success',
        confirmButtonText: 'OK'
      }).then(() => {
        location.reload();
      });
    }, error => {
      Swal.fire({
        title: 'Error!',
        text: 'Something went wrong while attempting to delete the post.',
        icon: 'error',
        confirmButtonColor: '#DC143C',
        confirmButtonText: 'OK'
      });
    })

  }

}
