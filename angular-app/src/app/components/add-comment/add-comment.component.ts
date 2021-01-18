import {Component, Input, OnInit} from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import {AuthService} from '../../services/auth.service';
import { CulturalOfferService } from '../../services/culturalOffer.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { ImageModel } from 'src/app/model/comment-model';
import Swal from "sweetalert2";
@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent implements OnInit {

  private userEmail: string = "";
  commentText: string = "";
  offerId: string|null = '';
  images = [] as any;
  files: string[] = [];

  addForm = new FormGroup({
    commentText: new FormControl('', Validators.required),
    file: new FormControl('', [Validators.required]),
    fileSource: new FormControl('', [Validators.required])
  });

  constructor(private route: ActivatedRoute,
    private offerService: CulturalOfferService,
    private authService: AuthService) { }

  ngOnInit(): void {
    let token = this.authService.getToken();
    let data = this.authService.decodeToken(token);

    this.userEmail = data?.sub || "";
    this.offerId = this.route.snapshot.paramMap.get('id');
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  onFileChange(event: any){

    console.log("form changed")
    for (let i = 0; i < event.target.files.length; i++) {
      this.files.push(event.target.files[i]);
    }

    if (event.target.files && event.target.files[0]) {
      const filesAmount = event.target.files.length;
      for (let i = 0; i < filesAmount; i++) {
        let reader = new FileReader();
        reader.onload = (event: any) => {
          if (!this.images.includes(event.target.result)) {
            this.images.push(event.target.result);
            this.addForm.patchValue({
              fileSource: this.images
            });
          }
        }
        reader.readAsDataURL(event.target.files[i]);
      }
    }

  }


  
  onClickAddComment(): void{

    console.log(this.commentText);
    this.offerService.addComment(Number(this.offerId),this.commentText,this.images[0].split(',')[1])
    .subscribe(response => {
      Swal.fire({
        title: 'Success!',
        text: 'Comment successfully created! It will show up once one of our admins approves it.',
        icon: 'success',
        confirmButtonText: 'OK'
      });
    }, error => {
      Swal.fire({
        title: 'Error!',
        text: 'Image size must be less than 64Kb!',
        icon: 'error',
        confirmButtonColor: '#DC143C',
        confirmButtonText: 'OK'
      });
    })

  }



}
