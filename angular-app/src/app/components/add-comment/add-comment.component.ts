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
  image: any;
  files: string[] = [];

  addForm = new FormGroup({
    commentText: new FormControl('', Validators.required),
    file: new FormControl('', []),
    fileSource: new FormControl('', [])
  });

  constructor(
    private route: ActivatedRoute,
    private offerService: CulturalOfferService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    const token = this.authService.getToken();
    const data = this.authService.decodeToken(token);

    this.userEmail = data?.sub || "";
    this.offerId = this.route.snapshot.paramMap.get('id');
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  get f() {
    return this.addForm.controls;
  }

  onFileChange(event: any){
    for (let i = 0; i < event.target.files.length; i++) {
      this.files.push(event.target.files[i]);
    }

    if (event.target.files && event.target.files[0]) {
      const filesAmount = event.target.files.length;
      for (let i = 0; i < filesAmount; i++) {
        const reader = new FileReader();
        reader.onload = (event: any) => {
          if (this.image != event.target.result) {
            this.image = event.target.result;
            this.addForm.patchValue({
              fileSource: this.image
            });
          }
        }
        reader.readAsDataURL(event.target.files[i]);
      }
    }

  }



  submit(): void{
    let file;

    if(this.image == null){
      file = "";
    }else{
      file = this.image.split(',')[1];
    }

    this.offerService.addComment(Number(this.offerId),this.addForm.value.commentText,file)
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
    });

  }



}
