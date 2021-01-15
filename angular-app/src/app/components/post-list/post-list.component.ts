import { Component, Input, OnInit } from '@angular/core';
import { PostModel } from 'src/app/model/post-model';
import { AuthService } from '../../services/auth.service';
import { CulturalOfferService } from '../../services/culturalOffer.service';
import { AddPostComponent } from '../add-post/add-post.component';
import { AddPostModel } from '../../model/post-model';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  @Input() offer_id: string = "";
  public posts: Array<PostModel> = [];
  public pageNum: number = 1;
  public nextBtn: boolean = false;
  public totalPages: number = 1;
  public totalElements: number = 0;
  newModel: AddPostModel = { id: 0, content: "", culturalOfferId: 0, title: "" };

  constructor(
    private authService: AuthService,
    private offerService: CulturalOfferService,
    private dialog: MatDialog) {
      console.log("post item contructor");
  }

  ngOnInit(): void {
    console.log("postlist id = ", this.offer_id);
    this.offerService.getPostsForOffer(Number(this.offer_id), this.pageNum)
      .subscribe((data: any) => {
        console.log("posts = ", data);
        this.posts = data.content;
        this.totalPages = data.totalPages;
        this.nextBtn = data.last;
        console.log(this.nextBtn);
        this.totalElements = data.totalElements;
      })
  }

  addPost() {
    const dialogRef = this.dialog.open(AddPostComponent, {
      width: '500px',
      data: this.newModel
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined) {
        let post = result.data;
        post.culturalOfferId = this.offer_id;
        this.offerService.addPost(post, ()=>{
          location.reload()
        });
      }
    });
  }
  
  isAdmin() {
    return this.authService.isAdmin();
  }

  retrievePosts() {
    this.offerService.getPostsForOffer(Number(this.offer_id), this.pageNum)
      .subscribe((data: any) => {
        this.posts = data.content;
        this.nextBtn = data.last;
      })
  }

  nextPage() {
    this.pageNum += 1;
    this.retrievePosts();
  }

  previousPage() {
    this.pageNum -= 1;
    this.retrievePosts();
  }

  firstPage() {
    this.pageNum = 1;
    this.retrievePosts();
  }

  lastPage() {
    this.pageNum = this.totalPages;
    this.retrievePosts();
  }
}
